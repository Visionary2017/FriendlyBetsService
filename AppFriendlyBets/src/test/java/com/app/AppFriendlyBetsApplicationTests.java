package com.app;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.entities.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppFriendlyBetsApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void listarUsuarios() {
		Usuario[] usuarios = restTemplate.getForObject("http://localhost:8081/api/usuarios", Usuario[].class);
		List<Usuario> usuariosX = Arrays.asList(usuarios);
		System.out.println("Test - Listar Usuarios - GET");
		for(Usuario u:usuarios) {
			System.out.println(u.getIdUsuario() + " - " + u.getNombre() + " - " + u.getApellidos() + 
								" - " + u.getCelular() + " - " + u.getEmail() + " - " + u.getPassword());
		}
		assertNotNull(usuariosX);
	}
	
	@Test
	public void obtenerUsuario() {
		Usuario u = restTemplate.getForObject("http://localhost:8081/api/usuario/1", Usuario.class);
		System.out.println("Test - Obtener Usuario - GET");
		System.out.println(u.getIdUsuario() + " - " + u.getNombre() + " - " + u.getApellidos() + 
				" - " + u.getCelular() + " - " + u.getEmail() + " - " + u.getPassword());
		assertNotNull(u);
	}
	
	@Test
	public void validarUsuario() {
		long exists = restTemplate.getForObject("http://localhost:8081/api/usuario/amamani.9508@gmail.com/1263456", long.class);
		System.out.println("Test - Validar Usuario - GET");
		if(exists!=0) {
			System.out.println("El ID del Usuario es : " + exists);
		}else {
			System.out.println("El email y/o password son incorrectas.");
		}
		assertNotNull(exists);
	}
	
	@Test
	public void crearUsuario() {
		Usuario request_obj = new Usuario();
		
		request_obj.setApellidos("Perez");
		request_obj.setNombre("Pedro");
		request_obj.setCelular("999159357");;
		request_obj.setEmail("pperez3@gmail.com");
		request_obj.setPassword("123456");
		
		Usuario usuario = restTemplate.postForObject("http://localhost:8081/api/usuario", request_obj, Usuario.class);
		
		System.out.println("Test - Crear Usuario - POST");
		
		System.out.println("REGISTRADO ::: Codigo : " + usuario.getIdUsuario() + " / Apellido : " + usuario.getApellidos() + 
				" / Nombre : " + usuario.getNombre() + " / Celular : " + usuario.getCelular() +
				" / Email : " + usuario.getEmail() + " / Password : " + usuario.getPassword());
		
		assertNotNull(usuario);
	}
	
	@Test
	public void actualizarUsuario() {
		Usuario request_obj = new Usuario();
		
		request_obj.setApellidos("Perez");
		request_obj.setNombre("Pedro");
		request_obj.setCelular("951753111");;
		request_obj.setEmail("pperez2@gmail.com");
		request_obj.setPassword("123456");
		
		Usuario usuario = restTemplate.getForObject("http://localhost:8081/api/usuario/12", Usuario.class);
		
		if(usuario != null) {
			restTemplate.put("http://localhost:8081/api/usuario/12", request_obj, Usuario.class);
			
			System.out.println("Test - Actualizar Usuario - PUT");
			
			System.out.println("ACTUALIZADO ::: Codigo : " + usuario.getIdUsuario() + " / Apellido : " + usuario.getApellidos() + 
					" / Nombre : " + usuario.getNombre() + " / Celular : " + usuario.getCelular() +
					" / Email : " + usuario.getEmail() + " / Password : " + usuario.getPassword());
		}else {
			System.out.println("No existe el usuario");
		}
		
		assertNotNull(usuario);
	}
	
	@Test
	public void borrarUsuario() {
		
		Usuario usuario = restTemplate.getForObject("http://localhost:8081/api/usuario/12", Usuario.class);
		
		if(usuario != null) {
			restTemplate.delete("http://localhost:8081/api/usuario/12", Object.class);
			
			System.out.println("Test - Eliminar Usuario - PUT");
			
			System.out.println("ELIMINADO ::: Codigo : "+ usuario.getIdUsuario() + " / Apellido : " + usuario.getApellidos() + 
					" / Nombre : " + usuario.getNombre() + " / Celular : " + usuario.getCelular() +
					" / Email : " + usuario.getEmail() + " / Password : " + usuario.getPassword());
		}else {
			System.out.println("No existe el usuario");
		}
		
		assertNotNull(usuario);
	}
	

}

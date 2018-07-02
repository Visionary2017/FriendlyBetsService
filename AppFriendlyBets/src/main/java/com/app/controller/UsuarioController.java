package com.app.controller;

import java.util.List;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.exception.ResourceNotFoundException;

import com.app.repository.UsuarioRepositoy;
import com.app.entities.Usuario;

@CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	private UsuarioRepositoy usuarioRepository;

	@GetMapping("/usuarios")
	public List<Usuario> listarUsuarios() {
		return (List<Usuario>) usuarioRepository.findAll();
	}
	
	@CrossOrigin(origins = "*", allowCredentials = "true")
	@GetMapping("/usuario/{idUsuario}")
	public Usuario obtenerUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "idUsuario", idUsuario));
		return usuario;
	}
	
	@CrossOrigin(origins = "*", allowCredentials = "true")
	@GetMapping("/usuario/{email}/{password}")
	public long validarUsuario(@PathVariable(value = "email") String email,
							   @PathVariable(value = "password") String password) {
		return usuarioRepository.findByEmail(email, password);
	}
	
	@CrossOrigin(origins = "*", allowCredentials = "true")
	@PostMapping("/usuario")
	public Usuario crearUsuario(@Valid @RequestBody Usuario usuario) throws Exception {
		Usuario ent_usuario = null;
		long rs = usuarioRepository.existUsuario(usuario.getEmail());
		
		try {
			if(rs==0) {
				ent_usuario = usuarioRepository.save(usuario);
			}else {
				throw new Exception("Ya existe un usuario registrado con el correo " + usuario.getEmail());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		
		return ent_usuario;
	}

	@PutMapping("/usuario/{idUsuario}")
	public Usuario actualizarUsuario(@PathVariable(value = "idUsuario") Long idUsuario,
			@Valid @RequestBody Usuario usuarioDetalle) {

		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "idUsurio", idUsuario));
		usuario.setNombre(usuarioDetalle.getNombre());
		usuario.setApellidos(usuarioDetalle.getApellidos());
		usuario.setCelular(usuarioDetalle.getCelular());
		usuario.setEmail(usuarioDetalle.getEmail());
		usuario.setPassword(usuarioDetalle.getPassword());
		Usuario updateUsuario = usuarioRepository.save(usuario);
		return updateUsuario;
	}

	@DeleteMapping("/usuario/{idUsuario}")
	public ResponseEntity<String> borrarUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {

		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "idUsurio", idUsuario));
		usuarioRepository.delete(usuario);
		return ResponseEntity.ok().build();
	}

}

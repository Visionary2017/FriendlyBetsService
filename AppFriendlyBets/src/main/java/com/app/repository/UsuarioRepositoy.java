package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.entities.Usuario;

public interface UsuarioRepositoy extends CrudRepository<Usuario, Long>{

	@Query("SELECT idUsuario FROM Usuario WHERE email=:email AND password=:password")
	long findByEmail(@Param("email") String email,@Param("password") String password);
	
	@Query("SELECT COUNT(1) FROM Usuario WHERE email=:email")
	long existUsuario(@Param("email") String email);
	
}

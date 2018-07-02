package com.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "Usuario", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class Usuario implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idusuario", nullable=false)
	private Long idUsuario;
	
	@NotEmpty(message="El campo nombres es obligatorio.")
	@Column(name="nombre", nullable=false, length=100)
	private String nombre;
	
	@NotEmpty(message="El campo apellidos es obligatorio.")
	@Column(name="apellidos", nullable=false, length=100)
	private String apellidos;
	
	@NotEmpty(message="El campo celular es obligatorio.")
	@Column(name="celular", nullable=false, length=12)
	private String celular;
	
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", 
			 message = "El campo email no contiene el formato correcto")
	@NotEmpty(message="El campo email es obligatorio.")
	@Column(name="email", nullable=false, length=100, unique=true)
	private String email;
	
	@NotEmpty(message="El campo password es obligatorio.")
	@Column(name="password", nullable=false, length=60)
	private String password;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

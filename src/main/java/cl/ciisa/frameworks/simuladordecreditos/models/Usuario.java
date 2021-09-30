package cl.ciisa.frameworks.simuladordecreditos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;


@Entity
@Table( name="USUARIOS" )
public class Usuario {
	public Usuario() {
		
	}
	public Usuario(Long id, Long rut, String nombre, String apellido1, String apellido2, String calle, String comuna,
			String fechaNacimiento, String genero, String password, String email, int nivel) {
		super();
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.calle = calle;
		this.comuna = comuna;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.password = password;
		this.email = email;
		this.nivel = nivel;
	}
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long   id = null;
	@Column( name="USU_RUT" )
	private Long   rut = null;
	@Column( name="USU_NOMBRE" )
	private String nombre = null;
	@Column( name="USU_APELLIDO1" )
	private String apellido1 = null;
	@Column( name="USU_APELLIDO2" )
	private String apellido2 = null;
	@Column( name="USU_CALLE" )
	private String calle = null;
	@Column( name="USU_COMUNA" )
	private String comuna = null;
	@Column( name="USU_FECHA_NACIMIENTO" )
	private String fechaNacimiento = null;
	@Column( name="USU_GENERO" )
	private String genero = null;
	@Column( name="USU_PASSWORD" )
	private String password = null;
	@Column( name="USU_EMAIL" )
	private String email = null;
	@Column( name="USU_NIVEL" )
	private int    nivel = 0;
	public char dv() {
		int m = 0, s = 1;
		for (; rut != 0; rut /= 10) {
			s = (int) ((s + rut % 10 * (9 - m++ % 6)) % 11);
		}
		return (char) (s != 0 ? s + 47 : 75) ;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRut() {
		return rut;
	}
	public void setRut(Long rut) {
		this.rut = rut;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getComuna() {
		return comuna;
	}
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", rut=" + rut + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2="
				+ apellido2 + ", calle=" + calle + ", comuna=" + comuna + ", fechaNacimiento=" + fechaNacimiento
				+ ", genero=" + genero + ", password=" + password + ", email=" + email + ", nivel=" + nivel + "]";
	}

}

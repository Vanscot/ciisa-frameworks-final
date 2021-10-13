package cl.ciisa.frameworks.simuladordecreditos.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table( name="cliente" )
public class Cliente {
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long   id = null;
	@Column( name="cli_rut" )
	private Long   rut = (long) 0;
	@Column( name="cli_nombre" )
	private String nombre = null;
	@Column( name="cli_apellido1" )
	private String apellido1 = null;
	@Column( name="cli_apellido2" )
	private String apellido2 = null;
	@Column( name="cli_calle" )
	private String calle = null;
	@Column( name="cli_comuna" )
	private String comuna = null;
	@Column( name="cli_fecha_nacimiento" )
	private String fechaNacimiento = null;
	@Column( name="cli_genero" )
	private String genero = null;
	@Column( name="USU_EMAIL" )
	private String email = null;
	@Transient
	private String dv;
	@OneToMany
	@JoinColumn( name="cli_id" )
	private List<Credito> creditos;
	@OneToMany
	@JoinColumn( name="cli_id" )
	private List<Liquidacion> liquidaciones;
	@OneToMany
	@JoinColumn( name="cli_id" )
	private List<Simulacion> simulaciones;
	
	public Cliente() {
	}
	public Cliente( Long id, Long rut, String nombre, String apellido1, String apellido2, String calle, String comuna,
			String fechaNacimiento, String genero, String email ) {
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
		this.email = email;
	}
	public String calculaDigitoVerificador( long rut ) {
		int m = 0, s = 1;
		for (; rut != 0; rut /= 10) {
			s = (int) ((s + rut % 10 * (9 - m++ % 6)) % 11);
		}
		return ( (char) (s != 0 ? s + 47 : 75) ) + "";
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDv() {
		return calculaDigitoVerificador( this.rut );
	}
	public void setDv( char d ) {
		this.dv = this.rut != null ? calculaDigitoVerificador( this.rut ) : "";
	}
	public List<Credito> getCreditos() {
		return creditos;
	}
	public void setCreditos(List<Credito> creditos) {
		this.creditos = creditos;
	}
	public void addCredito( Credito credito ) {
		this.creditos.add( credito );
	}
	public List<Liquidacion> getLiquidaciones() {
		return liquidaciones;
	}
	public void setLiquidaciones(List<Liquidacion> liquidaciones) {
		this.liquidaciones = liquidaciones;
	}
	public void addLiquidacion( Liquidacion liquidacion ) {
		this.liquidaciones.add( liquidacion );
	}
	public List<Simulacion> getSimulaciones() {
		return simulaciones;
	}
	public void setSimulaciones(List<Simulacion> simulaciones ) {
		this.simulaciones = simulaciones;
	}
	public void addSimulacion( Simulacion simulacion ) {
		this.simulaciones.add( simulacion );
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", rut=" + rut + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2="
				+ apellido2 + ", calle=" + calle + ", comuna=" + comuna + ", fechaNacimiento=" + fechaNacimiento
				+ ", genero=" + genero + ", email=" + email + "]";
	}
}

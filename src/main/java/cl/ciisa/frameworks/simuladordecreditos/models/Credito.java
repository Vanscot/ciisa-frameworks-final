package cl.ciisa.frameworks.simuladordecreditos.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table( name="CREDITOS" )
public class Credito {
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long   id = null;
	@Column( name="CRE_RUT" )
	private Long   rut = null;
	@Column( name="CRE_NOMBRE" )
	private String nombre = null;
	@Column( name="CRE_MONTO" )
	private Long   monto = null;
	@Column( name="CRE_TASA" )
	private float  tasa  = 0;
	@Column( name="CRE_TIPO" )
	private String tipo = null;
	@Column( name="CRE_SEGUROS" )
	private Long   seguros = null;
	@Transient
	private List<Cuota> cuotas = null;
	
	public Credito() {
		
	}
	public Credito(Long id, Long rut, String nombre, Long monto, float tasa, String tipo, Long seguros) {
		super();
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.monto = monto;
		this.tasa = tasa;
		this.tipo = tipo;
		this.seguros = seguros;
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
	public Long getMonto() {
		return monto;
	}
	public void setMonto(Long monto) {
		this.monto = monto;
	}
	public float getTasa() {
		return tasa;
	}
	public void setTasa(float tasa) {
		this.tasa = tasa;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getSeguros() {
		return seguros;
	}
	public void setSeguros(Long seguros) {
		this.seguros = seguros;
	}
	public List<Cuota> getCuotas() {
		return cuotas;
	}
	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}
}

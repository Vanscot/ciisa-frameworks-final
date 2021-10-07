package cl.ciisa.frameworks.simuladordecreditos.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name="credito" )
public class Credito {
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long   id = null;
	@Column( name="cre_rut" )
	private Long   rut = null;
	@Column( name="cre_nombre" )
	private String nombre = null;
	@Column( name="cre_monto" )
	private Long   monto = null;
	@Column( name="cre_tasa" )
	private float  tasa  = 0;
	@Column( name="cre_tipo" )
	private String tipo = null;
	@Column( name="cre_seguro" )
	private Long   seguros = null;
	@OneToMany( mappedBy="credito" )
	private List<Cuota> cuotas;
	@ManyToOne
	@JoinColumn( name="cli_id")
	private Cliente cliente;
	
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
	public void addCuotas( Cuota cuota ) {
		this.cuotas.add( cuota );
	}
}

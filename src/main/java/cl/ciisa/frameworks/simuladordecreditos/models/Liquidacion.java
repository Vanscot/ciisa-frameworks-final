package cl.ciisa.frameworks.simuladordecreditos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name="liquidacion" )
public class Liquidacion {
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long   id = null;
	@Column( name="liq_rut" )
	private Long   rut = null;
	@Column( name="liq_ano" )
	private int    ano = 0;
	@Column( name="liq_mes" )
	private int    mes = 0;
	@Column( name="liq_monto" )
	private Long   monto = null;
	@ManyToOne
	@JoinColumn( name="cli_id" )
	private Cliente cliente;
	
	public Liquidacion() {
		
	}
	public Liquidacion(Long id, Long rut, int ano, int mes, Long monto) {
		super();
		this.id = id;
		this.rut = rut;
		this.ano = ano;
		this.mes = mes;
		this.monto = monto;
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
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public Long getMonto() {
		return monto;
	}
	public void setMonto(Long monto) {
		this.monto = monto;
	}

	
}

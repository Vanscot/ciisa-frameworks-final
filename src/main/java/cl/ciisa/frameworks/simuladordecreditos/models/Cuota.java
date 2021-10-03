package cl.ciisa.frameworks.simuladordecreditos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="CUOTAS" )
public class Cuota {
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long   id = null;
	@Column( name="CUO_CREDITO" )
	private String credito = null;
	@Column( name="CUO_MONTO_INTERES" )
	private Long   interes = null;
	@Column( name="CUO_MONTO_CAPITAL" )
	private Long   capital  = null;
	@Column( name="CUO_FECHA_VENCIMIENTO" )
	private String vencimiento = null;
	@Column( name="CUO_FECHA_PAGO" )
	private String pago = null;
	public Cuota() {
		
	}
	public Cuota(Long id, String credito, Long interes, Long capital, String vencimiento, String pago) {
		super();
		this.id = id;
		this.credito = credito;
		this.interes = interes;
		this.capital = capital;
		this.vencimiento = vencimiento;
		this.pago = pago;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCredito() {
		return credito;
	}
	public void setCredito(String credito) {
		this.credito = credito;
	}
	public Long getInteres() {
		return interes;
	}
	public void setInteres(Long interes) {
		this.interes = interes;
	}
	public Long getCapital() {
		return capital;
	}
	public void setCapital(Long capital) {
		this.capital = capital;
	}
	public String getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}
	public String getPago() {
		return pago;
	}
	public void setPago(String pago) {
		this.pago = pago;
	}
}


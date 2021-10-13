package cl.ciisa.frameworks.simuladordecreditos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table( name="CUOTAS" )
public class Cuota {
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long   id = null;
	@Column( name="cuo_monto" )
	private Long   monto  = (long) 0;
	@Column( name="cuo_vencimiento" )
	private String vencimiento = null;
	@Column( name="cuo_pago" )
	private String pago = "";
	@ManyToOne
	@JoinColumn( name="cre_id" )
	private Credito credito;
	@Transient
	private Long creId;
	@Transient
	private Long cliId;

	public Cuota() {
	}
	public Cuota(Long id, Long monto, String vencimiento, String pago) {
		super();
		this.id = id;
		this.monto = monto;
		this.vencimiento = vencimiento;
		this.pago = pago;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMonto() {
		return monto;
	}
	public void setMonto(Long monto) {
		this.monto = monto;
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
	public void setCreId( Long creId ) {
	}
	public Long getCreId() {
		return this.credito != null ? this.credito.getId() : null;
	}
	public void setCliId( Long cliId ) {
	}
	public Long getCliId() {
		return this.credito != null ? this.credito.getCliId() : null;
	}
}


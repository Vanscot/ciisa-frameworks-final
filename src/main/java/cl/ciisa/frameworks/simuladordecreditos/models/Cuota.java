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
}


package cl.ciisa.frameworks.simuladordecreditos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.List;

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
}

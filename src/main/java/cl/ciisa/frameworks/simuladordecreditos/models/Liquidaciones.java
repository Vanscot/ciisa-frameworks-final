package cl.ciisa.frameworks.simuladordecreditos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="LIQUIDACIONES" )
public class Liquidaciones {
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long   id = null;
	@Column( name="LIQ_RUT" )
	private Long   rut = null;
	@Column( name="LIQ_ANO" )
	private int    ano = 0;
	@Column( name="LIQ_MES" )
	private int    mes = 0;
	
	
}

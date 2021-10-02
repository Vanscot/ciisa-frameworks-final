package cl.ciisa.frameworks.simuladordecreditos.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="TIPO_CREDITO" )
public class TipoCredito {
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long   id = null;
	@Column( name="T_CRE_TIPO" )
	private String tipo = null;
}

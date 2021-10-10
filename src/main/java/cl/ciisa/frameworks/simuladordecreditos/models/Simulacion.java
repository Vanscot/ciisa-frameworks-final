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
@Table( name="simulacion" )
public class Simulacion {

		@Id
		@GeneratedValue( strategy=GenerationType.AUTO )
		private Long   id = null;
		@Column( name="sim_rut" )
		private Long   rut = null;
		@Column( name="sim_nombre" )
		private String nombre = null;
		@Column( name="sim_monto" )
		private Long   monto = null;
		@Column( name="sim_tasa" )
		private float  tasa  = 0;
		@Column( name="sim_tipo" )
		private String tipo = null;
		@Column( name="sim_seguro" )
		private Long   seguros = null;
		@Column( name="sim_cuotas" )
		private int cuotas = 0;
		@Column( name="sim_monto_cuota")
		private Long montoCuota;
		@Column( name="sim_monto_cuota_ano")
		private Long montoCuotaAno;
		@ManyToOne
		@JoinColumn( name="cli_id")
		private Cliente cliente;

		public Simulacion() {
			
		}
		public Simulacion(Long id, Long rut, String nombre, Long monto, float tasa, String tipo, Long seguros, int cuotas, Long montoCuota, Long montoCuotaAno ) {
			super();
			this.id = id;
			this.rut = rut;
			this.nombre = nombre;
			this.monto = monto;
			this.tasa = tasa;
			this.tipo = tipo;
			this.seguros = seguros;
			this.cuotas = cuotas;
			this.montoCuota = montoCuota;
			this.montoCuotaAno = montoCuotaAno;
		}

		public void calcular() {
			this.montoCuota = (long) ( ( this.monto / this.cuotas ) + ( this.seguros / this.cuotas ) + ( ( this.monto / this.cuotas ) * ( this.tasa / 12 ) ) );
			this.montoCuotaAno = (long) ( ( this.monto / 12 ) + ( this.seguros / 12 ) + ( ( this.monto / 12 ) * ( this.tasa / 12 ) ) );
		}
		
		@Override
		public String toString() {
			return "Simulacion [id=" + id + ", rut=" + rut + ", nombre=" + nombre + ", monto=" + monto + ", tasa="
					+ tasa + ", tipo=" + tipo + ", seguros=" + seguros + ", cuotas=" + cuotas
					+ "]";
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
		public int getCuotas() {
			return cuotas;
		}
		public void setCuotas(int cuotas) {
			this.cuotas = cuotas;
		}

		public Long getMontoCuota() {
			return montoCuota;
		}

		public void setMontoCuota(Long montoCuota) {
			this.montoCuota = montoCuota;
		}

		public Long getMontoCuotaAno() {
			return montoCuotaAno;
		}

		public void setMontoCuotaAno(Long montoCuotaAno) {
			this.montoCuotaAno = montoCuotaAno;
		}
}

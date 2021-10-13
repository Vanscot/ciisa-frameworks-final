package cl.ciisa.frameworks.simuladordecreditos.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.ciisa.frameworks.simuladordecreditos.models.Liquidacion;
import cl.ciisa.frameworks.simuladordecreditos.models.Simulacion;
import cl.ciisa.frameworks.simuladordecreditos.models.Cliente;
import cl.ciisa.frameworks.simuladordecreditos.models.Credito;
import cl.ciisa.frameworks.simuladordecreditos.models.Cuota;
import cl.ciisa.frameworks.simuladordecreditos.repository.CreditoRepository;
import cl.ciisa.frameworks.simuladordecreditos.repository.CuotaRepository;
import cl.ciisa.frameworks.simuladordecreditos.repository.LiquidacionRepository;
import cl.ciisa.frameworks.simuladordecreditos.repository.SimulacionRepository;
import cl.ciisa.frameworks.simuladordecreditos.repository.ClienteRepository;

@RestController
@Validated
@RequestMapping( "/API/clientes" )
public class ClienteAPI {

	@Autowired
	ClienteRepository link;
	@Autowired
	LiquidacionRepository linkLiquidaciones;
	@Autowired
	CreditoRepository linkCreditos;
	@Autowired
	CuotaRepository linkCuotas;
	@Autowired
	SimulacionRepository linkSimulaciones;
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<Cliente> getClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		for( Cliente cliente : link.findAll() ) {
			clientes.add( cliente );
		}
		return clientes;
	}
	
	@RequestMapping( value="/", method=RequestMethod.POST )
	public Cliente newCliente( @RequestBody Cliente clienteJSON ) {
		System.out.print( clienteJSON );
		System.out.print( "\n" );
		Cliente cliente = new Cliente();
		JSONObject clienteParsed = new JSONObject( clienteJSON );
		if( clienteParsed.has( "rut" ) ) cliente.setRut( clienteParsed.getLong( "rut" ) );
		if( clienteParsed.has( "nombre" ) ) cliente.setNombre( clienteParsed.getString( "nombre" ) );
		if( clienteParsed.has( "apellido1" ) ) cliente.setApellido1( clienteParsed.getString( "apellido1" ) );
		if( clienteParsed.has( "apellido2" ) ) cliente.setApellido2( clienteParsed.getString( "apellido2" ) );
		if( clienteParsed.has( "calle" ) ) cliente.setCalle( clienteParsed.getString( "calle" ) );
		if( clienteParsed.has( "comuna" ) ) cliente.setComuna( clienteParsed.getString( "comuna" ) );
		if( clienteParsed.has( "fechaNacimiento" ) ) cliente.setFechaNacimiento( clienteParsed.getString( "fechaNacimiento" ) );
		if( clienteParsed.has( "genero" ) ) cliente.setGenero( clienteParsed.getString( "genero" ) );
		if( clienteParsed.has( "email" ) ) cliente.setEmail( clienteParsed.getString( "email" ) );
		link.save( cliente );

		return cliente;
	}
	
	@RequestMapping( value="/{id_cliente}", method=RequestMethod.GET )
	public Cliente getCliente( @PathVariable Long id_cliente ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new Cliente();
		return result.get();
	}
	
	@RequestMapping( value="/{id_cliente}", method=RequestMethod.PATCH )
	public Cliente updCliente( @PathVariable Long id_cliente, @RequestBody Cliente clienteJSON ) {
		if( id_cliente == 1 ) return new Cliente();
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new Cliente();
		Cliente cliente = result.get();
		JSONObject clienteParsed = new JSONObject( clienteJSON );
		if( clienteParsed.has( "rut" ) ) cliente.setRut( clienteParsed.getLong( "rut" ) );
		if( clienteParsed.has( "nombre" ) ) cliente.setNombre( clienteParsed.getString( "nombre" ) );
		if( clienteParsed.has( "apellido1" ) ) cliente.setApellido1( clienteParsed.getString( "apellido1" ) );
		if( clienteParsed.has( "apellido2" ) ) cliente.setApellido2( clienteParsed.getString( "apellido2" ) );
		if( clienteParsed.has( "calle" ) ) cliente.setCalle( clienteParsed.getString( "calle" ) );
		if( clienteParsed.has( "comuna" ) ) cliente.setComuna( clienteParsed.getString( "comuna" ) );
		if( clienteParsed.has( "fechaNacimiento" ) ) cliente.setFechaNacimiento( clienteParsed.getString( "fechaNacimiento" ) );
		if( clienteParsed.has( "genero" ) ) cliente.setGenero( clienteParsed.getString( "genero" ) );
		if( clienteParsed.has( "email" ) ) cliente.setEmail( clienteParsed.getString( "email" ) );
		link.save( cliente );
		return cliente;
	}
	
	@RequestMapping( value="/{id_cliente}", method=RequestMethod.DELETE )
	public Cliente delCliente( @PathVariable Long id_cliente ) {
		link.deleteById( id_cliente );
		return new Cliente();
	}

	@RequestMapping( value="/{id_cliente}/liquidaciones", method=RequestMethod.GET ) //liquidaciones de una persona
	public List<Liquidacion> getLiquidaciones( @PathVariable Long id_cliente ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new ArrayList<Liquidacion>();
		return result.get().getLiquidaciones();
	}
	
	@RequestMapping( value="/{id_cliente}/liquidaciones", method=RequestMethod.POST ) //subir liquidaciones de una persona
	public Liquidacion addLiquidacion( @PathVariable Long id_cliente, @RequestBody Liquidacion liquidacionJSON ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new Liquidacion();
		Cliente cliente = result.get();
		Liquidacion liquidacion = new Liquidacion();
		JSONObject liquidacionParsed = new JSONObject( liquidacionJSON );
		if( liquidacionParsed.has( "rut" ) ) liquidacion.setRut( liquidacionParsed.getLong( "rut" ) );
		if( liquidacionParsed.has( "ano" ) ) liquidacion.setAno( liquidacionParsed.getInt( "ano" ) );
		if( liquidacionParsed.has( "mes" ) ) liquidacion.setMes( liquidacionParsed.getInt( "mes" ) );
		if( liquidacionParsed.has( "monto" ) ) liquidacion.setMonto( liquidacionParsed.getLong( "monto" ) );
		cliente.addLiquidacion( liquidacion );
		linkLiquidaciones.save( liquidacion );
		return liquidacion;
	}
	
	@RequestMapping( value="/{id_cliente}/liquidaciones/{id_liquidacion}", method=RequestMethod.GET ) //liquidacion especifica de una persona
	public Liquidacion getLiquidacion( @PathVariable Long id_cliente, @PathVariable Long id_liquidacion ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new Liquidacion();
		Cliente cliente = result.get();
		Optional<Liquidacion> resultLiquidacion = linkLiquidaciones.findById( id_liquidacion );
		if( ! resultLiquidacion.isPresent() ) return new Liquidacion();
		Liquidacion liquidacion = resultLiquidacion.get();
		if( ! cliente.getLiquidaciones().contains( liquidacion ) ) return new Liquidacion();
		return liquidacion;
	}
	
	@RequestMapping( value="/{id_cliente}/liquidaciones/{id_liquidacion}", method=RequestMethod.DELETE )
	public Liquidacion delLiquidacion( @PathVariable Long id_cliente, @PathVariable Long id_liquidacion ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new Liquidacion();
		Cliente cliente = result.get();
		Optional<Liquidacion> resultLiquidacion = linkLiquidaciones.findById( id_liquidacion );
		if( ! resultLiquidacion.isPresent() ) return new Liquidacion();
		Liquidacion liquidacion = resultLiquidacion.get();
		if( cliente.getLiquidaciones().contains( liquidacion ) ) linkLiquidaciones.deleteById( id_liquidacion );
		return new Liquidacion();
	}
	
	@RequestMapping( value="/{id_cliente}/creditos", method=RequestMethod.GET ) 
	public List<Credito> getCreditos( @PathVariable Long id_cliente ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new ArrayList<Credito>();
		return result.get().getCreditos();
	}
	
	@RequestMapping( value="/{id_cliente}/creditos/simular", method=RequestMethod.POST )
	public Simulacion simular( @PathVariable Long id_cliente, @RequestBody Simulacion simulacionJSON ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new Simulacion();
		Cliente cliente = result.get();
		Simulacion simulacion = new Simulacion();
		JSONObject simulacionParsed = new JSONObject( simulacionJSON );
		if( simulacionParsed.has( "monto" ) ) simulacion.setMonto( simulacionParsed.getLong( "monto" ) );
		if( simulacionParsed.has( "tipo" ) ) simulacion.setTipo( simulacionParsed.getString( "tipo" ) );
		if( simulacionParsed.has( "nombre" ) ) simulacion.setNombre( simulacionParsed.getString( "nombre" ) );
		if( simulacionParsed.has( "cuotas" ) ) simulacion.setCuotas( simulacionParsed.getInt( "cuotas" ) );
		simulacion.setRut( cliente.getRut() );
		//Tasa base por monto y valor seguros
		if( simulacion.getMonto() < 100001 ) {
			simulacion.setTasa( 7 );
			simulacion.setSeguros( (long) 14000 );
		} else if( simulacion.getMonto() < 1000001 ) {
			simulacion.setTasa( 9 );
			simulacion.setSeguros( (long) 17650 );
		} else if( simulacion.getMonto() < 10000001 ) {
			simulacion.setTasa( 11 );
			simulacion.setSeguros( (long) 23640 );
		}
		//Tasa modificada por cuotas
		if( simulacion.getCuotas() < 13 ) {
			simulacion.setTasa( simulacion.getTasa() + 1 );
		} else if( simulacion.getCuotas() < 25 ) {
			simulacion.setTasa( simulacion.getTasa() + 3 );
		} else if( simulacion.getCuotas() < 37 ) {
			simulacion.setTasa( simulacion.getTasa() + 5 );
		}
		simulacion.calcular();
		cliente.addSimulacion( simulacion );
		linkSimulaciones.save( simulacion );
		return simulacion;
	}
	
	@RequestMapping( value="/{id_cliente}/creditos/{id_credito}", method=RequestMethod.GET )
	public Credito getCredito( @PathVariable Long id_cliente, @PathVariable Long id_credito ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new Credito();
		Cliente cliente = result.get();
		Optional<Credito> resultCredito = linkCreditos.findById( id_credito );
		if( ! resultCredito.isPresent() ) return new Credito();
		Credito credito = resultCredito.get();
		if( ! cliente.getCreditos().contains( credito ) ) return new Credito();
		return credito;
	}
	
	@RequestMapping( value="/{id_cliente}/creditos/{id_credito}/cuotas", method=RequestMethod.GET )
	public List<Cuota> getCuotasCredito( @PathVariable Long id_cliente, @PathVariable Long id_credito ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new ArrayList<Cuota>();
		Cliente cliente = result.get();
		Optional<Credito> resultCredito = linkCreditos.findById( id_credito );
		if( ! resultCredito.isPresent() ) return new ArrayList<Cuota>();
		Credito credito = resultCredito.get();
		if( ! cliente.getCreditos().contains( credito ) ) return new ArrayList<Cuota>();
		return credito.getCuotas();
	}
	
	@RequestMapping( value="/{id_cliente}/creditos/{id_credito}/cuotas/{id_cuota}", method=RequestMethod.GET )
	public Cuota getCuotaCredito( @PathVariable Long id_cliente, @PathVariable Long id_credito, @PathVariable Long id_cuota ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new Cuota();
		Cliente cliente = result.get();
		Optional<Credito> resultCredito = linkCreditos.findById( id_credito );
		if( ! resultCredito.isPresent() ) return new Cuota();
		Credito credito = resultCredito.get();
		if( ! cliente.getCreditos().contains( credito ) ) return new Cuota();
		Optional<Cuota> resultCuota = linkCuotas.findById( id_cuota );
		if( ! resultCuota.isPresent() ) return new Cuota();
		Cuota cuota = resultCuota.get();
		if( ! credito.getCuotas().contains( cuota ) ) return new Cuota();
		return cuota;
	}

	@RequestMapping( value="/{id_cliente}/creditos/{id_credito}/cuotas/{id_cuota}", method=RequestMethod.PATCH )
	public Cuota pagarCuota( @PathVariable Long id_cliente, @PathVariable Long id_credito, @PathVariable Long id_cuota, @RequestBody Cuota cuotaJSON ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new Cuota();
		Cliente cliente = result.get();
		Optional<Credito> resultCredito = linkCreditos.findById( id_credito );
		if( ! resultCredito.isPresent() ) return new Cuota();
		Credito credito = resultCredito.get();
		if( ! cliente.getCreditos().contains( credito ) ) return new Cuota();
		Optional<Cuota> resultCuota = linkCuotas.findById( id_cuota );
		if( ! resultCuota.isPresent() ) return new Cuota();
		Cuota cuota = resultCuota.get();
		if( ! credito.getCuotas().contains( cuota ) ) return new Cuota();
		JSONObject cuotaParsed = new JSONObject( cuotaJSON );
		System.out.print( cuotaJSON );
		if( ! cuotaParsed.has( "pago" ) ) return new Cuota();
		System.out.print( "Parsear JSON" );
		System.out.print( "\n" );
		cuota.setPago( cuotaParsed.getString( "pago" ) );
		linkCuotas.save( cuota );
		return cuota;
	}
	
	@RequestMapping( value="/{id_cliente}/creditos", method=RequestMethod.POST )
	public Credito addCredito( @PathVariable Long id_cliente, @RequestBody String datos ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new Credito();
		Cliente cliente = result.get();
		JSONObject datosParsed = new JSONObject( datos );
		Credito credito = new Credito();
		Simulacion simulacion;
		if( datosParsed.has( "simulacion" ) ) {
			Optional<Simulacion> resultSimulacion = linkSimulaciones.findById( datosParsed.getLong( "simulacion" ) );
			if( ! resultSimulacion.isPresent() ) return new Credito();
			simulacion = resultSimulacion.get();
			if( ! cliente.getSimulaciones().contains( simulacion ) ) return credito;
		} else {
			simulacion = new Simulacion();
			if( ! datosParsed.has( "nombre" ) ) return credito;
			if( ! datosParsed.has( "monto" ) ) return credito;
			if( ! datosParsed.has( "tasa" ) ) return credito;
			if( ! datosParsed.has( "tipo" ) ) return credito;
			if( ! datosParsed.has( "seguros" ) ) return credito;
			if( ! datosParsed.has( "cuotas" ) ) return credito;
			simulacion.setNombre( datosParsed.getString( "nombre" ) );
			simulacion.setMonto( datosParsed.getLong( "monto" ) );
			simulacion.setTasa( datosParsed.getLong( "tasa" ) );
			simulacion.setTipo( datosParsed.getString( "tipo" ) );
			simulacion.setSeguros( datosParsed.getLong( "seguros" ) );
			simulacion.setCuotas( datosParsed.getInt( "cuotas" ) );
			simulacion.calcular();
		}
		credito.setRut( cliente.getRut() ); 
		credito.setNombre( simulacion.getNombre() );
		credito.setMonto( simulacion.getMonto() );
		credito.setTasa( simulacion.getTasa() );
		credito.setTipo( simulacion.getTipo() );
		credito.setSeguros( simulacion.getSeguros() );
		cliente.addCredito( credito );
		linkCreditos.save( credito );
		int totalCuotas = 0;
		if( datosParsed.has( "usar_cuotas" ) ) totalCuotas = simulacion.getCuotas();
		else totalCuotas = 12;
		for( int i = 1; i <= totalCuotas; i++ ) {
			Cuota cuota = new Cuota();
			DateFormat formatter = new SimpleDateFormat( "yyyy-MM-03");
			Calendar now = Calendar.getInstance();
			now.add( Calendar.MONTH, i );
			String fecha = formatter.format( now.getTime() );
			cuota.setMonto( simulacion.getMontoCuota() );
			cuota.setVencimiento( fecha );
			credito.addCuotas( cuota );
			linkCuotas.save( cuota );
		}
		linkCreditos.save( credito );
		return credito;
	}
	
	@RequestMapping( value="/{id_cliente}/cuotas", method=RequestMethod.GET ) 
	public List<Cuota> getCuotasCliente( @PathVariable Long id_cliente ) {
		Optional<Cliente> result = link.findById( id_cliente );
		if( ! result.isPresent() ) return new ArrayList<Cuota>();
		Cliente cliente = result.get();
		List<Cuota> cuotas = new ArrayList<Cuota>();
		cliente.getCreditos().forEach( (credito)-> {
			if( cuotas.size() > 2 ) return;
			credito.getCuotas().forEach( (cuota)-> {
				if( cuotas.size() > 2 ) return;
				if( cuota.getPago().isEmpty() ) cuotas.add( cuota ); 
			});
		});
		return cuotas;
	}
	
}


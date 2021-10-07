package cl.ciisa.frameworks.simuladordecreditos.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.ciisa.frameworks.simuladordecreditos.models.Liquidacion;
import cl.ciisa.frameworks.simuladordecreditos.models.Cliente;
import cl.ciisa.frameworks.simuladordecreditos.models.Credito;
import cl.ciisa.frameworks.simuladordecreditos.models.Cuota;
import cl.ciisa.frameworks.simuladordecreditos.repository.CreditoRepository;
import cl.ciisa.frameworks.simuladordecreditos.repository.CuotaRepository;
import cl.ciisa.frameworks.simuladordecreditos.repository.LiquidacionRepository;
import cl.ciisa.frameworks.simuladordecreditos.repository.ClienteRepository;

@RestController
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


	@RequestMapping( value="/", method=RequestMethod.POST )
	public Cliente newCliente( @RequestBody Cliente clienteJSON ) {
		System.out.print( clienteJSON );
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
		cliente.setNivel( 1 );
		link.save( cliente );

		return cliente;
	}
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<Cliente> getClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		for( Cliente cliente : link.findAll() ) {
			clientes.add( cliente );
		}
		return clientes;
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public Cliente getCliente( @PathVariable Long id ) {
		Optional<Cliente> result = link.findById( id );
		if( ! result.isPresent() ) return new Cliente();
		if( result.get().getNivel() < 1 ) return new Cliente();
		Cliente cliente = result.get();
		return cliente;
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.PATCH )
	public Cliente updCliente( @PathVariable Long id, @RequestBody Cliente clienteJSON ) {
		if( id == 1 ) return new Cliente();
		Optional<Cliente> result = link.findById( id );
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
		cliente.setNivel( 1 );
		link.save( cliente );
		return cliente;
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.DELETE )
	public Cliente delCliente( @PathVariable Long id ) {
		link.deleteById( id );
		return new Cliente();
	}
	@RequestMapping( value="/{id}/liquidaciones", method=RequestMethod.GET ) //liquidaciones de una persona
	public List<Liquidacion> getLiquidaciones( @PathVariable Long id ) {
		Optional<Cliente> result = link.findById( id );
		if( ! result.isPresent() ) return new ArrayList<Liquidacion>();
		Cliente cliente = result.get();
		return cliente.getLiquidaciones();
	}
	
	@RequestMapping( value="/{id}/liquidaciones/{id_liquidacion}", method=RequestMethod.GET ) //liquidacion especifica de una persona
	public Liquidacion getLiquidacion( @PathVariable Long id, @PathVariable Long id_liquidacion ) {
		Optional<Cliente> result = link.findById( id );
		if( ! result.isPresent() ) return new Liquidacion();
		Cliente cliente = result.get();
		Optional<Liquidacion> resultLiquidacion = linkLiquidaciones.findById( id_liquidacion );
		if( ! resultLiquidacion.isPresent() ) return new Liquidacion();
		Liquidacion liquidacion = resultLiquidacion.get();
		if( ! liquidacion.getRut().equals( cliente.getRut() ) ) return new Liquidacion();
		return liquidacion;
	}

	@RequestMapping( value="/{id}/liquidaciones", method=RequestMethod.POST ) //subir liquidaciones de una persona
	public Liquidacion addLiquidacion( @PathVariable Long id, @RequestBody Liquidacion liquidacionJSON ) {
		Optional<Cliente> result = link.findById( id );
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
	
	@RequestMapping( value="/{id}/liquidaciones/{id_liquidacion}", method=RequestMethod.DELETE )
	public Liquidacion delLiquidacion( @PathVariable Long id, @PathVariable Long id_liquidacion ) {
		linkLiquidaciones.deleteById( id_liquidacion );
		return new Liquidacion();
	}
	
	@RequestMapping( value="/{id}/creditos", method=RequestMethod.GET ) 
	public List<Credito> getCreditos( @PathVariable Long id ) {
		Optional<Cliente> result = link.findById( id );
		if( ! result.isPresent() ) return new ArrayList<Credito>();
		return result.get().getCreditos();
	}
	
	@RequestMapping( value="/{id}/cuotas", method=RequestMethod.GET ) 
	public List<Cuota> getCuotasCliente( @PathVariable Long id ) {
		Optional<Cliente> result = link.findById( id );
		if( ! result.isPresent() ) return new ArrayList<Cuota>();
		List<Cuota> cuotas = new ArrayList<Cuota>();
		//ver como filtrar las cuotas para que sean las proximas por pagar
		return cuotas;
	}
 	/*
	@RequestMapping( value="/{id}/creditos", method=RequestMethod.POST ) //tomar un nuevo credito en base a datos adjuntos
	@RequestMapping( value="/{id}/creditos/simular", method=RequestMethod.POST ) //simular un nuevo credito en base a datos adjuntos
	@RequestMapping( value="/{id}/creditos/{credito}", method=RequestMethod.GET ) //credito especifico
	@RequestMapping( value="/{id}/creditos/{credito}/cuotas", method=RequestMethod.GET ) //cuotas de un credito
	@RequestMapping( value="/{id}/creditos/{credito}/cuotas/{cuota}", method=RequestMethod.GET ) //cuota especifica de un credito
	@RequestMapping( value="/{id}/creditos/{credito}/cuotas/{cuota}", method=RequestMethod.PATCH ) //pagar cuota especifica de un credito
	//cuotas por vencer
	 *
	 */
	
}


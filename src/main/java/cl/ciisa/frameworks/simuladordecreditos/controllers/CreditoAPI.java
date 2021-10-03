package cl.ciisa.frameworks.simuladordecreditos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.ciisa.frameworks.simuladordecreditos.models.Credito;
import cl.ciisa.frameworks.simuladordecreditos.models.Cuota;
import cl.ciisa.frameworks.simuladordecreditos.repository.CreditoRepository;
import cl.ciisa.frameworks.simuladordecreditos.repository.CuotaRepository;
import cl.ciisa.frameworks.simuladordecreditos.repository.TipoCreditoRepository;

import java.util.Optional;

@RestController
@RequestMapping( "/API/creditos" )
public class CreditoAPI {
	@Autowired
	CreditoRepository link;
	@Autowired
	CuotaRepository linkCuotas;
	@Autowired
	TipoCreditoRepository linkTipos;
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<Credito> getCreditos() {
		List<Credito> creditos = new ArrayList<Credito>();
		for( Credito credito : link.findAll() ) {
			creditos.add( credito );
		}
		return creditos;
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public Credito getCredito( @PathVariable Long id ) {
		Optional<Credito> result = link.findById( id );
		if( ! result.isPresent() ) return new Credito();
		return result.get();
	}
	
	@RequestMapping( value="/{id}/cuotas", method=RequestMethod.GET )
	public List<Cuota> getCuotasCredito( @PathVariable Long id ) {
		Optional<Credito> result = link.findById( id );
		if( ! result.isPresent() ) return new ArrayList<Cuota>();
		Credito credito = result.get();
		List<Cuota> cuotas = linkCuotas.getByCredito( credito.getNombre() );
		return cuotas; 
	}

	/*
	@RequestMapping( value="/, RequestMethod.POST ) //crear nuevo credito en base a datos adjuntos
	@RequestMapping( value="/{id}", method=RequestMethod.PATCH )
	@RequestMapping( value="/{id}", method=RequestMethod.DELETE )
	@RequestMapping( value="/simular, RequestMethod.POST ) //proponer nuevo credito en base a datos adjuntos
	 */
}

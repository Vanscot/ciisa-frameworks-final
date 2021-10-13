package cl.ciisa.frameworks.simuladordecreditos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.ciisa.frameworks.simuladordecreditos.models.Credito;
import cl.ciisa.frameworks.simuladordecreditos.models.Cuota;
import cl.ciisa.frameworks.simuladordecreditos.repository.CreditoRepository;

import java.util.Optional;

@RestController
@Validated
@RequestMapping( "/API/creditos" )
public class CreditoAPI {
	@Autowired
	CreditoRepository link;
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<Credito> getCreditos() {
		List<Credito> creditos = new ArrayList<Credito>();
		for( Credito credito : link.findAll() ) {
			creditos.add( credito );
		}
		return creditos;
	}
	
	@RequestMapping( value="/{id_credito}", method=RequestMethod.GET )
	public Credito getCredito( @PathVariable Long id_credito ) {
		Optional<Credito> result = link.findById( id_credito );
		if( ! result.isPresent() ) return new Credito();
		return result.get();
	}
	
	@RequestMapping( value="/{id_credito}/cuotas", method=RequestMethod.GET )
	public List<Cuota> getCuotasCredito( @PathVariable Long id_credito ) {
		Optional<Credito> result = link.findById( id_credito );
		if( ! result.isPresent() ) return new ArrayList<Cuota>();
		return result.get().getCuotas(); 
	}

	@RequestMapping( value="/{id_credito}", method=RequestMethod.DELETE )
	public Credito delCredito( @PathVariable Long id_credito ) {
		Optional<Credito> result = link.findById( id_credito );
		if( ! result.isPresent() ) return new Credito();
		link.deleteById( id_credito );
		return new Credito();
	}

}

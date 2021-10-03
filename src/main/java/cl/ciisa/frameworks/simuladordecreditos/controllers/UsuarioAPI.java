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

import cl.ciisa.frameworks.simuladordecreditos.models.Usuario;
import cl.ciisa.frameworks.simuladordecreditos.repository.UsuarioRepository;

@RestController
@RequestMapping( "/API/usuarios" )
public class UsuarioAPI {

	@Autowired
	UsuarioRepository link;

	private void addAdmin() {
		Usuario usuario = new Usuario();
		usuario.setNombre( "Admin" );
		usuario.setApellido1( "" );
		usuario.setApellido2( "" );
		usuario.setCalle( "" );
		usuario.setComuna( "" );
		usuario.setFechaNacimiento( "" );
		usuario.setGenero( "" );
		usuario.setPassword( "holasoyadmin" );
		usuario.setEmail( "admin@localhost" );
		usuario.setNivel( 2 );
		link.save( usuario );
	}
	
	@RequestMapping( value="/", method=RequestMethod.POST )
	public Usuario newUsuario( @RequestBody Usuario usuarioJSON ) {
		Optional<Usuario> result = link.findById( (long) 1 );
		if( ! result.isPresent() ) addAdmin(); //No hay usuarios, creamos el admin
		System.out.print( usuarioJSON );
		Usuario usuario = new Usuario();
		JSONObject usuarioParsed = new JSONObject( usuarioJSON );
		if( usuarioParsed.has( "rut" ) ) usuario.setRut( usuarioParsed.getLong( "rut" ) );
		if( usuarioParsed.has( "nombre" ) ) usuario.setNombre( usuarioParsed.getString( "nombre" ) );
		if( usuarioParsed.has( "apellido1" ) ) usuario.setApellido1( usuarioParsed.getString( "apellido1" ) );
		if( usuarioParsed.has( "apellido2" ) ) usuario.setApellido2( usuarioParsed.getString( "apellido2" ) );
		if( usuarioParsed.has( "calle" ) ) usuario.setCalle( usuarioParsed.getString( "calle" ) );
		if( usuarioParsed.has( "comuna" ) ) usuario.setComuna( usuarioParsed.getString( "comuna" ) );
		if( usuarioParsed.has( "fechaNacimiento" ) ) usuario.setFechaNacimiento( usuarioParsed.getString( "fechaNacimiento" ) );
		if( usuarioParsed.has( "genero" ) ) usuario.setGenero( usuarioParsed.getString( "genero" ) );
		if( usuarioParsed.has( "password" ) ) usuario.setPassword( usuarioParsed.getString( "password" ) );
		if( usuarioParsed.has( "email" ) ) usuario.setEmail( usuarioParsed.getString( "email" ) );
		usuario.setNivel( 1 );
		link.save( usuario );

		return usuario;
	}
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for( Usuario usuario : link.findAll() ) {
			if( usuario.getNivel() < 1 ) continue;
			if( usuario.getId() == 1 ) usuario.setPassword( "" );
			usuarios.add( usuario );
		}
		if( usuarios.size() < 1 ) {
			addAdmin();//No hay usuarios, creamos el admin
			Optional<Usuario> result = link.findById( (long) 1 );
			Usuario admin = result.get();
			admin.setPassword( "" );
			usuarios.add( admin );
		}
		
		return usuarios;
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.GET )
	public Usuario getUsuario( @PathVariable Long id ) {
		Optional<Usuario> result = link.findById( id );
		if( ! result.isPresent() ) return new Usuario();
		if( result.get().getNivel() < 1 ) return new Usuario();
		Usuario usuario = result.get();
		if( usuario.getId() == 1 ) usuario.setPassword( "" );
		return usuario;
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.PATCH )
	public Usuario updUsuario( @PathVariable Long id, @RequestBody Usuario usuarioJSON ) {
		if( id == 1 ) return new Usuario();
		Optional<Usuario> result = link.findById( id );
		if( ! result.isPresent() ) return new Usuario();
		Usuario usuario = result.get();
		JSONObject usuarioParsed = new JSONObject( usuarioJSON );
		if( usuarioParsed.has( "rut" ) ) usuario.setRut( usuarioParsed.getLong( "rut" ) );
		if( usuarioParsed.has( "nombre" ) ) usuario.setNombre( usuarioParsed.getString( "nombre" ) );
		if( usuarioParsed.has( "apellido1" ) ) usuario.setApellido1( usuarioParsed.getString( "apellido1" ) );
		if( usuarioParsed.has( "apellido2" ) ) usuario.setApellido2( usuarioParsed.getString( "apellido2" ) );
		if( usuarioParsed.has( "calle" ) ) usuario.setCalle( usuarioParsed.getString( "calle" ) );
		if( usuarioParsed.has( "comuna" ) ) usuario.setComuna( usuarioParsed.getString( "comuna" ) );
		if( usuarioParsed.has( "fechaNacimiento" ) ) usuario.setFechaNacimiento( usuarioParsed.getString( "fechaNacimiento" ) );
		if( usuarioParsed.has( "genero" ) ) usuario.setGenero( usuarioParsed.getString( "genero" ) );
		if( usuarioParsed.has( "password" ) ) usuario.setPassword( usuarioParsed.getString( "password" ) );
		if( usuarioParsed.has( "email" ) ) usuario.setEmail( usuarioParsed.getString( "email" ) );
		usuario.setNivel( 1 );
		link.save( usuario );
		return usuario;
	}
	
	@RequestMapping( value="/{id}", method=RequestMethod.DELETE )
	public Usuario delUsuario( @PathVariable Long id ) {
		if( id == 1 ) return new Usuario();
		Optional<Usuario> result = link.findById( id );
		if( ! result.isPresent() ) return new Usuario();
		Usuario usuario = result.get();
		usuario.setNivel( 0 );
		link.save( usuario );
		return new Usuario();
	}

	/*
	@RequestMapping( value="/{id}/liquidaciones, RequestMethod.GET ) //liquidaciones de una persona
	@RequestMapping( value="/{id}/liquidaciones, RequestMethod.POST ) //subir liquidaciones de una persona
	@RequestMapping( value="/{id}/liquidaciones/{liquidacion}, RequestMethod.GET ) //liquidacion especifica de una persona
	@RequestMapping( value="/{id}/liquidaciones/{liquidacion}, RequestMethod.DELETE ) //liquidacion especifica de una persona
	@RequestMapping( value="/{id}/creditos, RequestMethod.GET ) //creditos de una persona
	@RequestMapping( value="/{id}/creditos, RequestMethod.POST ) //tomar un nuevo credito en base a datos adjuntos
	@RequestMapping( value="/{id}/creditos/simular, RequestMethod.POST ) //simular un nuevo credito en base a datos adjuntos
	@RequestMapping( value="/{id}/creditos/{credito}, RequestMethod.GET ) //credito especifico
	@RequestMapping( value="/{id}/creditos/{credito}/cuotas, RequestMethod.GET ) //cuotas de un credito
	@RequestMapping( value="/{id}/creditos/{credito}/cuotas/{cuota}, RequestMethod.GET ) //cuota especifica de un credito
	@RequestMapping( value="/{id}/creditos/{credito}/cuotas/{cuota}, RequestMethod.PATCH ) //pagar cuota especifica de un credito
	@RequestMapping( value="/{id}/cuotas, RequestMethod.GET ) //cuotas por vencer
	 *
	 */
}


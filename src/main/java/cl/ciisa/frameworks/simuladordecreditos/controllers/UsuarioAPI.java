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

	@RequestMapping( value="/", method=RequestMethod.GET )
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for( Usuario usuario : link.findAll() ) {
			if( usuario.getNivel() < 1 ) continue;
			if( usuario.getId() == 1 ) usuario.setPassword( "" );
			usuarios.add( usuario );
		}
		
		return usuarios;
	}

	@RequestMapping( value="/usuario/{id}", method=RequestMethod.GET )
	public Usuario getUsuario( @PathVariable Long id ) {
		Optional<Usuario> result = link.findById( id );
		if( ! result.isPresent() ) return new Usuario();
		if( result.get().getNivel() < 1 ) return new Usuario();
		Usuario usuario = result.get();
		if( usuario.getId() == 1 ) usuario.setPassword( "" );
		return usuario;
	}
	
	@RequestMapping( value="/usuario/{id}", method=RequestMethod.PATCH )
	public Usuario updUsuario( @PathVariable Long id, @RequestBody Usuario usuarioJSON ) {
		if( id == 1 ) return new Usuario();
		Optional<Usuario> result = link.findById( id );
		if( ! result.isPresent() ) return new Usuario();
		Usuario usuario = result.get();
		JSONObject usuarioParsed = new JSONObject( usuarioJSON );
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
	
	@RequestMapping( value="/usuario", method=RequestMethod.POST )
	public Usuario newUsuario( @RequestBody Usuario usuarioJSON ) {
		System.out.print( usuarioJSON );
		Usuario usuario = new Usuario();
		JSONObject usuarioParsed = new JSONObject( usuarioJSON );
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
	
	@RequestMapping( value="/usuario/{id}", method=RequestMethod.DELETE )
	public Usuario delUsuario( @PathVariable Long id ) {
		if( id == 1 ) return new Usuario();
		Optional<Usuario> result = link.findById( id );
		if( ! result.isPresent() ) return new Usuario();
		Usuario usuario = result.get();
		usuario.setNivel( 0 );
		link.save( usuario );
		return new Usuario();
	}
}


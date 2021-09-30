package cl.ciisa.frameworks.simuladordecreditos.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.ciisa.frameworks.simuladordecreditos.models.Usuario;
import cl.ciisa.frameworks.simuladordecreditos.repository.UsuarioRepository;

@Controller
@RequestMapping( value="/GUI/usuarios" )
public class UsuarioGUI {

	@Autowired
	UsuarioRepository link;
	
	@RequestMapping( value="/", method=RequestMethod.GET )
	public String getUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for( Usuario usuario : link.findAll() ) {
			if( usuario.getNivel() < 1 ) continue;
			if( usuario.getId() == 1 ) usuario.setPassword( "" );
			usuarios.add( usuario );
		}
		
		return "/GUI/usuarios/index";
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
	/*
	@RequestMapping( value="/usuarios/usuario/{id}", method=RequestMethod.POST )
	public Usuario updUsuario( @PathVariable Long id, @RequestBody Usuario usuarioJSON ) {
		Usuario found = new Usuario();
		for( Usuario usuarioActual : this.list ) {
			if( usuarioActual.getId() != id ) continue;
			JSONObject usuarioParsed = new JSONObject( usuarioJSON );

			if( usuarioParsed.has( "nombre" ) ) usuarioActual.setNombre( usuarioParsed.getString( "nombre" ) );
			if( usuarioParsed.has( "apellido1" ) ) usuarioActual.setApellido1( usuarioParsed.getString( "apellido1" ) );
			if( usuarioParsed.has( "apellido2" ) ) usuarioActual.setApellido2( usuarioParsed.getString( "apellido2" ) );
			if( usuarioParsed.has( "calle" ) ) usuarioActual.setCalle( usuarioParsed.getString( "calle" ) );
			if( usuarioParsed.has( "comuna" ) ) usuarioActual.setComuna( usuarioParsed.getString( "comuna" ) );
			if( usuarioParsed.has( "fechaNacimiento" ) ) usuarioActual.setFechaNacimiento( usuarioParsed.getString( "fechaNacimiento" ) );
			if( usuarioParsed.has( "genero" ) ) usuarioActual.setGenero( usuarioParsed.getString( "genero" ) );
			if( usuarioParsed.has( "password" ) ) usuarioActual.setPassword( usuarioParsed.getString( "password" ) );
			if( usuarioParsed.has( "email" ) ) usuarioActual.setEmail( usuarioParsed.getString( "email" ) );
			usuarioActual.setNivel( 1 );
			found = usuarioActual;
		}
		return found;
	}
	
	@RequestMapping( value="/usuarios/usuario", method=RequestMethod.GET )
	public Usuario newUsuario() {
		System.out.print( "nuevo usuario" );
		Usuario usuario = new Usuario();
		return usuario;
	}
	
	@RequestMapping( value="/usuarios/usuario", method=RequestMethod.POST )
	public Usuario newUsuario( @RequestBody Usuario usuarioJSON ) {
		System.out.print( usuarioJSON );
		Usuario usuario = new Usuario();
		JSONObject usuarioParsed = new JSONObject( usuarioJSON );
		
		if( usuarioParsed.has( "nombre" ) ) usuarioJSON.setNombre( usuarioParsed.getString( "nombre" ) );
		if( usuarioParsed.has( "apellido1" ) ) usuarioJSON.setApellido1( usuarioParsed.getString( "apellido1" ) );
		if( usuarioParsed.has( "apellido2" ) ) usuarioJSON.setApellido2( usuarioParsed.getString( "apellido2" ) );
		if( usuarioParsed.has( "calle" ) ) usuarioJSON.setCalle( usuarioParsed.getString( "calle" ) );
		if( usuarioParsed.has( "comuna" ) ) usuarioJSON.setComuna( usuarioParsed.getString( "comuna" ) );
		if( usuarioParsed.has( "fechaNacimiento" ) ) usuarioJSON.setFechaNacimiento( usuarioParsed.getString( "fechaNacimiento" ) );
		if( usuarioParsed.has( "genero" ) ) usuarioJSON.setGenero( usuarioParsed.getString( "genero" ) );
		if( usuarioParsed.has( "password" ) ) usuarioJSON.setPassword( usuarioParsed.getString( "password" ) );
		if( usuarioParsed.has( "email" ) ) usuarioJSON.setEmail( usuarioParsed.getString( "email" ) );
		usuario.setNivel( 1 );
		this.list.add( usuario );
		usuario.setId( (long) this.list.size() );
		
		return usuario;
	}
	
	@RequestMapping( value="/usuarios/usuario/{id}", method=RequestMethod.DELETE )
	public Usuario delUsuario( @PathVariable Long id ) {
		Usuario usuario = new Usuario();
		for( Usuario usuarioActual : this.list ) {
			if( usuarioActual.getId() != id ) continue;
			usuarioActual.setNivel( 0 );
			usuario = usuarioActual;
		}
		return usuario;
	}
	*/
}


package cl.ciisa.frameworks.simuladordecreditos.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${server.error.path:${error.path:/error}}"})

public class ErrorController extends AbstractErrorController {
	public ErrorController( ErrorAttributes errorAttributes ) {
		super(errorAttributes);
	}

	@RequestMapping
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		HttpStatus status = this.getStatus(request);
		Map<String, Object> errorCustomAttribute = new HashMap<>();
		errorCustomAttribute.put( "mensaje", "Los campos enviados no son correctos" );
		errorCustomAttribute.put( "estado", status.name() );
		return new ResponseEntity( errorCustomAttribute, status );
	}
	
	public String getErrorPath() {
		return "/error";
	}

}

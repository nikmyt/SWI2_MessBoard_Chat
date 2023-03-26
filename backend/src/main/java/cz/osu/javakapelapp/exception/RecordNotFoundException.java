package cz.osu.javakapelapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;

public class RecordNotFoundException extends ResponseStatusException {

	//it was protected?
	public RecordNotFoundException(String statusText) {
		super(HttpStatus.NOT_FOUND, statusText);
	}

}

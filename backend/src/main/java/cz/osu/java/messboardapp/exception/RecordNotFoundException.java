package cz.osu.java.messboardapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RecordNotFoundException extends ResponseStatusException {

	//it was protected?
	public RecordNotFoundException(String statusText) {
		super(HttpStatus.NOT_FOUND, statusText);
	}

}

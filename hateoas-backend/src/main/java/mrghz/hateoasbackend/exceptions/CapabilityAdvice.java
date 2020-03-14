package mrghz.hateoasbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CapabilityAdvice {

	@ResponseBody
	@ExceptionHandler(CapabilityException.class)
	public final ResponseEntity<CapabilityNotFoundResponse> capabilityNotFoundResponseResponseEntity(
			CapabilityException exc) {
		CapabilityNotFoundResponse response = new CapabilityNotFoundResponse(exc.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}

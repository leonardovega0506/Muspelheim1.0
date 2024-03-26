package mx.com.ananda.midgard.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleNotFound(HttpClientErrorException.NotFound ex) {
        // Aquí puedes personalizar el mensaje de error que deseas devolver
        String errorMessage = "Error de response: Código: " + ex.getRawStatusCode() + "\nMensaje: " + ex.getStatusText();
        return ResponseEntity.status(ex.getRawStatusCode()).body(errorMessage);
    }
}

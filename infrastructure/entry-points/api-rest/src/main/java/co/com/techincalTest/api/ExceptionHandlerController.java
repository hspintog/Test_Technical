package co.com.techincalTest.api;


import co.com.techincalTest.model.DNAStructureLengthException;
import co.com.techincalTest.model.InvalidCaractersDNAException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler( DNAStructureLengthException.class )
    @ResponseStatus(  HttpStatus.INTERNAL_SERVER_ERROR )
    public String handleAccessDeniedException( DNAStructureLengthException exception) {
        return exception.getErroMessage();
    }

    @ExceptionHandler( InvalidCaractersDNAException.class )
    @ResponseStatus(  HttpStatus.INTERNAL_SERVER_ERROR )
    public String handleAccessDeniedException( InvalidCaractersDNAException exception) {
        return exception.getErroMessage();
    }
}

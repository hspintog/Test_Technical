package co.com.techincalTest.usecase.mutant;

import co.com.techincalTest.model.DNAStructureLengthException;
import co.com.techincalTest.model.InvalidCaractersDNAException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DNAException {
    private static final Logger logger = LogManager.getLogger(DNAException.class);

    @ExceptionHandler(DNAStructureLengthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDNAStructureException(DNAStructureLengthException exc) {
        return exc.getMessage();
    }

    @ExceptionHandler(InvalidCaractersDNAException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidCaractersException(InvalidCaractersDNAException exc) {
        return exc.getMessage();
    }
}

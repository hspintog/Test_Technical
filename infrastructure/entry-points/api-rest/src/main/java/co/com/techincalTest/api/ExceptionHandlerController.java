package co.com.techincalTest.api;


import co.com.techincalTest.model.InternalErrorException;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerController {
    private static final Logger logger = LogManager.getLogger(ExceptionHandlerController.class);
    private Gson gson = new Gson();

    @ExceptionHandler( InternalErrorException.class )
    @ResponseStatus(  HttpStatus.INTERNAL_SERVER_ERROR )
    public String handleAccessDeniedException( InternalErrorException accessDeniedException,
                                                      WebRequest request ) {


        logger.info("Error response: {}");

        return "Error";
    }
}

package co.com.techincalTest.api;
import co.com.techincalTest.model.DNAStructureLengthException;
import co.com.techincalTest.model.InvalidCaractersDNAException;
import co.com.techincalTest.model.SequenceDna;
import co.com.techincalTest.usecase.mutant.MutantUseCase;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/mutant", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private static final Logger logger = LogManager.getLogger(ApiRest.class);
    private final String className = getClass().getName();
    private Gson gson = new Gson();


    private final MutantUseCase mutantUseCase;

    @PostMapping(path = "/valid")
    public ResponseEntity<Boolean> isMutant(@RequestBody  SequenceDna dna) throws DNAStructureLengthException, InvalidCaractersDNAException {
        logger.info(dna);
        try{
            logger.info("Start {}->isMutant ", className);
            logger.info("Request dnaSequence: {} ", gson.toJson(dna));
            boolean isMutant = mutantUseCase.isMutantDNA(dna);
            logger.info("Response Boolean: {} ", isMutant);
            logger.info("End {}->isMutant ", className);
            return new ResponseEntity<>( isMutant, HttpStatus.OK );
        }catch(Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>( false, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }


    @GetMapping(path = "/status")
    public ResponseEntity status(@Context HttpServletRequest request) {
        return new ResponseEntity<>(HttpStatus.OK.value(), HttpStatus.OK);
    }


}

package co.com.techincalTest.api;

import co.com.techincalTest.model.Stats;
import co.com.techincalTest.usecase.mutant.GuardadoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class StatsController {

    private final GuardadoUseCase saveDB;

    @GetMapping(path = "/")
    public ResponseEntity<Stats> stats()  {
        Stats respose = saveDB.read();
        return new ResponseEntity<>(respose, HttpStatus.OK);
    }
}

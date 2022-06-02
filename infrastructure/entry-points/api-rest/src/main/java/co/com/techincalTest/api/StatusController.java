package co.com.techincalTest.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@RestController
@RequestMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class StatusController {

    @GetMapping(path = "/")
    public ResponseEntity status(@Context HttpServletRequest request) {
        return new ResponseEntity<>(HttpStatus.OK.value(), HttpStatus.OK);
    }
}

package co.com.techincalTest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PosicionesMutante {
    char[][] dna;
    private int sequenceToMudant;
    private int countSequencesMatchMutant;
    private int matchs;
}

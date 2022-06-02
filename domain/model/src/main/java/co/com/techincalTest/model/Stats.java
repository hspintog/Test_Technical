package co.com.techincalTest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Stats {
    private int count_mutant_dna;
    private int count_human_dna;
    private BigDecimal ratio;

}

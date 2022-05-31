package co.com.techincalTest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**Return Result sequence DNA and is mutant
 *
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Mutant {

    private String[] dna;
    private boolean isMutant;

}

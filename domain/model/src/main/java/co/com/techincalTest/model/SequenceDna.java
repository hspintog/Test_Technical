package co.com.techincalTest.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**Model to sequence DNA
 *
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SequenceDna {
    @NotNull
    private String[] dna;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dna == null) ? 0 : dna.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SequenceDna)) return false;
        SequenceDna that = (SequenceDna) o;
        return Arrays.equals(getDna(), that.getDna());
    }
}

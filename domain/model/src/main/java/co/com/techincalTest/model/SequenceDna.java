package co.com.techincalTest.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

/**Model to sequence DNA
 *
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SequenceDna {
    @NotNull
    private List<String> dna;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dna == null) ? 0 : dna.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SequenceDna other = (SequenceDna) obj;
        if (dna == null) {
            if (other.dna != null)
                return false;
        } else if (!CollectionUtils.isEqualCollection(dna, other.dna))
            return false;
        return true;
    }
}

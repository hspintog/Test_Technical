package co.com.techincalTest.model.gateways;

import co.com.techincalTest.model.Mutant;
import co.com.techincalTest.model.Stats;


public interface MutantRepository {
    void create(Mutant mutant);

    Stats read();

}

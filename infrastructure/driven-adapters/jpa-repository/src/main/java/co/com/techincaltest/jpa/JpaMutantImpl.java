package co.com.techincaltest.jpa;

import co.com.techincalTest.model.Mutant;
import co.com.techincalTest.model.Stats;
import co.com.techincalTest.model.gateways.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
public class JpaMutantImpl implements MutantRepository {


    private IMutantRepository IMutantRepository;

    @Autowired
    public JpaMutantImpl(IMutantRepository IMutantRepository) {
        this.IMutantRepository = IMutantRepository;
    }

    public void create(Mutant mutant) {
        try{
            String id = UUID.randomUUID().toString();
            mutant.setId(id);
            IMutantRepository.save(mutant.getId(), mutant.getDna(), mutant.isMutant());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public Stats read() {
        int countHuman = IMutantRepository.findByCountMutantHuman(false);
        int countMutant = IMutantRepository.findByCountMutantHuman(true);
        return Stats.builder()
                .count_human_dna(countHuman)
                .count_mutant_dna(countMutant)
                .build();
    }

}

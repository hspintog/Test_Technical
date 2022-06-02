package co.com.techincalTest.usecase.mutant;

import co.com.techincalTest.model.Mutant;
import co.com.techincalTest.model.Stats;
import co.com.techincalTest.model.gateways.MutantRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@RequiredArgsConstructor
public class GuardadoUseCase {


    private  MutantRepository mutantRepository;

    @Autowired
    public GuardadoUseCase(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    public void create(Mutant mutant) {
        mutantRepository.create(mutant);
    }

    public Stats read() {
        Stats stats = mutantRepository.read();
        BigDecimal ratio = BigDecimal.ZERO;
        if(stats.getCount_mutant_dna() != 0){
            if(stats.getCount_human_dna() == 0){
                ratio = BigDecimal.ONE;
            }else{
                BigDecimal mutant = BigDecimal.valueOf(stats.getCount_mutant_dna());
                BigDecimal human = BigDecimal.valueOf(stats.getCount_human_dna());
                ratio = mutant.divide(human, 2, RoundingMode.HALF_UP);
            }
        }
        stats.setRatio(ratio);
        return stats;
    }
}

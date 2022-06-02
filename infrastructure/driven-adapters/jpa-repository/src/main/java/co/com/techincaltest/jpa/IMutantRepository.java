package co.com.techincaltest.jpa;

import co.com.techincaltest.jpa.entity.MutanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface IMutantRepository extends JpaRepository<MutanteEntity, String> {
    @Transactional
    @Modifying
    @Query(value = "insert into mutante( id, dna, isMutant) "
            + "values( ?1, ?2, ?3)", nativeQuery = true)
    int save(String id, String dna, boolean IsMutant );


    @Query(value = "SELECT COUNT(dna)  FROM mutante " +
            " WHERE isMutant = ?1", nativeQuery = true)
    int findByCountMutantHuman(boolean mutant);

}

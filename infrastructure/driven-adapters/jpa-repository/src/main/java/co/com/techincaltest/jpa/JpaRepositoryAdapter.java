package co.com.techincaltest.jpa;

import co.com.techincalTest.model.Mutant;
import co.com.techincaltest.jpa.entity.MutanteEntity;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRepositoryAdapter extends AdapterOperations<
        Mutant,
        MutanteEntity,
        String,
        IMutantRepository>
{

    public JpaRepositoryAdapter(IMutantRepository repository, ObjectMapper mapper) {
        /**
         * Could be use mapper.mapBuilder if your domain model implement builder pattern
         * super(repository, mapper, d ->
         * mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build()); Or using mapper.map with
         * the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Mutant.class));
    }
}

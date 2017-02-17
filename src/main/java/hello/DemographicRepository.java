package hello;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.DemographicInfo;

@Repository
public interface DemographicRepository extends CrudRepository<DemographicInfo, Long> {

}
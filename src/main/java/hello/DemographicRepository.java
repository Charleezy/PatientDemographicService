package hello;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.DemographicInfo;

@Repository
public interface DemographicRepository extends CrudRepository<DemographicInfo, Long> {
	DemographicInfo findByIdentificationDocument(long identificationDocument);

	List<DemographicInfo> findByFirstName(String string);
	List<DemographicInfo> findByLastName(String string);
	List<DemographicInfo> findByDob(String string);
	List<DemographicInfo> findByAddress(String string);
}
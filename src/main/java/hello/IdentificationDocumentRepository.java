package hello;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.IdentificationDocument;

@Repository
public interface IdentificationDocumentRepository extends CrudRepository<IdentificationDocument, Long> {

	//TODO: remove
//    List<IdentificationDocument> findByLastName(String lastName);
}
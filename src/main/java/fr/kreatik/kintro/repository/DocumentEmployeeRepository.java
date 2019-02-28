package fr.kreatik.kintro.repository;

import fr.kreatik.kintro.domain.DocumentEmployee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the DocumentEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentEmployeeRepository extends MongoRepository<DocumentEmployee, String> {

}

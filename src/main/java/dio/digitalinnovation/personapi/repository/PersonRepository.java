package dio.digitalinnovation.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dio.digitalinnovation.personapi.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}

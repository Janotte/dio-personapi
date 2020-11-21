package dio.digitalinnovation.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dio.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import dio.digitalinnovation.personapi.entity.Person;
import dio.digitalinnovation.personapi.repository.PersonRepository;

@Service
public class PersonService {
	
	PersonRepository personRepository;
	
	@Autowired	
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public MessageResponseDTO createPerson(@RequestBody Person person) {
		Person savedPerson = personRepository.save(person);
		return MessageResponseDTO
				.builder()
				.message("Created  person with id: " + savedPerson.getId())
				.build();
	}

}

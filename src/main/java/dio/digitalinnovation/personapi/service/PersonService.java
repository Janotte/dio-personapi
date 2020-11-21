package dio.digitalinnovation.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.digitalinnovation.personapi.dto.request.PersonDTO;
import dio.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import dio.digitalinnovation.personapi.entity.Person;
import dio.digitalinnovation.personapi.mapper.PersonMapper;
import dio.digitalinnovation.personapi.repository.PersonRepository;

@Service
public class PersonService {

	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);
		
		Person savedPerson = personRepository.save(personToSave);
		return MessageResponseDTO
				.builder()
				.message("Created  person with id: " + savedPerson.getId())
				.build();
	}

}

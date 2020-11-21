package dio.digitalinnovation.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.digitalinnovation.personapi.dto.request.PersonDTO;
import dio.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import dio.digitalinnovation.personapi.entity.Person;
import dio.digitalinnovation.personapi.exception.PersonNotFoundException;
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
		return MessageResponseDTO.builder().message("Created  person with id: " + savedPerson.getId()).build();
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();

		return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));

		return personMapper.toDTO(person);
	}

	public void delete(Long id) throws PersonNotFoundException {
		personRepository.findById(id)
			.orElseThrow(() -> new PersonNotFoundException(id));
		personRepository.deleteById(id);
	}

}

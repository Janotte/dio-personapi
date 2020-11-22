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
		
		return createMessageResponse(savedPerson.getId(), "Created person with ID: ");
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();

		return allPeople
				.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));

		return personMapper.toDTO(person);
	}

	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		
		personRepository.deleteById(id);
	}

	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);
		
		Person personToUpdate = personMapper.toModel(personDTO);
		
		Person updatedPerson = personRepository.save(personToUpdate);
		return createMessageResponse(updatedPerson.getId(), "Updated person with ID: ");
	}

	private void verifyIfExists(Long id) throws PersonNotFoundException {
		personRepository.findById(id)
			.orElseThrow(() -> new PersonNotFoundException(id));
	}

	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO
				.builder()
				.message(message + id)
				.build();
	}

}

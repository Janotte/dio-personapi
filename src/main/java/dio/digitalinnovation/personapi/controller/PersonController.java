package dio.digitalinnovation.personapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dio.digitalinnovation.personapi.dto.request.PersonDTO;
import dio.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import dio.digitalinnovation.personapi.exception.PersonNotFoundException;
import dio.digitalinnovation.personapi.service.PersonService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

	PersonService personService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
		return personService.createPerson(personDTO);
	}

	@GetMapping
	public List<PersonDTO> listAllPeople() {
		return personService.listAll();
	}

	@GetMapping("/{id}")
	public PersonDTO findPersonById(@PathVariable Long id) throws PersonNotFoundException {
		return personService.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePersonById(@PathVariable Long id) throws PersonNotFoundException {
		personService.delete(id);
	}

	@PutMapping("/{id}")
	public MessageResponseDTO updatePersonById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO)
			throws PersonNotFoundException {
		return personService.updateById(id, personDTO);
	}

}

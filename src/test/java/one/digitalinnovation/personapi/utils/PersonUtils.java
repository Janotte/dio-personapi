package one.digitalinnovation.personapi.utils;

import java.time.LocalDate;
import java.util.Collections;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;

public class PersonUtils {

	private static final String FIRST_NAME = "Sandro";
	private static final String LAST_NAME = "Janotte";
	private static final String CPF_NUMBER = "904.229.500-76";
	private static final Long PERSON_ID = 1L;
	private static final LocalDate BIRTH_DATE = LocalDate.of(1969, 06, 17);

	public static PersonDTO createFakeDTO() {
		return PersonDTO
				.builder()
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate("17-06-1969")
				.phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
				.build();
	}

	public static Person createFakeEntity() {
		return Person
				.builder()
				.id(PERSON_ID)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate(BIRTH_DATE)
				.phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
				.build();
	}

	public static String asJsonString(PersonDTO personDTO) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objectMapper.registerModules(new JavaTimeModule());

			return objectMapper.writeValueAsString(personDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

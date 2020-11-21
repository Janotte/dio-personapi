package dio.digitalinnovation.personapi.dto;

import java.util.List;

import dio.digitalinnovation.personapi.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

	private Long id;

	private String cpf;

	private String firstName;

	private String lastName;

	private String birthDate;

	private List<Phone> phones;

}

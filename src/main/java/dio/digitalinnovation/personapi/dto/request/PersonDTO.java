package dio.digitalinnovation.personapi.dto.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

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

	@NotEmpty
	@CPF
	private String cpf;

	@NotBlank
	@Size(min = 2, max = 100)
	private String firstName;

	@NotBlank
	@Size(min = 2, max = 100)
	private String lastName;

	private String birthDate;

	@Valid
	@NotEmpty
	private List<Phone> phones;

}

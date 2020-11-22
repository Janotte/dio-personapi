package one.digitalinnovation.personapi.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.PhoneType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PhoneType type;
	
	@NotBlank
	@Size(min = 10, max = 20)
	private String number;

}

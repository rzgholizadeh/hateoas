package mrghz.hateoasbackend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// Lombok handles all setter/getter equals and hash and no args constructor
@Data
@NoArgsConstructor
public class Capability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Technology Stack cannot be blank")
	@NotNull(message = "Technology Stack cannot be null")
	private String techStack;
	private Integer numOfDevelopers = 0;
	private Integer numOfAvailableDevelopers = 0;

	public Capability(String techStack, Integer numOfDevelopers, Integer numOfAvailableDevelopers) {
		super();
		this.techStack = techStack;
		this.numOfDevelopers = numOfDevelopers;
		this.numOfAvailableDevelopers = numOfAvailableDevelopers;
	}

}

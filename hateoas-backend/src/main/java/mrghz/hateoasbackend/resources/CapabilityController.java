package mrghz.hateoasbackend.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrghz.hateoasbackend.domain.Capability;
import mrghz.hateoasbackend.services.CapabilityService;

@RestController
@RequestMapping("/dashboard")
//for talking with React
@CrossOrigin
public class CapabilityController {

	private CapabilityService capabilityService;

	public CapabilityController(CapabilityService capabilityService) {
		this.capabilityService = capabilityService;
	}

	@GetMapping
	public CollectionModel<EntityModel<Capability>> getAllCapabilities() {
		List<EntityModel<Capability>> capabilities = capabilityService.getAllCapabilities().stream()
				.map(capability -> new EntityModel<>(capability,
						linkTo(methodOn(CapabilityController.class)
								.getCapability(capability.getId())).withRel("getThisCapability"),
						linkTo(methodOn(CapabilityController.class).getAllCapabilities())
								.withRel("getAllCapability")))
				.collect(Collectors.toList());
		return new CollectionModel<>(capabilities);
	}

	@GetMapping("/{id}")
	public EntityModel<?> getCapability(@PathVariable Long id) {
		Capability capability = capabilityService.findCapabilityById(id);
		return new EntityModel<>(capability,
				linkTo(methodOn(CapabilityController.class).getCapability(id))
						.withRel("getThisCapability"),
				linkTo(methodOn(CapabilityController.class).getAllCapabilities())
						.withRel("getAllCapability"));
	}

	@PostMapping
	public Object createCapability(@Valid @RequestBody Capability capability,
			BindingResult result) {
		if (result.hasErrors()) {
			return capabilityService.errorMap(result);
		}
		Capability newCapability = capabilityService.saveCapability(capability);
		return new EntityModel<>(newCapability,
				linkTo(methodOn(CapabilityController.class).getCapability(newCapability.getId()))
						.withRel("getThisCapability"),
				linkTo(methodOn(CapabilityController.class).getAllCapabilities())
						.withRel("getAllCapability"));
	}
}

package mrghz.hateoasbackend.resources;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrghz.hateoasbackend.assembler.CapabilityRepresentationModelAssembler;
import mrghz.hateoasbackend.domain.Capability;
import mrghz.hateoasbackend.services.CapabilityService;

@RestController
@RequestMapping("/dashboard")
//for talking with React
@CrossOrigin
public class CapabilityController {

	private CapabilityService capabilityService;
	private CapabilityRepresentationModelAssembler assembler;

	public CapabilityController(CapabilityService capabilityService, CapabilityRepresentationModelAssembler assembler) {
		this.capabilityService = capabilityService;
		this.assembler = assembler;
	}

	@GetMapping
	public CollectionModel<EntityModel<Capability>> getAllCapabilities() {
		return new CollectionModel<>(
				capabilityService.getAllCapabilities().stream().map(capability -> assembler.toModel(capability))
						.collect(Collectors.toList()),
				new Link("http://localhost:8080/dashboard").withRel("createCapability"));
	}

	@GetMapping("/{id}")
	public EntityModel<?> getCapability(@PathVariable Long id) {
		return assembler.toModel(capabilityService.findCapabilityById(id));
	}

	@PostMapping
	public Object createCapability(@Valid @RequestBody Capability capability, BindingResult result) {
		if (result.hasErrors()) {
			return capabilityService.errorMap(result);
		}
		return assembler.toModel(capabilityService.saveCapability(capability));
	}

	@PutMapping("/{id}")
	public Object updateCapability(@PathVariable Long id, @Valid @RequestBody Capability capability,
			BindingResult result) {
		if (result.hasErrors()) {
			return capabilityService.errorMap(result);
		}
		return assembler.toModel(capabilityService.updateCapability(id, capability));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCapability(@PathVariable Long id) {
		capabilityService.deleteCapability(id);
		return new ResponseEntity<String>("Capability Deleted", HttpStatus.OK);
	}

}

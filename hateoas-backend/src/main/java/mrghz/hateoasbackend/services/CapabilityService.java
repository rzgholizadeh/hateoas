package mrghz.hateoasbackend.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import mrghz.hateoasbackend.domain.Capability;
import mrghz.hateoasbackend.exceptions.CapabilityException;
import mrghz.hateoasbackend.repositories.CapabilityRepository;

@Service
public class CapabilityService {

	private CapabilityRepository capabilityRepository;

	public CapabilityService(CapabilityRepository capabilityRepository) {
		this.capabilityRepository = capabilityRepository;
	}

	public List<Capability> getAllCapabilities() {
		return capabilityRepository.findAll();
	}

	public Capability findCapabilityById(Long id) {
		return capabilityRepository.findById(id).orElseThrow(
				() -> new CapabilityException("Capability with ID: " + id + " Not found"));
	}

	public Capability saveCapability(Capability capability) {
		return capabilityRepository.save(capability);
	}

	public ResponseEntity<?> errorMap(BindingResult result) {
		var errorMap = new HashMap<>();
		for (FieldError error : result.getFieldErrors()) {
			errorMap.put(error.getField(), error.getDefaultMessage());
		}
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}

	public Capability updateCapability(Long id, Capability capability) {
		return capabilityRepository.findById(id).map(cap -> {
			cap.setTechStack(capability.getTechStack());
			cap.setNumOfDevelopers(capability.getNumOfDevelopers());
			cap.setNumOfAvailableDevelopers(capability.getNumOfAvailableDevelopers());
			return capabilityRepository.save(cap);
		}).orElseGet(() -> {
			return capabilityRepository.save(capability);
		});
	}

	public void deleteCapability(Long id) {
		capabilityRepository.delete(capabilityRepository.findById(id).orElseThrow(
				() -> new CapabilityException("Capability with ID: " + id + " Not found")));
	}
}

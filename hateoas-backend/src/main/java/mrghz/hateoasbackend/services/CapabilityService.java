package mrghz.hateoasbackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

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
}

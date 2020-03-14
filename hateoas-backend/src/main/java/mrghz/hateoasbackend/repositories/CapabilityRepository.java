package mrghz.hateoasbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mrghz.hateoasbackend.domain.Capability;

public interface CapabilityRepository extends JpaRepository<Capability, Long> {
	
	
}

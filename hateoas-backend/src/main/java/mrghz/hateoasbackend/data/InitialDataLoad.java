package mrghz.hateoasbackend.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mrghz.hateoasbackend.domain.Capability;
import mrghz.hateoasbackend.repositories.CapabilityRepository;

@Configuration
public class InitialDataLoad {

	// This is the initial data that we want every time the system is restarted.
	// This is for using H2 only. Because the data gets wiped out
	@Bean
	CommandLineRunner LoadDB(CapabilityRepository capabilityRepository) {
		return args -> { // lambda expression
			capabilityRepository.save(new Capability("Java", 100, 50));
			capabilityRepository.save(new Capability("ReactJS", 70, 20));
			capabilityRepository.save(new Capability("Python", 200, 100));

		};
	}
}

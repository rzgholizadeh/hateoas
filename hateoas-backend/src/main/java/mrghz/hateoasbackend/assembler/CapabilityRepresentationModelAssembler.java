package mrghz.hateoasbackend.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;

import mrghz.hateoasbackend.domain.Capability;
import mrghz.hateoasbackend.resources.CapabilityController;

@Component
public class CapabilityRepresentationModelAssembler implements SimpleRepresentationModelAssembler<Capability> {

	@Override
	public EntityModel<Capability> toModel(Capability entity) {
		return new EntityModel<>(entity,
				linkTo(methodOn(CapabilityController.class).getCapability(entity.getId())).withRel("getThisCapability"),
				linkTo(methodOn(CapabilityController.class).deleteCapability(entity.getId()))
						.withRel("deleteThisCapability"),
				linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("getAllCapability"),
				new Link("http://localhost:8080/dashboard/" + entity.getId()).withRel("updateThisCapability"));
	}

	@Override
	public void addLinks(EntityModel<Capability> resource) {

	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Capability>> resources) {

	}

}

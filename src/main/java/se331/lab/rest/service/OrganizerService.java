package se331.lab.rest.service;

import se331.lab.rest.entity.Organizer;

import java.util.List;
import java.util.Optional;

public interface OrganizerService {
    List<Organizer> getAllOrganizer();
    Optional<Organizer> getOrganizerById(Long id);
    Organizer save(Organizer organizer);
}

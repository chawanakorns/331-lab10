package se331.lab.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import se331.lab.rest.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByTitleContainingOrDescriptionContaining(String title, String description, Pageable pageable);
    Page<Event> findByTitleContainingAndDescriptionContaining(String title, String description, Pageable pageable);
    Page<Event> findByTitleContainingOrDescriptionContainingOrOrganizer_NameContaining(String title, String description, String organizerName, Pageable pageable);
}

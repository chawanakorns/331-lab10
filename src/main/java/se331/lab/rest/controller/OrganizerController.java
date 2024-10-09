package se331.lab.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.lab.rest.entity.Organizer;
import se331.lab.rest.entity.OrganizerDTO;
import se331.lab.rest.service.OrganizerService;
import se331.lab.rest.util.LabMapper;

@RestController
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerService organizerService;

    @GetMapping("/organizers")
    ResponseEntity<?> getOrganizers() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(organizerService.getAllOrganizer()));
    }

    @GetMapping("/organizers/{id}")
    ResponseEntity<OrganizerDTO> getOrganizerById(@PathVariable Long id) {
        return organizerService.getOrganizerById(id)
                .map(organizer -> ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(organizer)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/organizers")
    public ResponseEntity<?> addOrganizer(@RequestBody Organizer organizer){
        Organizer savedOrganizer = organizerService.save(organizer);
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(savedOrganizer));
    }
}

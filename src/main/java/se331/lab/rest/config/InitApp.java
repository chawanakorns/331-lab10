package se331.lab.rest.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organizer;
import se331.lab.rest.entity.Participant;
import se331.lab.rest.repository.EventRepository;
import se331.lab.rest.repository.OrganizerRepository;
import se331.lab.rest.repository.ParticipantRepository;

@Component
@RequiredArgsConstructor
@Profile("db")
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // Save Organizers
        Organizer org1, org2, org3;
        org1 = organizerRepository.save(Organizer.builder().name("CAMT").build());
        org2 = organizerRepository.save(Organizer.builder().name("CMU").build());
        org3 = organizerRepository.save(Organizer.builder().name("ChiangMai").build());

        // Save Events
        Event tempEvent1 = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petAllowed(false)
                .build());
        tempEvent1.setOrganizer(org1);
        org1.getOwnEvents().add(tempEvent1);

        Event tempEvent2 = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("8.00am-4.00 pm.")
                .petAllowed(false)
                .build());
        tempEvent2.setOrganizer(org1);
        org1.getOwnEvents().add(tempEvent2);

        Event tempEvent3 = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petAllowed(false)
                .build());
        tempEvent3.setOrganizer(org2);
        org2.getOwnEvents().add(tempEvent3);

        Event tempEvent4 = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petAllowed(true)
                .build());
        tempEvent4.setOrganizer(org3);
        org3.getOwnEvents().add(tempEvent4);

        Participant participant1 = participantRepository.save(Participant.builder().name("John Doe").telNo("123-456-7890").build());
        Participant participant2 = participantRepository.save(Participant.builder().name("Jane Smith").telNo("098-765-4321").build());
        Participant participant3 = participantRepository.save(Participant.builder().name("Alice Brown").telNo("456-123-7890").build());
        Participant participant4 = participantRepository.save(Participant.builder().name("Bob Johnson").telNo("321-654-0987").build());
        Participant participant5 = participantRepository.save(Participant.builder().name("Charlie Davis").telNo("789-012-3456").build());

        tempEvent1.getParticipants().add(participant1);
        tempEvent1.getParticipants().add(participant2);
        tempEvent1.getParticipants().add(participant3);

        tempEvent2.getParticipants().add(participant1);
        tempEvent2.getParticipants().add(participant4);
        tempEvent2.getParticipants().add(participant5);

        tempEvent3.getParticipants().add(participant2);
        tempEvent3.getParticipants().add(participant3);
        tempEvent3.getParticipants().add(participant4);

        tempEvent4.getParticipants().add(participant1);
        tempEvent4.getParticipants().add(participant3);
        tempEvent4.getParticipants().add(participant5);

        // Save Events with Participants
        eventRepository.save(tempEvent1);
        eventRepository.save(tempEvent2);
        eventRepository.save(tempEvent3);
        eventRepository.save(tempEvent4);
    }
}

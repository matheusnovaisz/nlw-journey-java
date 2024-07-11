package com.matheusnovaisz.planner.participant;

import com.matheusnovaisz.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public void registerParticipantsToTrip(Trip trip, List<String> emails_to_invite){
        // Register participants to event
        List<Participant> participants = emails_to_invite.stream().map(email -> new Participant(email, trip)).toList();

        participantRepository.saveAll(participants);
    }

    public void triggerConfirmationEmailToParticipants(UUID tripId){
        // Trigger confirmation email
    }

    public void triggerConfirmationEmailToParticipant(String participantEmail){
        // Trigger confirmation email
    }

    public List<ParticipantData> getAllParticipantsFromTrip(UUID tripId){
        return participantRepository.findByTripId(tripId).stream()
                .map(participant ->
                        new ParticipantData(participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed()))
                .toList();
    }
}

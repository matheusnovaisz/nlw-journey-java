package com.matheusnovaisz.planner.participant;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    public void registerParticipantsToTrip(UUID trip, List<String> participants){
        // Register participants to event
    }

    public void triggerConfirmationEmailToParticipants(UUID trip, List<String> participants){
        // Trigger confirmation email
    }
}

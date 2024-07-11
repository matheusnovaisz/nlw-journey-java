package com.matheusnovaisz.planner.trip;

import com.matheusnovaisz.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController{

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private TripRepository repository;

    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody TripRequestPayload tripPayload){
        Trip newTrip = new Trip(tripPayload);

        this.repository.save(newTrip);

        this.participantService.registerParticipantsToTrip(newTrip.getId(), tripPayload.emails_to_invite());
        return ResponseEntity.ok(new TripCreateResponse(newTrip.getId()));
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID tripId){
        Optional<Trip> trip = this.repository.findById(tripId);

        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

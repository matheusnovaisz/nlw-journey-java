package com.matheusnovaisz.planner.trip;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Table(name = "trips")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String destination;

    @Column(name="starts_at", nullable = false)
    private LocalDateTime startsAt;

    @Column(name="ends_at", nullable = false)
    private LocalDateTime endsAt;

    @Column(name="is_confirmed", nullable = false)
    private Boolean isConfirmed;

    @Column(name="owner_name", nullable = false)
    private String ownerName;

    @Column(name="owner_email", nullable = false)
    private String ownerEmail;

    public Trip(TripRequestPayload tripPayload){
        this.destination = tripPayload.destination();
        this.startsAt = LocalDateTime.parse(tripPayload.starts_at(), DateTimeFormatter.ISO_DATE_TIME);
        this.endsAt = LocalDateTime.parse(tripPayload.ends_at(), DateTimeFormatter.ISO_DATE_TIME);
        this.ownerName = tripPayload.owner_name();
        this.ownerEmail = tripPayload.owner_email();
        this.isConfirmed = false;
    }
}

package com.matheusnovaisz.planner.link;

import com.matheusnovaisz.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    public LinkResponse registerLink(LinkRequestPayload linkRequestPayload, Trip trip) {
        Link link = new Link(linkRequestPayload.title(), linkRequestPayload.url(), trip);
        this.linkRepository.save(link);
        return new LinkResponse(link.getId());
    }

    public List<LinkData> getLinksByTrip(UUID trip) {
        return this.linkRepository.findByTripId(trip).stream().map(link -> new LinkData(link.getTitle(), link.getUrl(), link.getId())).toList();
    }
}

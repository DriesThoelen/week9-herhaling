package be.pxl.ja.ticketsystem.mapper;

import be.pxl.ja.ticketsystem.Venue;

public class VenueMapper implements Mapper<Venue> {
    @Override
    public Venue map(String[] lines) {
        return new Venue(lines[1], lines[2], lines[3], lines[4], Integer.parseInt(lines[5]));
    }
}

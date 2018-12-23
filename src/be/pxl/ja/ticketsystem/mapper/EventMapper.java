package be.pxl.ja.ticketsystem.mapper;

import be.pxl.ja.ticketsystem.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EventMapper implements Mapper<Event> {
    @Override
    public Event map(String[] lines) {
        return new Event(lines[2], LocalDateTime.parse(lines[1], DateTimeFormatter.ofPattern("ddMMuuuuHHmm")), lines[3], Double.parseDouble(lines[4]), lines[5]);
    }
}

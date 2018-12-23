package be.pxl.ja.ticketsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Event {
    private static int count = 0;

    private String name;
    private LocalDateTime time;
    private String description;
    private double price;
    private String id;
    private ArrayList<User> attendees;
    private Venue venue;

    {
        count++;
    }

    public Event(String name, LocalDateTime time, String description, double price, String venueId) {
        this.name = name;
        this.time = time;
        this.description = description;
        this.price = price;
        this.attendees = new ArrayList<>();
        this.venue = TicketSystem.findVenueById(venueId);
        this.id = generateId();
    }

    private String generateId() {
        StringBuilder builder = new StringBuilder(this.name.substring(0, 3).toUpperCase());
        builder.append("-");
        builder.append(String.format("%05d", count));
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addAttendee(User user) throws EventBookedFullException {
        if (this.venue.getCapacity() > attendees.size()) {
            attendees.add(user);
        }
        else
        {
            throw new EventBookedFullException("Adding user to event failed. This event is already booked full.");
        }
    }

    public ArrayList<User> getAttendees() {
        return attendees;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Venue getVenue() {
        return venue;
    }

    public boolean isEventBookedFull() {
        if (this.venue.getCapacity() == attendees.size()) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Event nr. : " + id + "\n"
                + "Name : " + name + "\n"
                + "Time : " + time.format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")) + "\n"
                + "Description : " + description + "\n"
                + "Price : " + price + "\n"
                + "Attendees : " + attendees + "\n"
                + venue;
    }
}

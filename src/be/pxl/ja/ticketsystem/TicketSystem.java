package be.pxl.ja.ticketsystem;

import be.pxl.ja.ticketsystem.mapper.EventMapper;
import be.pxl.ja.ticketsystem.mapper.Mapper;
import be.pxl.ja.ticketsystem.mapper.UserMapper;
import be.pxl.ja.ticketsystem.mapper.VenueMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketSystem {
    public static List<Event> events;
    public static List<User> users;
    public static List<Venue> venues;
    private QueueService queueService;

    public TicketSystem() {
        loadUsers();
        loadVenues();
        loadEvents();
        queueService = new QueueService(events);
    }

    public void loadEvents() {
        events = loadData(Paths.get("data/eventdata.txt"), new EventMapper());
    }

    public void loadUsers() {
        users = loadData(Paths.get("data/userdata.txt"), new UserMapper());
    }

    public void loadVenues() {
        venues = loadData(Paths.get("data/venuedata.txt"), new VenueMapper());
    }

    public void addEvent(Event event) {
        events.add(event);
        queueService.makeNewQueue(event.getId());
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addVenue(Venue venue) {
        venues.add(venue);
    }

    public void requestTicket(Event event, User user) {
        queueService.addToQueue(event.getId(), user);
    }

    public void viewNext(String eventId) {
        System.out.println(queueService.getNextInLine(eventId).toString());
    }

    public void assignTickets(String eventID, int number) {
        for (int userNumber = 0; userNumber < number; userNumber++) {
            User user = queueService.getNextInLine(eventID);
            queueService.removeFromQueue(eventID, user);
            for (Event event:events) {
                if (event.getId().equals(eventID)) {
                    try {
                        event.addAttendee(user);
                        printTicket(event, user);
                    }
                    catch (EventBookedFullException ebfe) {
                        System.err.println(ebfe.getMessage());
                    }
                }
            }
        }
    }

    public static Venue findVenueById(String venueId) {
        Optional<Venue> optionalVenue = venues.stream().filter(v -> v.getId().equalsIgnoreCase(venueId)).findAny();
        if (optionalVenue.isPresent()) {
            return optionalVenue.get();
        }
        return null;
    }

    public void findAndShowEventByName(String name) {
        Optional<Event> optionalEvent = events.stream().filter(e -> e.getName().equals(name)).findAny();
        if (optionalEvent.isPresent()) {
            System.out.println(optionalEvent.get());
        }
    }

    public void showAllBookedFullEvents() {
        events.stream().filter(e -> e.isEventBookedFull()).forEach(e -> System.out.println(e.getName() + " " + e.getDescription() + " is booked full."));
    }

    public void showAllEventsForUser(User user) {
        events.stream().filter(e -> e.getAttendees().contains(user)).forEach(e -> System.out.println(e.getName()));
    }

    public void showVenuesWhereAtLeastOneEventIsBookedInOneWeek() {
        events.stream().filter(e -> e.getTime().isAfter(LocalDateTime.now()) && e.getTime().isBefore(LocalDateTime.now().plusDays(8))).filter(e -> e.getVenue() != null).forEach(e -> System.out.println(e.getVenue()));
    }

    public <T> List<T> loadData(Path path, Mapper<T> mapper) {
        List<T> data = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                data.add(mapper.map(line.split(";")));
            }
        } catch (IOException e) {
            System.out.println("data could not be loaded - " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }

    private void printTicket(Event event, User user) {
        String path = "out/" + event.getId() + "_" + user.getId() + ".txt";
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            writer.write(String.format("First Name : %s%n" +
                            "Last Name : %s%n" +
                            "Birthday : %s%n" +
                            "Event : %s%n" +
                            "Time : %s%n" +
                            "Description : %s%n" +
                            "Price : %.2f%n" +
                            "Venue : %s%n        %s%n        %s%n        %s%n",
                             user.getFirstname(), user.getLastname(), user.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                             event.getName(), event.getTime().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")), event.getDescription(), event.getPrice(),
                             event.getVenue().getName(), event.getVenue().getStreet(), event.getVenue().getZipCode(),
                             event.getVenue().getCity()));
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}

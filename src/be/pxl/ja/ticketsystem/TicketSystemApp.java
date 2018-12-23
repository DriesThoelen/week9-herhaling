package be.pxl.ja.ticketsystem;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TicketSystemApp {
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem();

        User user = new User("Thoelen", "Dries", LocalDate.of(1993, 6, 28));
        ticketSystem.addUser(user);
        Venue venue = new Venue("Vorst Nationaal", "Victor Rousseaulaan 208", "1190", "Vorst", 8000);
        ticketSystem.addVenue(venue);
        Event event = new Event( "Eros Ramazzotti", LocalDateTime.now().plusDays(5), "Vita Ce N'è World Tour", 79, "V-0005");
        ticketSystem.addEvent(event);

        ticketSystem.requestTicket(event, user);
        ticketSystem.assignTickets(event.getId(), 1);

        ticketSystem.findAndShowEventByName("Queens of the Stone Age");

        ticketSystem.requestTicket(ticketSystem.events.get(0), ticketSystem.users.get(0));
        ticketSystem.requestTicket(ticketSystem.events.get(1), ticketSystem.users.get(0));
        ticketSystem.requestTicket(ticketSystem.events.get(2), ticketSystem.users.get(0));
        ticketSystem.requestTicket(ticketSystem.events.get(3), ticketSystem.users.get(0));

        ticketSystem.requestTicket(ticketSystem.events.get(0), ticketSystem.users.get(1));
        ticketSystem.requestTicket(ticketSystem.events.get(1), ticketSystem.users.get(1));
        ticketSystem.requestTicket(ticketSystem.events.get(2), ticketSystem.users.get(1));
        ticketSystem.requestTicket(ticketSystem.events.get(3), ticketSystem.users.get(1));

        ticketSystem.requestTicket(ticketSystem.events.get(0), ticketSystem.users.get(2));
        ticketSystem.requestTicket(ticketSystem.events.get(1), ticketSystem.users.get(2));
        ticketSystem.requestTicket(ticketSystem.events.get(2), ticketSystem.users.get(2));
        ticketSystem.requestTicket(ticketSystem.events.get(3), ticketSystem.users.get(2));

        ticketSystem.requestTicket(ticketSystem.events.get(0), ticketSystem.users.get(3));
        ticketSystem.requestTicket(ticketSystem.events.get(1), ticketSystem.users.get(3));
        ticketSystem.requestTicket(ticketSystem.events.get(2), ticketSystem.users.get(3));
        ticketSystem.requestTicket(ticketSystem.events.get(3), ticketSystem.users.get(3));

        ticketSystem.requestTicket(ticketSystem.events.get(0), ticketSystem.users.get(4));
        ticketSystem.requestTicket(ticketSystem.events.get(1), ticketSystem.users.get(4));
        ticketSystem.requestTicket(ticketSystem.events.get(2), ticketSystem.users.get(4));
        ticketSystem.requestTicket(ticketSystem.events.get(3), ticketSystem.users.get(4));

        ticketSystem.assignTickets(ticketSystem.events.get(0).getId(), 4);
        ticketSystem.assignTickets(ticketSystem.events.get(1).getId(), 3);
        ticketSystem.assignTickets(ticketSystem.events.get(2).getId(), 2);
        ticketSystem.assignTickets(ticketSystem.events.get(3).getId(), 1);

        for (int i = 0; i < venue.getCapacity(); i++) {
            User u = new User(i + "", "user", LocalDate.of(1970, 1, 1));
            try {
                event.addAttendee(u);
            } catch (EventBookedFullException ebfex) {
                ebfex.printStackTrace();
            }
        }

        ticketSystem.showAllBookedFullEvents();
        System.out.println();
        ticketSystem.showAllEventsForUser(ticketSystem.users.get(0));
        System.out.println();
        ticketSystem.showVenuesWhereAtLeastOneEventIsBookedInOneWeek();

    }
}

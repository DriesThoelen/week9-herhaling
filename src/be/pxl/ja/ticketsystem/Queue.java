package be.pxl.ja.ticketsystem;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private String eventID;
    private List<User> peopleInQueue;

    public Queue(String eventID) {
        this.eventID = eventID;
        this.peopleInQueue = new ArrayList<>();
    }

    public String getEventID() {
        return eventID;
    }

    public List<User> getPeopleInQueue() {
        return peopleInQueue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Queue EventId " + eventID);
        for (User user:peopleInQueue) {
            sb.append("\n");
            sb.append(user.toString());
        }
        return sb.toString();
    }
}

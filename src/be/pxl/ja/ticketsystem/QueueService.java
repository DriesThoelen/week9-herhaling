package be.pxl.ja.ticketsystem;

import java.util.ArrayList;
import java.util.List;

public class QueueService {
    private Queue queue;
    private List<Queue> queues;

    public QueueService(List<Event> events) {
        queues = new ArrayList<>();
        for (Event event:events) {
            queue = new Queue(event.getId());
            queues.add(queue);
        }
    }

    public void makeNewQueue(String eventId) {
        Queue queue = new Queue(eventId);
        queues.add(queue);
    }

    public void addToQueue(String eventId, User user) {
        getQueue(eventId).getPeopleInQueue().add(user);
    }

    public Queue getQueue(String eventId) {
        for (Queue queue:queues) {
            if (queue.getEventID().equals(eventId)) {
                return queue;
            }
        }

        return null;
    }

    public User getNextInLine(String eventId) {
        return getQueue(eventId).getPeopleInQueue().get(0);
    }

    public void removeFromQueue(String eventId, User user) {
        getQueue(eventId).getPeopleInQueue().remove(user);
    }

    public void printQueue(String eventId) {
        System.out.println(getQueue(eventId).toString());
    }

    public int getQueueSize(String eventId) {
        return getQueue(eventId).getPeopleInQueue().size();
    }
}

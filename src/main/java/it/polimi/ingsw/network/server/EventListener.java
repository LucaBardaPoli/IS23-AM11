package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ServerMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to implement an observer-observable pattern
 */
public class EventListener {
    private final List<Listener> listeners;

    /**
     * Class Constructor
     */
    public EventListener() {
        this.listeners = new ArrayList<>();
    }

    /**
     * Adds a listener
     * @param listener is the listener added to the list.
     */
    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    /**
     * Removes a specified listener from the list.
     * @param listener is the listener to remove from the list
     */
    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Notifies all the listener in the list.
     * @param serverMessage message received from the server.
     */
    public void notifyListeners(ServerMessage serverMessage) {
        for(Listener l : this.listeners) {
            l.notify(serverMessage);
        }
    }

    /**
     * Notifies all the listener in the list.
     * @param serverMessage message received from the server.
     * @param clientHandler clientHandler that notifies the client.
     */
    public void notifyListeners(ServerMessage serverMessage, ClientHandler clientHandler) {
        clientHandler.notify(serverMessage);
    }
}

package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ServerMessage;

import java.util.ArrayList;
import java.util.List;

public class EventListener {
    private final List<Listener> listeners;

    /**
     * Class Constructor
     */
    public EventListener() {
        this.listeners = new ArrayList<>();
    }

    /**
     * Getter method
     * @return the listeners
     */
    public List<Listener> getListeners() {
        return this.listeners;
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
     * @param listener
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

package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ServerMessage;

import java.util.ArrayList;
import java.util.List;

public class EventListener {
    private final List<Listener> listeners;

    public EventListener() {
        this.listeners = new ArrayList<>();
    }

    public List<Listener> getListeners() {
        return this.listeners;
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void notifyListeners(ServerMessage serverMessage) {
        for(Listener l : this.listeners) {
            l.notify(serverMessage);
        }
    }
}

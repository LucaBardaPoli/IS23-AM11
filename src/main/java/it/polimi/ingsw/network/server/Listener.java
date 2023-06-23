package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ServerMessage;

public interface Listener {
    /**
     * Notifies the listener if a message is received
     * @param serverMessage is a generic server Message
     */
    void notify(ServerMessage serverMessage);
}

package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ServerMessage;

public interface Listener {
    void notify(ServerMessage serverMessage);
}

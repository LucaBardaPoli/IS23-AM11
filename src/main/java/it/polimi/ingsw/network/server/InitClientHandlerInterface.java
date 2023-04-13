package it.polimi.ingsw.network.server;

import java.rmi.Remote;

public interface InitClientHandlerInterface extends Remote {
    public boolean isLobbyEmpty();
    public boolean login(String nickname, int numPlayer);
    public boolean login(String nickname);
}

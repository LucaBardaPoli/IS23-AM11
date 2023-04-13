package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.LobbyManager;

public class InitClientHandler implements InitClientHandlerInterface {
    private LobbyManager lobbyManager;

    public InitClientHandler() {
        this.lobbyManager = LobbyManager.getInstance();
    }

    public boolean isLobbyEmpty() {
        return lobbyManager.getLobby().isEmpty();
    }

    public boolean login(String nickname, int numPlayer) {
        if(nickname.equals("") || lobbyManager.isNicknameTaken(nickname) || numPlayer < 2 || numPlayer > 4) {
            return false;
        }
        lobbyManager.addPlayer(nickname, numPlayer);
        return true;
    }

    public boolean login(String nickname) {
        return login(nickname, 2);
    }
}

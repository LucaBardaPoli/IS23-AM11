package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.server.ClientHandler;

public class ChatMessage implements ClientMessage, ServerMessage {
    private final String player;
    private final String textMessage;

    public ChatMessage(String player, String textMessage) {
        this.player = player;
        this.textMessage = textMessage;
    }

    public String getPlayer() {
        return player;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }

    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}

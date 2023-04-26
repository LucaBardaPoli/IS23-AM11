package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.server.ClientHandler;

public class ChatMessage implements ClientMessage, ServerMessage {
    private final String player;
    private final String receiver;
    private final String textMessage;

    public ChatMessage(String player, String receiver, String textMessage) {
        this.player = player;
        this.receiver = receiver;
        this.textMessage = textMessage;
    }
    public ChatMessage(String player, String textMessage) {
        this(player, null, textMessage);
    }

    public String getPlayer() {
        return player;
    }

    public String getReceiver() {
        return receiver;
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

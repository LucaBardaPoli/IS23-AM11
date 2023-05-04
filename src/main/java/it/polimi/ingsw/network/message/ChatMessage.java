package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.server.ClientHandler;

/**
 *
 */
public class ChatMessage implements ClientMessage, ServerMessage {
    // se non esiste il player è mandato in bcast
    /**
     * player is the name of the client which sends the message. (If the name is invalid, then the message is sent in broadcast)
     */
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

    /**
     * Handles the current message headed to the server
     * @param clientHandler is a server-thread which handles the different messages
     */
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }

    /**
     * Handles the current message headed to the client
     * @param clientController is a client-thread which handles the different messages
     */
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}

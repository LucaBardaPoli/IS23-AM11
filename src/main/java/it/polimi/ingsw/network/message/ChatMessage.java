package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.server.ClientHandler;

public class ChatMessage implements ClientMessage, ServerMessage {
    private final String player;
    private final String receiver;
    private final String textMessage;

    /**
     * Class constructor
     * @param player name of the client who sends the message
     * @param receiver name of the client who the message is sent to (if not valid is sent to everyone)
     * @param textMessage chat message
     */
    public ChatMessage(String player, String receiver, String textMessage) {
        this.player = player;
        this.receiver = receiver;
        this.textMessage = textMessage;
    }

    /**
     * Player getter
     * @return player
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Receiver getter
     * @return receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Message getter
     * @return text message
     */
    public String getTextMessage() {
        return textMessage;
    }

    /**
     * Handles the current message headed to the server
     * @param clientHandler that handles the client client-side
     */
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }

    /**
     * Handles the current message headed to the client
     * @param clientController that handles the client server-side
     */
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}

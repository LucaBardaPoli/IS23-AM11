package it.polimi.ingsw.view.controller;

import it.polimi.ingsw.network.message.ChatMessage;
import it.polimi.ingsw.network.message.ConfirmOrderNotify;
import it.polimi.ingsw.network.message.ConfirmPickRequest;
import it.polimi.ingsw.view.GUIView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Gui view controller that handles some players actions during the game
 */
public class GameController {
    private final GUIView gui;
    @FXML
    TextArea chat;
    @FXML
    TextField message;
    @FXML
    ChoiceBox messageReceiver;
    @FXML
    GridPane board;
    @FXML
    Button pick;

    /**
     * Class constructor
     * @param gui view to handle
     */
    public GameController(GUIView gui) {
        this.gui = gui;
    }

    /**
     * Handles a chat message
     */
    public void handleChatMessage() {
        String receiver = (String) this.messageReceiver.getValue();
        receiver = receiver.equals("-") ? "Everyone" : receiver;
        String message = this.message.getText();
        if(!message.equals("")) {
            this.gui.getClientController().sendMessage(new ChatMessage(this.gui.getClientController().getClient().getNickname(), receiver, message));
            this.message.setText("");
            String chatMessage = "\nYou to " + receiver + "\n" + message + "\n";
            this.chat.appendText(chatMessage);
        }
    }

    /**
     * Prints a unicast message
     * @param sender sender of the message
     * @param message message sent
     */
    public void printChatMessageUnicast(String sender, String message) {
        String chatMessage = "\n" + sender + " to You\n" + message + "\n";
        this.chat.appendText(chatMessage);
    }

    /**
     * Prints a broadcast message
     * @param sender of the message
     * @param message message sent
     */
    public void printChatMessageBroadcast(String sender, String message) {
        String chatMessage = "\n" + sender + " to Everyone\n" + message + "\n";
        this.chat.appendText(chatMessage);
    }

    /**
     * Handles the pick of a tile from the board
     */
    public void handlePick() {this.gui.getClientController().sendMessage(new ConfirmPickRequest());}

    /**
     * Handles the selection of a bookshelf column
     */
    public void handleInsert() {
        if(this.gui.getSelectedColumn() != -1) {
            this.gui.getClientController().sendMessage(new ConfirmOrderNotify(this.gui.getPickedTiles()));
        }
    }
}

package it.polimi.ingsw.view.controller;

import it.polimi.ingsw.network.message.ChatMessage;
import it.polimi.ingsw.view.GUIView;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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

    public GameController(GUIView gui) {
        this.gui = gui;
    }

    public void handleChatMessage() {
        String receiver = (String) this.messageReceiver.getValue();
        String message = this.message.getText();
        if(!message.equals("")) {
            this.gui.getClientController().sendMessage(new ChatMessage(this.gui.getClientController().getClient().getNickname(), receiver, message));
            this.message.setText("");
            String chatMessage = "\nYou to " + receiver + "\n" + message + "\n";
            this.chat.appendText(chatMessage);
        }
    }

    public void printChatMessageUnicast(String sender, String message) {
        String chatMessage = "\n" + sender + " to You\n" + message + "\n";
        this.chat.appendText(chatMessage);
    }

    public void printChatMessageBroadcast(String sender, String message) {
        String chatMessage = "\n" + sender + " to Everyone\n" + message + "\n";
        this.chat.appendText(chatMessage);
    }

    public void handlePick() {

    }
}

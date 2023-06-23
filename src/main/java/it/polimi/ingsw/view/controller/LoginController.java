package it.polimi.ingsw.view.controller;

import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.NumPlayersResponse;
import it.polimi.ingsw.view.GUIView;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Gui view controller that handles some players actions during the login phase of the game
 */
public class LoginController {
    private final GUIView gui;
    @FXML TextField serverIpTextField;
    @FXML TextField nicknameTextField;
    @FXML ChoiceBox numPlayersChoice;
    @FXML Button tcpButton;
    @FXML Button rmiButton;

    /**
     * Class constructor
     * @param gui view to handle
     */
    public LoginController(GUIView gui) {
        this.gui = gui;
    }

    /**
     * Shows a pop-up when a connection error occurs
     */
    public void showConnectionError() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Connection with the server failed!");
        alert.initOwner(this.gui.getMainWindow());
        alert.show();
    }

    /**
     * Handles a new TCP connection
     */
    public void handleTcpButton() {
        if(!LaunchClient.openConnection(this.tcpButton.getText(), this.serverIpTextField.getText(), this.gui.getAdapter())) {
            this.showConnectionError();
        }
    }

    /**
     * Handles a new RMI connection
     */
    public void handleRmiButton() {
        if(!LaunchClient.openConnection(this.rmiButton.getText(), this.serverIpTextField.getText(), this.gui.getAdapter())) {
            this.showConnectionError();
        }
    }

    /**
     * Sends the nickname of a new player to the server
     */
    public void handleLogin() {
        this.gui.getClientController().sendMessage(new LoginRequest(this.nicknameTextField.getText()));
    }

    /**
     * Sends the number of players of the new game to the server
     */
    public void handleNumPlayers() {
        if(this.numPlayersChoice.getValue().equals("-")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid number of players!");
            alert.initOwner(this.gui.getMainWindow());
            alert.show();
        } else {
            this.gui.getClientController().sendMessage(new NumPlayersResponse((Integer) this.numPlayersChoice.getValue()));
        }
    }
}

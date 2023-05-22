package it.polimi.ingsw.view.controller;

import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.NumPlayersResponse;
import it.polimi.ingsw.view.GUIView;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    private final GUIView gui;
    @FXML TextField serverIpTextField;
    @FXML TextField nicknameTextField;
    @FXML ChoiceBox numPlayersChoice;
    @FXML Button tcpButton;
    @FXML Button rmiButton;

    public LoginController(GUIView gui) {
        this.gui = gui;
    }

    public void showConnectionError() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Connection with the server failed!");
        alert.initOwner(this.gui.getMainWindow());
        alert.show();
    }

    public void handleTcpButton() {
        if(!LaunchClient.openConnection(this.tcpButton.getText(), this.serverIpTextField.getText(), this.gui.getAdapter())) {
            this.showConnectionError();
        }
    }

    public void handleRmiButton() {
        if(!LaunchClient.openConnection(this.rmiButton.getText(), this.serverIpTextField.getText(), this.gui.getAdapter())) {
            this.showConnectionError();
        }
    }

    public void handleLogin() {
        this.gui.getClientController().sendMessage(new LoginRequest(this.nicknameTextField.getText()));
    }

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

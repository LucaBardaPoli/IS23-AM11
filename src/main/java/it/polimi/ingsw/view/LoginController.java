package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.NumPlayersResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

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
        new Alert(Alert.AlertType.ERROR, "Connection with the server failed!").show();
    }

    public void handleTcpButton() {
        if(!LaunchClient.openConnection(tcpButton.getText(), serverIpTextField.getText(), this.gui.getAdapter())) {
            this.showConnectionError();
        }
    }

    public void handleRmiButton() {
        if(!LaunchClient.openConnection(rmiButton.getText(), serverIpTextField.getText(), this.gui.getAdapter())) {
            this.showConnectionError();
        }
    }

    public void handleLogin() {
        this.gui.getClientController().sendMessage(new LoginRequest(nicknameTextField.getText()));
    }

    public void handleNumPlayers() {
        this.gui.getClientController().sendMessage(new NumPlayersResponse((Integer)numPlayersChoice.getValue()));
    }
}

package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.NumPlayersResponse;

import java.util.Scanner;

public class TUIView implements View {
    private ClientController clientController;
    private final Scanner scanner;

    public TUIView() {
        super();
        this.scanner = new Scanner(System.in);
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }
    public void chooseTypeOfConnection() {
        System.out.println("Insert the type of connection: ");
        String connection = this.scanner.nextLine();
        LaunchClient.openConnection(connection, "127.0.0.1", this);
    }

    public void chooseUsername() {
        System.out.println("Insert a nickname: ");
        String nickname = this.scanner.nextLine();
        this.clientController.getClient().sendMessage(new LoginRequest(nickname));
    }

    public void chooseNumPlayers() {
        System.out.println("Insert the number of players of the game: ");
        int numPlayers = this.scanner.nextInt();
        this.clientController.getClient().sendMessage(new NumPlayersResponse(numPlayers));
    }
}

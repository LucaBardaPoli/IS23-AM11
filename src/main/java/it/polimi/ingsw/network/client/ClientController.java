package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.LoginResponse;
import it.polimi.ingsw.network.message.PingMessage;
import it.polimi.ingsw.network.message.PongMessage;

import java.rmi.RemoteException;

public class ClientController {
    private Client client;
    // private View view;

    public ClientController(Client client) {
        this.client = client;
        this.client.setController(this);
    }

    public void run() {
        // Test message
            client.sendMessage(new LoginRequest("simone"));

        // Faccio partire il thread del pingpong lato client e lo passo come param
        client.startListening();
        // view.chooseUsername();
    }

    // Handles all kind of Server messages
    public void handle(LoginResponse message) {
        System.out.println("OK");
    }

    public void handle(PingMessage message) throws RemoteException {
        this.client.sendMessage(new PongMessage());
    }
}

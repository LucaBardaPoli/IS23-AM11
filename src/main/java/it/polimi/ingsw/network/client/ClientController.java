package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.LoginResponse;

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
        try {
            client.sendMessage(new LoginRequest("simone"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        client.startListening();
        // view.chooseUsername();
    }

    // Handles all kind of Server messages
    public void handle(LoginResponse message) {
        System.out.println("OK");
    }
}

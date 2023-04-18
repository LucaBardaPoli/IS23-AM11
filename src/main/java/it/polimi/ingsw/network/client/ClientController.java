package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.ServerMessage;

public class ClientController {
    private Client client;
    // private View view;

    public ClientController(Client client) {
        this.client = client;
    }

    public void run() {
        client.startListening();
        // view.chooseUsername();
    }

    // Handles all kind of Server messages
    public void handle(ServerMessage serverMessage) {

    }
}

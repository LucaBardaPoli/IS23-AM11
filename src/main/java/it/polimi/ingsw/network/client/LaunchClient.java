package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.NetworkSettings;
import it.polimi.ingsw.view.GUIView;
import it.polimi.ingsw.view.TUIView;
import it.polimi.ingsw.view.View;

public class LaunchClient {
    public static void main(String[] args) {
        View view;
        if (args.length > 0 && args[0].equalsIgnoreCase("TUI")) {
            view = new GUIView();
        } else { // Default is tui
            view = new TUIView();
        }
        view.chooseTypeOfConnection();
    }

    public static void openConnection(String typeOfConnection, String ip, View view) {
        Client client;
        if(typeOfConnection.equals("RMI")) {
            client = new ClientRMI(ip);
        } else { // Default is socket
            client = new ClientTCP(ip, NetworkSettings.SERVER_PORT_TCP);
        }
        client.openConnection();

        ClientController clientController = new ClientController(client, view);
        clientController.run();
    }
}
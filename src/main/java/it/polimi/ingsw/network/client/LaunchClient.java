package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.Settings;

public class LaunchClient {
    public static void main(String[] args) {
        /*
        View view;
        if (args.length > 0 && args[0].equalsIgnoreCase("TUI")) {
            view = new Tui().start();
        } else {
            view = new Gui().start();
        }
        view.chooseTypeOfConnection();
        */
    }

    public static void openConnection(String typeOfConnection, String ip) {
        Client client;
        if(typeOfConnection.equals("RMI")) {
            client = new RMIClient(ip);
        } else { // Default is socket
            client = new TCPClient(ip, Settings.SERVER_PORT_TCP);
        }
        client.openConnection();

        ClientController clientController = new ClientController(client);
        clientController.run();
    }
}
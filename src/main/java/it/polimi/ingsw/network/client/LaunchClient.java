package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.NetworkSettings;
import it.polimi.ingsw.view.GUIView;
import it.polimi.ingsw.view.TUIView;
import it.polimi.ingsw.view.View;

public class LaunchClient {
    /**
     * Main
     * @param args arguments passed from command line
     */
    public static void main(String[] args) {
        if(args.length > 0 && args[0].equalsIgnoreCase("--gui")) {
            GUIView.main(null);
        } else { // Default is TUI
            new TUIView();
        }
    }

    /**
     * Opens the connection based on the chosen type
     * @param typeOfConnection type of connection selected
     * @param ip IP address of the client
     * @param view view of the client
     */
    public static boolean openConnection(String typeOfConnection, String ip, View view) {
        Client client;
        if(typeOfConnection.equals("RMI")) {
            client = new ClientRMI(ip);
        } else { // Default is socket
            client = new ClientTCP(ip, NetworkSettings.SERVER_PORT_TCP);
        }
        if(client.openConnection()) {
            ClientController clientController = new ClientController(client, view);
            clientController.initController();
            return true;
        } else {
            return false;
        }
    }
}
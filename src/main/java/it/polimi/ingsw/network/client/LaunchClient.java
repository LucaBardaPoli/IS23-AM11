package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.NetworkSettings;
import it.polimi.ingsw.view.GUIView;
import it.polimi.ingsw.view.TUIView;
import it.polimi.ingsw.view.View;

import java.io.UncheckedIOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Client main class that starts a new game
 */
public class LaunchClient {

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
            try {
                // Gets the private IP address
                DatagramSocket socket = new DatagramSocket();
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                String privateIp = socket.getLocalAddress().getHostAddress();
                System.setProperty("java.rmi.server.hostname", privateIp);
            } catch(SocketException | UnknownHostException | UncheckedIOException ignored) {

            }
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
package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.NetworkSettings;

public class LaunchServer {
    public static void main(String[] args) {
        ServerController serverController = new ServerController();
        serverController.startServer(NetworkSettings.SERVER_PORT_TCP);
        try {
            serverController.run();
        } finally {
            serverController.close();
        }
    }
}
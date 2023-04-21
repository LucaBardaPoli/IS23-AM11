package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.NetworkSettings;

import java.io.IOException;

public class LaunchServer {

    public static void main(String[] args) throws IOException {
        ServerController serverController = new ServerController();
        serverController.startServer(NetworkSettings.SERVER_PORT_TCP);
        try {
            serverController.run();
        } finally {
            serverController.close();
        }
    }
}
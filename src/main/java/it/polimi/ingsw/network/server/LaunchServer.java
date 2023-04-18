package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.Settings;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class LaunchServer {

    public static void main(String[] args) throws IOException {
        ServerController serverController = new ServerController();
        serverController.startServer(Settings.SERVER_PORT_TCP);
        try {
            serverController.run();
        } finally {
            serverController.close();
        }
    }
}
package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.ControllerManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiEchoServer {
    private int port;
    public static ControllerManager controllerManager;
    //private static final int maxRetries = 10;
    public MultiEchoServer(int port) {
        this.port = port;
        MultiEchoServer.controllerManager = ControllerManager.getInstance();
    }

    public void startServer() {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e.getMessage()); // Porta non disponibile
            return;
        }

        System.out.println("Server ready");
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new ServerClientHandler(socket));
            } catch(IOException e) {
                break;// Entrerei qui se serverSocket venisse chiuso
            }
        }
        executor.shutdown();
    }

    /*public static void startMyTimer() {
        Timer timer = new Timer();

        // lambda we pass down (to show another way to be called back by another class)
        TimeOutCheckerInterface timeOutChecker = (l) -> {
            System.out.println(l);
            Boolean timeoutReached = l > maxRetries;
            if (timeoutReached){
                System.out.println("Got timeout inside server class");
                return true;
            }
            return false;
        };

        TimerTask task = new TimeoutCounter(timeOutChecker);
        int intialDelay = 50;
        int delta = 1000;
        timer.schedule(task, intialDelay, delta);
    }*/

    public static void main(String[] args) {
        MultiEchoServer echoServer = new MultiEchoServer(1234);
        echoServer.startServer();
    }
}
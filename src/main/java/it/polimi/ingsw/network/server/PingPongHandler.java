package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.NetworkSettings;
import it.polimi.ingsw.network.message.PingMessage;

import java.io.IOException;
import java.net.Socket;

public class PingPongHandler implements Runnable{
    private final Socket socket;
    private ClientHandlerTCP clientHandlerTCP;
    private int lostPackets;
    private boolean isReceivedMessage;
    public PingPongHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        isReceivedMessage = false;
        lostPackets = 0;

        while (lostPackets < NetworkSettings.MAX_LOST_PACKETS){

            this.clientHandlerTCP.sendMessage(new PingMessage());
            try {
                Thread.sleep(NetworkSettings.MAX_PONG_WAIT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(!isReceivedMessage){
                lostPackets++;
                this.clientHandlerTCP.sendMessage(new PingMessage());
            } else{
                lostPackets = 0;
                isReceivedMessage = false;
            }

        }

        // Closing the connection of the client
        try {
            this.clientHandlerTCP.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void notifyReceivedMessage(){
        this.isReceivedMessage = true;
    }


    public void setClientHandlerTCP(ClientHandlerTCP clientHandlerTCP){
        this.clientHandlerTCP = clientHandlerTCP;
    }
}

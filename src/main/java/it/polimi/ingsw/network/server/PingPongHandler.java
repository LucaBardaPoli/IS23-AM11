package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.NetworkSettings;
import it.polimi.ingsw.network.message.PingMessage;

public class PingPongHandler implements Runnable {
    private ClientHandler clientHandler;
    private int lostPackets;
    private boolean isReceivedMessage;
    private boolean stopPing;

    @Override
    public void run() {
        stopPing = false;
        try {
            Thread.sleep(NetworkSettings.INIT_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        isReceivedMessage = false;
        lostPackets = 0;

        while(lostPackets < NetworkSettings.MAX_LOST_PACKETS && !this.stopPing) {

            if(!this.stopPing) {
                this.clientHandler.sendMessage(new PingMessage());
            }

            try {
                Thread.sleep(NetworkSettings.MAX_PONG_WAIT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(!isReceivedMessage) {
                lostPackets++;

            } else {
                lostPackets = 0;
                isReceivedMessage = false;
            }
        }

        // Closing the connection of the client
        if(!this.clientHandler.stopConnection) {
            this.clientHandler.initClose();
        }
    }

    public void notifyReceivedMessage(){
        this.isReceivedMessage = true;
    }

    public void setClientHandler(ClientHandler clientHandler){
        this.clientHandler = clientHandler;
    }

    public void stopPing() {
        this.stopPing = true;
    }
}

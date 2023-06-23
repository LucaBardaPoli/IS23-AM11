package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.NetworkSettings;
import it.polimi.ingsw.network.message.PingMessage;

/**
 * Class used to test the connection between client and server
 */
public class PingPongHandler implements Runnable {
    private ClientHandler clientHandler;
    private boolean isReceivedMessage;
    private boolean stopPing;

    /**
     * Manages the PingPong between the Client and the Server.
     */
    public void run() {
        stopPing = false;
        try {
            Thread.sleep(NetworkSettings.INIT_TIME);
        } catch (InterruptedException e) {
            this.stopPing();
        }

        isReceivedMessage = false;
        int lostPackets = 0;

        while(lostPackets < NetworkSettings.MAX_LOST_PACKETS && !this.stopPing) {

            if(!this.stopPing) {
                this.clientHandler.sendMessage(new PingMessage());
            }

            try {
                Thread.sleep(NetworkSettings.MAX_PONG_WAIT);
            } catch (InterruptedException e) {
                this.stopPing();
            }

            if(!isReceivedMessage) {
                lostPackets++;
            } else {
                lostPackets = 0;
                isReceivedMessage = false;
            }
        }

        // Closing the connection with the client
        if(!this.clientHandler.stopConnection) {
            this.clientHandler.initClose();
        }
    }

    /**
     * Notifies if a message is received from the Client.
     */
    public void notifyReceivedMessage(){
        this.isReceivedMessage = true;
    }

    /**
     * Setter method: sets a ClientHandler.
     * @param clientHandler clientHandler that will handle a specified client.
     */
    public void setClientHandler(ClientHandler clientHandler){
        this.clientHandler = clientHandler;
    }

    /**
     * Stops the PingPong
     */
    public void stopPing() {
        this.stopPing = true;
    }
}

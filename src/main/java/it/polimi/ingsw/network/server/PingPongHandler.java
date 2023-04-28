package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.NetworkSettings;
import it.polimi.ingsw.network.message.PingMessage;

public class PingPongHandler implements Runnable {
    private ClientHandler clientHandler;
    private int lostPackets;
    private boolean isReceivedMessage;

    @Override
    public void run() {
        new Thread(() -> {
            try {
                Thread.sleep(NetworkSettings.INIT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            isReceivedMessage = false;
            lostPackets = 0;

            while (lostPackets < NetworkSettings.MAX_LOST_PACKETS) {

                this.clientHandler.sendMessage(new PingMessage());
                try {
                    Thread.sleep(NetworkSettings.MAX_PONG_WAIT);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (!isReceivedMessage) {
                    lostPackets++;
                    this.clientHandler.sendMessage(new PingMessage());
                } else {
                    lostPackets = 0;
                    isReceivedMessage = false;
                }

            }
            // Closing the connection of the client
            this.clientHandler.close();
        }).start();
    }

    public void notifyReceivedMessage(){
        this.isReceivedMessage = true;
    }


    public void setClientHandler(ClientHandler clientHandler){
        this.clientHandler = clientHandler;
    }
}

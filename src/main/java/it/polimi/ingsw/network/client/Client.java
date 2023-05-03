package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.ClientMessage;

public abstract class Client {
    protected String serverIp;
    protected String nickname;
    protected ClientController controller;
    protected boolean stopConnection;

    public Client(String serverIp) {
        this.serverIp = serverIp;
        this.stopConnection = false;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setController(ClientController clientController) {
        this.controller = clientController;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public abstract void openConnection();

    public abstract void start();

    public abstract void sendMessage(ClientMessage clientMessage);

    public abstract void close();
}

package it.polimi.ingsw.network;

public class NetworkSettings {
    // Server infos
    public static final int SERVER_PORT_TCP = 6666;
    public static final int SERVER_PORT_RMI = 1099;
    public static final String SERVER_NAME = "127.0.0.1";

    // Client infos
    public static final int CLIENT_PORT_RMI = 1;

    // Other infos
    public static final String RMI_REMOTE_OBJECT = "RMIListener";

    public static final int MAX_LOST_PACKETS = 5;
    public static final int MAX_PONG_WAIT = 5000;
    public static final int INIT_TIME = 2000;
}
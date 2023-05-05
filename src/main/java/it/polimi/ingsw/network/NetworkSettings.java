package it.polimi.ingsw.network;

public class NetworkSettings {
    /**
     * NetworkSettings Class
     */

    /* Server info */
    public static final int SERVER_PORT_TCP = 11500;
    public static final int SERVER_PORT_RMI = 1099;
    public static final String SERVER_NAME = "127.0.0.1";

    /* Client info */
    public static final int CLIENT_PORT_RMI = 0;

    /* Other info */
    public static final String RMI_REMOTE_OBJECT = "RMIListener";

    /* Ping pong info */
    public static final int MAX_LOST_PACKETS = 5;
    public static final int MAX_PONG_WAIT = 10000;
    public static final int INIT_TIME = 2000;
}
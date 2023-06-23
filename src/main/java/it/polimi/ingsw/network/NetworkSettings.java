package it.polimi.ingsw.network;

/**
 * Class that contains some data (constants) related to the whole network
 */
public class NetworkSettings {

    /* Server info */
    public static final int SERVER_PORT_TCP = 11500;
    public static int SERVER_PORT_RMI = 1099;


    /* Client info */
    public static final int CLIENT_PORT_RMI = 0;


    /* Other info */
    public static final String RMI_REMOTE_OBJECT = "RMIListener";


    /* Ping pong info */
    public static final int MAX_LOST_PACKETS = 5;
    public static final int MAX_PONG_WAIT = 20000;
    public static final int INIT_TIME = 2000;
}
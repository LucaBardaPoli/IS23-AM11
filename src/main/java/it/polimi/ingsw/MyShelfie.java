package it.polimi.ingsw;

import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.server.LaunchServer;

import java.util.Arrays;

/**
 * Application launcher
 */
public class MyShelfie {
    public static void main(String[] args) {
        if(args.length > 0) {
            if(args[0].equalsIgnoreCase("--server") || args[0].equalsIgnoreCase("--s")) {
                LaunchServer.main(Arrays.copyOfRange(args, 1, args.length));
            } else {
                LaunchClient.main(Arrays.copyOfRange(args, 1, args.length));
            }
        } else {
            LaunchClient.main(new String[] {"--gui"});
        }
    }
}

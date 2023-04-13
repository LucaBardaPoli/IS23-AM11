package it.polimi.ingsw.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPInitClientHandler extends InitClientHandler implements Runnable {
    private Socket socket;

    public TCPInitClientHandler(Socket socket) {
        super();
        this.socket = socket;
    }

    public void run() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String nickname = "";
            int numPlayers = 0;

            while(true) {
                out.println("Mandami lo username");
                nickname = in.readLine();
                if(isLobbyEmpty()) {
                    out.println("Dammi il num di giocatori");
                    numPlayers = Integer.parseInt(in.readLine());
                    if(login(nickname, numPlayers)) {
                        break;
                    }
                } else {
                    if(login(nickname)) {
                        break;
                    }
                }
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }
}

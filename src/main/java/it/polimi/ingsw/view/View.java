package it.polimi.ingsw.view;

import it.polimi.ingsw.network.client.ClientController;

public interface View {
    void setClientController(ClientController clientController);
    void chooseTypeOfConnection();
    void chooseUsername();
    void chooseNumPlayers();
}

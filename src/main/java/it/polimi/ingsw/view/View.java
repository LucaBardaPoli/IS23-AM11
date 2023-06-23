package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import java.util.List;

public interface View {

    /**
     * Players getter
     * @return players
     */
    List<String> getPlayers();

    /**
     * Current player getter
     * @return current player
     */
    String getCurrentPlayer();

    /**
     * Picked tiles getter
     * @return picked tiles
     */
    List<Tile> getPickedTiles();


    /* Initialization methods */
    /**
     * Client controller setter
     * @param clientController new client controller
     */
    void setClientController(ClientController clientController);

    /**
     * Methods that saves the information about the new game which is about to start
     * @param board new board
     * @param commonGoals new common goals
     * @param commonGoalsTokens new common goals tokens
     * @param personalGoal new personal goals
     * @param nextPlayer next player to play
     */
    void startGame(Board board, List<CommonGoal> commonGoals, List<Integer> commonGoalsTokens, PersonalGoal personalGoal, String nextPlayer);

    /**
     * Players setter
     * @param players new list of players
     */
    void setPlayers(List<String> players);

    /**
     * Handles the lobby before the start of the game
     * @param lobbySize size of the lobby
     * @param lobby lobby of the game
     * @param newPlayerConnected true if connected, false if disconnected
     * @param playerName name of the player
     */
    void updateLobbyInfo(int lobbySize, List<String> lobby, boolean newPlayerConnected, String playerName);


    /* Methods to update the items */
    /**
     * Updates the current board
     * @param board new board
     */
    void updateBoard(Board board);

    /**
     * Updates a player's bookshelf
     * @param player name of the player
     * @param bookshelf new bookshelf
     */
    void updateBookshelf(String player, Bookshelf bookshelf);

    /**
     * Updates the currently picked tiles
     * @param pickedTiles picked tiles
     */
    void updatePickedTiles(List<Tile> pickedTiles);

    /**
     * Updates a player's points
     * @param player name of the player
     * @param points new points
     */
    void updatePoints(String player, int points);

    /**
     * Updates the common goals tokens
     * @param commonGoalsTokens new common goals tokens
     */
    void updateCommonGoals(List<Integer> commonGoalsTokens);

    /**
     * Updates the end game value
     * @param endGame new end game value
     */
    void updateEndGame(boolean endGame);


    /* Methods to ask for a player's move */
    /**
     * Asks for the type of connection
     */
    void showChooseTypeOfConnection();

    /**
     * Asks for the nickname
     */
    void showChooseNickname();

    /**
     * Asks for the number of players
     */
    void showChooseNumPlayers();

    /**
     * Asks to pick a tile
     */
    void showPickATile();

    /**
     * Asks to choose the column where to insert the picked tiles
     */
    void showChooseColumn();

    /**
     * Asks to swap the picked tiles order
     */
    void showSwapTilesOrder();


    /* Methods to show a move's result */
    /**
     * Shows that the nickname is not valid
     */
    void showInvalidNickname();

    /**
     * Shows that the pick is valid
     */
    void showValidPick();

    /**
     * Shows that the pick is not valid
     */
    void showInvalidPick();

    /**
     * Shows that the tile unpick is valid
     */
    void showValidUnpick();

    /**
     * Shows that the tile unpick is not valid
     */
    void showInvalidUnpick();

    /**
     * Shows that no tiles are picked
     */
    void showNoPickedTiles();

    /**
     * Shows that the selected column is valid
     */
    void showValidColumn();

    /**
     * Shows that the selected column is not valid
     */
    void showInvalidColumn();

    /**
     * Shows that the swap is valid
     */
    void showValidSwap();

    /**
     * Shows that the swap is not valid
     */
    void showInvalidSwap();


    /* Methods to handle the change of a turn */
    /**
     * Handles the start of a new turn
     * @param player name of the player who is now playing
     */
    void startTurn(String player);

    /**
     * Handles the end of a player's turn
     */
    void endTurn();

    /**
     * Shows a recap of the game
     */
    void showEndGame();


    /* Methods to handle chat messages */
    /**
     * Shows an unicast message
     * @param sender sender of the message
     * @param message message sent
     */
    void showNewChatMessageUnicast(String sender, String message);

    /**
     * Shows a broadcast message
     * @param sender sender of the message
     * @param message message sent
     */
    void showNewChatMessageBroadcast(String sender, String message);


    /* Methods to show disconnection issues */
    /**
     * Shows a message when a player has disconnected
     * @param disconnectedPlayer name of the player
     */
    void showPlayerDisconnected(String disconnectedPlayer);

    /**
     * Shows a message when there's a connection issue
     */
    void showDisconnection();
}

package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import javafx.application.Platform;

import java.util.List;

/**
 * Class that adapts the calling of methods by the network controllers to how they need to be called
 * due to GUI Application requirements
 */
public class GUIViewAdapter implements View {
    private final GUIView view;

    /**
     * Class constructor
     * @param view to handle
     */
    public GUIViewAdapter(GUIView view) {
        this.view = view;
    }

    /**
     * Players getter
     * @return players
     */
    public List<String> getPlayers() {
        return this.view.getPlayers();
    }

    /**
     * Current player getter
     * @return current player
     */
    public String getCurrentPlayer() {
        return this.view.getCurrentPlayer();
    }

    /**
     * Picked tiles getter
     * @return picked tiles
     */
    public List<Tile> getPickedTiles() {
        return this.view.getPickedTiles();
    }


    /* Initialization methods */
    /**
     * Client controller setter
     * @param clientController new client controller
     */
    public void setClientController(ClientController clientController) {
        this.view.setClientController(clientController);
    }

    /**
     * Methods that saves the information about the new game which is about to start
     * @param board new board
     * @param commonGoals new common goals
     * @param commonGoalsTokens new common goals tokens
     * @param personalGoal new personal goals
     * @param nextPlayer next player to play
     */
    public void startGame(Board board, List<CommonGoal> commonGoals, List<Integer> commonGoalsTokens, PersonalGoal personalGoal, String nextPlayer) {
        Platform.runLater(() -> this.view.startGame(board, commonGoals, commonGoalsTokens, personalGoal, nextPlayer));
    }

    /**
     * Players setter
     * @param players new list of players
     */
    public void setPlayers(List<String> players) {
        Platform.runLater(() -> this.view.setPlayers(players));
    }

    /**
     * Handles the lobby before the start of the game
     * @param lobbySize size of the lobby
     * @param lobby lobby of the game
     * @param newPlayerConnected true if connected, false if disconnected
     * @param playerName name of the player
     */
    public void updateLobbyInfo(int lobbySize, List<String> lobby, boolean newPlayerConnected, String playerName) {
        Platform.runLater(() -> this.view.updateLobbyInfo(lobbySize, lobby, newPlayerConnected, playerName));
    }


    /* Methods to update the items */
    /**
     * Updates the current board
     * @param board new board
     */
    public void updateBoard(Board board) {
        Platform.runLater(() -> this.view.updateBoard(board));
    }

    /**
     * Updates a player's bookshelf
     * @param player name of the player
     * @param bookshelf new bookshelf
     */
    public void updateBookshelf(String player, Bookshelf bookshelf) {
        Platform.runLater(() -> this.view.updateBookshelf(player, bookshelf));
    }

    /**
     * Updates the currently picked tiles
     * @param pickedTiles picked tiles
     */
    public void updatePickedTiles(List<Tile> pickedTiles) {
        Platform.runLater(() -> this.view.updatePickedTiles(pickedTiles));
    }

    /**
     * Updates a player's points
     * @param player name of the player
     * @param points new points
     */
    public void updatePoints(String player, int points) {
        Platform.runLater(() -> this.view.updatePoints(player, points));
    }

    /**
     * Updates the common goals tokens
     * @param commonGoalsTokens new common goals tokens
     */
    public void updateCommonGoals(List<Integer> commonGoalsTokens) {
        Platform.runLater(() -> this.view.updateCommonGoals(commonGoalsTokens));
    }

    /**
     * Updates the end game value
     * @param endGame new end game value
     */
    public void updateEndGame(boolean endGame) {
        Platform.runLater(() -> this.view.updateEndGame(endGame));
    }


    /* Methods to ask for a player's move */
    /**
     * Asks for the type of connection
     */
    public void showChooseTypeOfConnection() {
        Platform.runLater(this.view::showChooseTypeOfConnection);
    }

    /**
     * Asks for the nickname
     */
    public void showChooseNickname() {
        Platform.setImplicitExit(false);
        Platform.runLater(this.view::showChooseNickname);
    }

    /**
     * Asks for the number of players
     */
    public void showChooseNumPlayers() {
        Platform.runLater(this.view::showChooseNumPlayers);
    }

    /**
     * Asks to pick a tile
     */
    public void showPickATile() {
        Platform.runLater(this.view::showPickATile);
    }

    /**
     * Asks to choose the column where to insert the picked tiles
     */
    public void showChooseColumn() {
        Platform.runLater(this.view::showChooseColumn);
    }

    /**
     * Asks to swap the picked tiles order
     */
    public void showSwapTilesOrder() {
        Platform.runLater(this.view::showSwapTilesOrder);
    }


    /* Methods to show a move's result */
    /**
     * Shows that the nickname is not valid
     */
    public void showInvalidNickname() {
        Platform.runLater(this.view::showInvalidNickname);
    }

    /**
     * Shows that the pick is valid
     */
    public void showValidPick() {
        Platform.runLater(this.view::showValidPick);
    }

    /**
     * Shows that the pick is not valid
     */
    public void showInvalidPick() {
        Platform.runLater(this.view::showInvalidPick);
    }

    /**
     * Shows that the tile unpick is valid
     */
    public void showValidUnpick() {
        Platform.runLater(this.view::showValidUnpick);
    }

    /**
     * Shows that the tile unpick is not valid
     */
    public void showInvalidUnpick() {
        Platform.runLater(this.view::showInvalidUnpick);
    }

    /**
     * Shows that no tiles are picked
     */
    public void showNoPickedTiles() {
        Platform.runLater(this.view::showNoPickedTiles);
    }

    /**
     * Shows that the selected column is valid
     */
    public void showValidColumn() {
        Platform.runLater(this.view::showValidColumn);
    }

    /**
     * Shows that the selected column is not valid
     */
    public void showInvalidColumn() {
        Platform.runLater(this.view::showInvalidColumn);
    }

    /**
     * Shows that the swap is valid
     */
    public void showValidSwap() {
        Platform.runLater(this.view::showValidSwap);
    }

    /**
     * Shows that the swap is not valid
     */
    public void showInvalidSwap() {
        Platform.runLater(this.view::showInvalidSwap);
    }


    /* Methods to handle the change of a turn */
    /**
     * Handles the start of a new turn
     * @param player name of the player who is now playing
     */
    public void startTurn(String player) {
        Platform.runLater(() -> this.view.startTurn(player));
    }

    /**
     * Handles the end of a player's turn
     */
    public void endTurn() {
        Platform.runLater(this.view::endTurn);
    }

    /**
     * Shows a recap of the game
     */
    public void showEndGame() {
        Platform.runLater(this.view::showEndGame);
    }


    /* Methods to handle chat messages */
    /**
     * Shows an unicast message
     * @param sender sender of the message
     * @param message message sent
     */
    public void showNewChatMessageUnicast(String sender, String message) {
        Platform.runLater(() -> this.view.showNewChatMessageUnicast(sender, message));
    }

    /**
     * Shows a broadcast message
     * @param sender sender of the message
     * @param message message sent
     */
    public void showNewChatMessageBroadcast(String sender, String message) {
        Platform.runLater(() -> this.view.showNewChatMessageBroadcast(sender, message));
    }


    /* Methods to show disconnection issues */
    /**
     * Shows a message when a player has disconnected
     * @param disconnectedPlayer name of the player
     */
    public void showPlayerDisconnected(String disconnectedPlayer) {
        Platform.runLater(() -> this.view.showPlayerDisconnected(disconnectedPlayer));
    }

    /**
     * Shows a message when there's a connection issue
     */
    public void showDisconnection() {
        Platform.runLater(this.view::showDisconnection);
    }
}

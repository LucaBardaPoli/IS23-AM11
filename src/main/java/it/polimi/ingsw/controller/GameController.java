package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

/**
 * Class that handles the evolution of the game (communication with view and model)
 */
public class GameController implements GameControllerInterfaceView, GameControllerInterfaceModel {
    private GameInterface model;

    /**
     * Setter of the model
     * @param model game to handle
     */
    public void setModel(GameInterface model) {
        this.model = model;
    }

    /**
     * Getter of the model
     * @return model
     */
    public GameInterface getModel() {
        return this.model;
    }

    /**
     * Picks a card from the board
     * @param player player who played the move
     * @param position position of the card to pick on the board
     */
    public void pickCard(Player player, Position position) {
        if(checkPlayer(player)) {
            this.model.pickCard(position);
        }
    }

    /**
     * Confirms the cards picked from the board
     * @param player player who played the move
     */
    public void confirmPick(Player player) {
        if(checkPlayer(player)) {
            this.model.confirmChoice();
        }
    }

    /**
     * Confirms the column where to insert the cards
     * @param player player who played the move
     * @param column column where to insert the cards
     */
    public void confirmColumn(Player player, Integer column) {
        if(checkPlayer(player)) {
            this.model.confirmColumn(column);
        }
    }

    /**
     * Moves the selected card to the last place in the list of cards to insert in the bookshelf
     * @param player player who played the move
     * @param index index of the card to swap
     */
    public void swapCards(Player player, Integer index) {
        if(checkPlayer(player)) {
            this.model.rearrangeCards(index);
        }
    }

    /**
     * Confirms the order of the cards to insert in the board
     * @param player player who played the move
     */
    public void confirmOrder(Player player) {
        if(checkPlayer(player)) {
            this.model.confirmOrderSelectedCards();
        }
    }

    /**
     * Sends a message to a player
     * @param player player to send the message
     * @param text message
     */
    public void sendMessage(Player player, String text) {

    }

    /**
     * Checks if the given player is the one who's currently playing
     * @param player player who played the move
     * @return true whether the given player is the one who's currently playing
     */
    private boolean checkPlayer(Player player) {
        return this.model.getCurrentPlayer().equals(player);
    }


    // Methods called by the model to change the view status
    public void changeTurn() {

    }

    public void removeBoardCard() {

    }

    public void addBoardCard() {

    }

    public void newCardOrder() {

    }

    public void addCardToBookshelf() {

    }

    public void updatePoints() {

    }

    public void initGame() {

    }

    public void finalMessage() {

    }

    public void sendMessage() {

    }

    public void endGame() {

    }
}

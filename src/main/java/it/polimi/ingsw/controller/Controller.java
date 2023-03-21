package it.polimi.ingsw.controller;

/**
 * Controller class that handles the games creation and the communication between View and Model
 */
public class Controller {
    private GameManager model;

    /**
     * Class constructor
     */
    public Controller() {
        this.model = new GameManager();
    }

    /**
     * Sends a message to a specific player
     * @param player player to send the message to
     * @param text text of the message
     */
    public void sendMessage(Player player, String text) {

    }

    /**
     * Handles the selection of a card on the board game
     * @param selectBoard selection of a card to pick
     */
    public void handleAction(SelectBoard selectBoard) {

    }

    public void handleAction(SelectBookshelfOrder cbo) {

    }

    public void handleAction(SelectColumn sc) {

    }

    public void handleCardsSelection() {

    }

    public void handleCardsOrderConfirmation() {

    }

    public void handleTurnChange(Player player) {

    }
}

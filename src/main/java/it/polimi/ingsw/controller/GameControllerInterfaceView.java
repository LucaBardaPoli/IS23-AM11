package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import java.util.List;
import java.util.Optional;

public interface GameControllerInterfaceView {

    /**
     * Getter of the players
     * @return list of players
     */
    public List<Player> getPlayers();

    /**
     * Picks a card from the board
     * @param player player who played the move
     * @param position position of the card to pick on the board
     */
    public Optional<Tile> pickCard(Player player, Position position);

    /**
     * Confirms the cards picked from the board
     * @param player player who played the move
     */
    public void confirmPick(Player player);

    /**
     * Confirms the column where to insert the cards
     * @param player player who played the move
     * @param column column where to insert the cards
     */
    public boolean confirmColumn(Player player, Integer column);

    /**
     * Moves the selected card to the last place in the list of cards to insert in the bookshelf
     * @param player player who played the move
     * @param index index of the card to swap
     */
    public void swapCards(Player player, Integer index);

    /**
     * Confirms the order of the cards to insert in the board
     * @param player player who played the move
     */
    public void confirmOrder(Player player) ;

    /**
     * Sends a message to a player
     * @param player player to send the message
     * @param text message
     */
    public void sendMessage(Player player, String text);
}

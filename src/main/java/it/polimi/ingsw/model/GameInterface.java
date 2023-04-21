package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.GameControllerInterfaceModel;

import java.util.List;
import java.util.Optional;

public interface GameInterface {

    /**
     * Getter of the players
     * @return list of players
     */
    public List<Player> getPlayers();

    /**
     * Setter of the game's controller
     * @param gameController game's controller
     */
    public void setGameController(GameControllerInterfaceModel gameController);

    /**
     * Getter of the player who's currently playing
     * @return player
     */
    public Player getCurrentPlayer();

    /**
     * Tries to pick a card from the game board
     * @param position position where to pick the card
     * @return the picked card only if there is a valid pick
     */
    public Optional<Tile> pickCard(Position position);

    /**
     * Removes the card from the chosen ones
     * @param position position of the card
     */
    public void removeCard(Position position);

    /**
     * Checks if the picked cards are a valid combination
     * @return true if the pick is valid
     */
    public boolean confirmChoice();

    /**
     * Checks if the given column can contain the picked cards
     * @param column column where to insert the picked cards
     * @return a boolean depending on whether the insertion is valid or not
     */
    public boolean confirmColumn(Integer column);

    /**
     * Moves the selected card to the last place in the list of cards to insert in the bookshelf
     * @param position position of the selected card
     * @return the new sorted list of cards
     */
    public List<Tile> rearrangeCards(Integer position);

    /**
     * Checks if the current cards order is valid. If so it inserts them in the bookshelf
     */
    public void confirmOrderSelectedCards();

    /**
     * Returns the current points of a given player
     * @param player player's nickname
     * @return player's points if he exists
     */
    public Optional<Integer> getPlayerPoints(String player);

    /**
     * Checks if a given nickname is already taken
     * @param nickname nickname
     * @return true if exists a player with the given nickname
     */
    public boolean isNicknameTaken(String nickname);
}


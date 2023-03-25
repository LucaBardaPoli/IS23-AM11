package it.polimi.ingsw.model;

import java.util.List;
import java.util.Optional;

public interface GameInterface {
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
    public Optional<CardType> pickCard(Position position);

    /**
     * Checks if the picked cards are a valid combination
     * @return true if the pick is valid
     */
    public boolean confirmChoice();

    /**
     * Checks if the given column can contain the picked cards
     * @param column column where to insert the picked cards
     * @return the inserted cards only if the insertion is valid
     */
    public Optional<List<CardType>> confirmColumn(Integer column);

    /**
     * Moves the selected card to the last place in the list of cards to insert in the bookshelf
     * @param position position of the selected card
     * @return the new sorted list of cards
     */
    public List<CardType> rearrangeCards(Integer position);

    /**
     * Checks if the current cards order is valid. If so it inserts them in the bookshelf
     */
    public void confirmOrderSelectedCards();

    /**
     * Checks if a given nickname is already taken
     * @param nickname nickname
     * @return true if exists a player with the given nickname
     */
    public boolean isNicknameTaken(String nickname);
}


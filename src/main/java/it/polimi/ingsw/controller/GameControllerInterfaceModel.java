package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.GameInterface;

public interface GameControllerInterfaceModel {

    public void changeTurn();

    public void removeBoardCard();

    public void addBoardCard();

    public void newCardOrder();

    public void addCardToBookshelf();

    public void updatePoints();

    public void initGame();

    public void finalMessage();

    public void sendMessage();

    public void endGame();
}

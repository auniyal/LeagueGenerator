package org.leaguegenerator.domain;

public class DoublesGame {

    int gameId;
    DoublesPair doublesPairOne;
    DoublesPair doublesPairTwo;


    public DoublesPair getDoublesPairOne() {
        return doublesPairOne;
    }

    public int getGameId() {
        return gameId;
    }

    public DoublesPair getDoublesPairTwo() {
        return doublesPairTwo;
    }


    public DoublesGame(int gameId, DoublesPair doublesPairOne, DoublesPair doublesPairTwo) {
        this.gameId = gameId;
        this.doublesPairOne = doublesPairOne;
        this.doublesPairTwo = doublesPairTwo;
    }


    @Override
    public String toString() {
        return "org.leaguegenerator.domain.DoublesGame{" +
                ", doublesPairOne=" + doublesPairOne +
                ", doublesPairTwo=" + doublesPairTwo +
                '}';
    }
}

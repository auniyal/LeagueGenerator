package org.leaguegenerator.domain;

import java.util.Objects;

public class DoublesPair {

    int pairingId;
    SinglePlayer singlePlayerOne;
    SinglePlayer singlePlayerTwo;


    public SinglePlayer getsPlayerOne() {
        return singlePlayerOne;
    }


    public SinglePlayer getPlayerTwo() {
        return singlePlayerTwo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoublesPair that = (DoublesPair) o;
        return Objects.equals(singlePlayerOne, that.singlePlayerOne) && Objects.equals(singlePlayerTwo, that.singlePlayerTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(singlePlayerOne, singlePlayerTwo);
    }

    public DoublesPair(int pairingId, SinglePlayer singlePlayerOne, SinglePlayer singlePlayerTwo) {
        this.pairingId = pairingId;
        this.singlePlayerOne = singlePlayerOne;
        this.singlePlayerTwo = singlePlayerTwo;
    }

    @Override
    public String toString() {
        return "org.leaguegenerator.domain.DoublesPair{" +
                singlePlayerOne +
                "," + singlePlayerTwo +
                '}';
    }

    public boolean isPairingAllowed(DoublesPair doublesPair) {

        boolean conflict = (this.getsPlayerOne().equals(doublesPair.getsPlayerOne())
                || this.getsPlayerOne().equals(doublesPair.getPlayerTwo()))
                || (this.getPlayerTwo().equals(doublesPair.getsPlayerOne())
                || this.getPlayerTwo().equals(doublesPair.getPlayerTwo()));


        return !conflict;
    }
}

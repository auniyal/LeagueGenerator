package org.leaguegenerator.domain;

import java.util.Objects;

public class SinglePlayer {
    int playerId;

    public SinglePlayer(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }


    @Override
    public String toString() {
        return "org.leaguegenerator.domain.SinglePlayer{" +
                "playerId=" + playerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinglePlayer that = (SinglePlayer) o;
        return playerId == that.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }
}

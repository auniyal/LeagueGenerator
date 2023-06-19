import org.leaguegenerator.domain.DoublesGame;
import org.leaguegenerator.domain.DoublesPair;
import org.leaguegenerator.domain.SinglePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * Program to generate unique roudrobin pairing for doubles in raquet sports like tennis, badminton, tt etc.
 *
 */

public class Main {

    Logger logger = Logger.getAnonymousLogger();
    public static void main(String[] args) {

        int numberOfPlayers = 8;
        List<DoublesPair> uniquePairing = generateUniquePairs(numberOfPlayers);
        List<DoublesGame> doublesGames =  generateMatches(uniquePairing);

        //displayUniquePairs(uniquePairing);
        validateGeneratedMatchesCounts(numberOfPlayers, doublesGames);
        displayMatches(doublesGames, numberOfPlayers);

    }

    private static void validateGeneratedMatchesCounts(int numberOfPlayers, List<DoublesGame> doublesGames) {

        for (int count = 1; count <= numberOfPlayers; count++) {
            int finalCount = count;
            long totalMatches = doublesGames.stream().filter(x -> x.getDoublesPairOne().getsPlayerOne().getPlayerId() == finalCount
                            || x.getDoublesPairOne().getPlayerTwo().getPlayerId() == finalCount
                            || x.getDoublesPairTwo().getsPlayerOne().getPlayerId() == finalCount
                            || x.getDoublesPairTwo().getPlayerTwo().getPlayerId() == finalCount)
                    .count();


            if(numberOfPlayers%4==0 &&  totalMatches!=numberOfPlayers-1) {
                throw new RuntimeException(String.format("Retry, player:%d has %d games. Expected count is:%d ",count,totalMatches,numberOfPlayers-1 ));
            }
            System.out.println("Player " + count + " has " + totalMatches + " games");

        }
    }

    private static void displayMatches(List<DoublesGame> doublesGames, int totalPlayers) {
        System.out.printf("%n%n%n%n******************************* RoundRobin matches for  %s players *******************************%n", totalPlayers);
        doublesGames.forEach(Main::displayRow);
        System.out.println("**************************************************************\n\n");
    }

    private static List<DoublesGame> generateMatches(List<DoublesPair> uniquePairing) {
        List<DoublesGame> doublesGames = new ArrayList<>();
        for (int m = 0; m < uniquePairing.size(); m++) {
            DoublesPair firstPair = uniquePairing.get(m);
            DoublesPair secondPair = getSecondPair(uniquePairing, firstPair, doublesGames);
            if (secondPair != null) {
                doublesGames.add(new DoublesGame(m, firstPair, secondPair));
            }
        }
        return doublesGames;
    }

    private static void displayUniquePairs(List<DoublesPair> uniquePairing) {
        uniquePairing.forEach(System.out::println);
    }

    private static List<DoublesPair> generateUniquePairs(int numberOfPlayers) {
        List<DoublesPair> uniquePairing = new ArrayList<>();
        int pairingId = 1;
        for (int i = 1; i <= numberOfPlayers; i++) {
            for (int j = i + 1; j <= numberOfPlayers; j++) {
                uniquePairing.add(new DoublesPair(pairingId++, new SinglePlayer(i), new SinglePlayer(j)));
            }
        }

        //TODO shuffle trips unique pairing intermittently
        Collections.shuffle(uniquePairing);

        return uniquePairing;
    }


    private static DoublesPair getSecondPair(List<DoublesPair> uniquePairing, DoublesPair firstPair, List<DoublesGame> doublesGames) {

        for (DoublesPair doublesPair : uniquePairing) {
            boolean isPairingAllowed = firstPair.isPairingAllowed(doublesPair);
            boolean firstPairAlreadyPaired = alreadyPaired(doublesGames, firstPair);
            boolean secondPairAlreadyPaired = alreadyPaired(doublesGames, doublesPair);
            if (isPairingAllowed && !firstPairAlreadyPaired && !secondPairAlreadyPaired) {
                return doublesPair;
            }

        }
        return null;
    }


    private static boolean alreadyPaired(List<DoublesGame> doublesGames, DoublesPair doublesPair) {
        return !doublesGames.isEmpty() &&
                doublesGames.stream().anyMatch(x -> x.getDoublesPairOne().equals(doublesPair)
                        || x.getDoublesPairTwo().equals(doublesPair));
    }

    private static void displayRow(DoublesGame game) {
        System.out.printf("Match:%s  Player-%s/Player-%s vs Player-%s/Player-%s%n",
                game.getGameId() + 1,
                game.getDoublesPairOne().getsPlayerOne().getPlayerId(), game.getDoublesPairOne().getPlayerTwo().getPlayerId(),
                game.getDoublesPairTwo().getsPlayerOne().getPlayerId(), game.getDoublesPairTwo().getPlayerTwo().getPlayerId());
    }
}
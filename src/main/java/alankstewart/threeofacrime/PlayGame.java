package alankstewart.threeofacrime;

import alankstewart.threeofacrime.model.SuspectCard;
import alankstewart.threeofacrime.model.ThreeOfACrime;

import java.util.Scanner;

public final class PlayGame {

    public static void main(final String[] args) {
        System.out.println("Please enter three unique suspects and the number of matches separated by commas or 'q' to quit");
        final var threeOfACrime = new ThreeOfACrime();
        final var console = new Scanner(System.in);
        while (console.hasNextLine()) {
            final var line = console.nextLine();
            if (line.equalsIgnoreCase("q")) {
                break;
            }

            final var tokens = line.split(",");
            if (tokens.length == 4) {
                final int matches;
                try {
                    matches = Integer.parseInt(tokens[3]);
                } catch (final NumberFormatException e) {
                    System.out.println("You must enter 0, 1, or 2 for the number of matches");
                    continue;
                }
                if (matches < 0 || matches > 2) {
                    System.out.println("You must enter 0, 1, or 2 for the number of matches");
                    continue;
                }

                final SuspectCard suspectCard;
                try {
                    suspectCard = SuspectCard.of(tokens[0], tokens[1], tokens[2]);
                } catch (final IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                threeOfACrime.matchSuspects(suspectCard, matches);
                threeOfACrime.printSuspectCards();
                if (threeOfACrime.getSuspectCards().size() == 1) {
                    break;
                }
            } else {
                System.out.println("You must enter three suspects and the number of matches");
            }
        }
    }
}

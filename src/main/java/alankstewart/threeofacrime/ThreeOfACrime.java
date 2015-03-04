package alankstewart.threeofacrime;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public final class ThreeOfACrime implements Iterable<SuspectCard> {

    private final List<SuspectCard> suspectCards;

    public ThreeOfACrime() {
        suspectCards = StreamSupport.stream(spliterator(), false).collect(toList());
    }

    public static void main(final String[] args) {
        final ThreeOfACrime threeOfACrime = new ThreeOfACrime();
        System.out.println("Enter three unique suspects and the number of matched suspects separated by commas or 'q' to quit");
        final Scanner console = new Scanner(System.in);
        while (console.hasNextLine()) {
            final String line = console.nextLine();
            if (line.equals("q")) {
                break;
            }

            final String[] tokens = line.split(",");
            if (tokens.length == 4) {
                final int matches = Integer.parseInt(tokens[3]);
                if (matches < 0 || matches > 2) {
                    System.out.println("Must enter 0, 1, or 2 for the number of matches");
                    continue;
                }

                final SuspectCard suspectCard;
                try {
                    suspectCard = SuspectCard.of(tokens[0], tokens[1], tokens[2]);
                } catch (final IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                switch (matches) {
                    case 0:
                        threeOfACrime.matchZeroSuspects(suspectCard);
                        break;
                    case 1:
                        threeOfACrime.matchOneSuspect(suspectCard);
                        break;
                    case 2:
                        threeOfACrime.matchTwoSuspects(suspectCard);
                        break;
                }
                threeOfACrime.printSuspectCards();
                if (threeOfACrime.getSuspectCards().size() == 1) {
                    break;
                }
            } else {
                System.out.println("Must enter three suspects and number of matches");
            }
        }
    }

    @Override
    public Iterator<SuspectCard> iterator() {
        return new SuspectCardIterator();
    }

    public List<SuspectCard> getSuspectCards() {
        return Collections.unmodifiableList(suspectCards);
    }

    public void matchZeroSuspects(final SuspectCard suspectCard) {
        matchSuspects(suspectCard, 6);
    }

    public void matchOneSuspect(final SuspectCard suspectCard) {
        matchSuspects(suspectCard, 5);
    }

    public void matchTwoSuspects(final SuspectCard suspectCard) {
        matchSuspects(suspectCard, 4);
    }

    public void printSuspectCards() {
        getSuspectCards().forEach(System.out::println);
        System.out.println(String.format("%d\n-----------------------------------------------",
                getSuspectCards().size()));
    }

    private void matchSuspects(final SuspectCard suspectCard, final int number) {
        suspectCards.remove(suspectCard);
        suspectCards.retainAll(suspectCards
                .stream()
                .filter(s -> Stream.concat(s.getSuspects().stream(),
                        suspectCard.getSuspects().stream()).distinct().count() == number)
                .collect(toList()));
    }
}

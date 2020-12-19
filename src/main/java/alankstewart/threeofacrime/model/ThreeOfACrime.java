package alankstewart.threeofacrime.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public final class ThreeOfACrime implements Iterable<SuspectCard> {

    private final List<SuspectCard> suspectCards = new ArrayList<>();
    private final List<SuspectCard> suspectCardsWon = new ArrayList<>();
    private final Set<SuspectCard> suspectCardsPlayed = new HashSet<>();

    public ThreeOfACrime() {
        startNewRound();
    }

    public void startNewRound() {
        suspectCards.clear();
        suspectCards.addAll(StreamSupport.stream(spliterator(), false).collect(toList()));
        suspectCards.removeAll(suspectCardsWon);
        suspectCardsPlayed.clear();
    }

    @Override
    public Iterator<SuspectCard> iterator() {
        return new SuspectCardIterator();
    }

    public List<SuspectCard> getSuspectCards() {
        return Collections.unmodifiableList(suspectCards);
    }

    public void matchSuspects(final SuspectCard suspectCard, final int matches) {
        if (matches < 0 || matches > 2 || suspectCardsPlayed.contains(suspectCard) || suspectCardsWon.contains(suspectCard)) {
            return;
        }

        suspectCardsPlayed.add(suspectCard);
        suspectCards.retainAll(suspectCards
                .stream()
                .filter(s -> Stream.concat(s.getSuspects().stream(),
                        suspectCard.getSuspects().stream()).distinct().count() == 6 - matches)
                .collect(toList()));

        if (suspectCards.size() == 1) {
            suspectCardsWon.add(suspectCards.get(0));
        }
    }

    public void printSuspectCards() {
        getSuspectCards().forEach(System.out::println);
        System.out.format("%d\n-----------------------------------------------\n", getSuspectCards().size());
    }
}

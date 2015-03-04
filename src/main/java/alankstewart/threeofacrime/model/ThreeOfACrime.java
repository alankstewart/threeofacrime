package alankstewart.threeofacrime.model;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public final class ThreeOfACrime implements Iterable<SuspectCard> {

    private final List<SuspectCard> suspectCards;

    public ThreeOfACrime() {
        suspectCards = StreamSupport.stream(spliterator(), false).collect(toList());
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
        System.out.format("%d\n-----------------------------------------------\n", getSuspectCards().size());
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

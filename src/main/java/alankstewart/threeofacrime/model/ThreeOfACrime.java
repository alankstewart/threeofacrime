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

    public void matchSuspects(final SuspectCard suspectCard, final int matches) {
        if (matches < 0 || matches > 2) {
            return;
        }
        final List<SuspectCard> matchedSuspects = suspectCards
                .stream()
                .filter(s -> Stream.concat(s.getSuspects().stream(),
                        suspectCard.getSuspects().stream()).distinct().count() == 6 - matches)
                .collect(toList());
        if (!matchedSuspects.isEmpty()) {
            suspectCards.retainAll(matchedSuspects);
        }
    }

    public void printSuspectCards() {
        getSuspectCards().forEach(System.out::println);
        System.out.format("%d\n-----------------------------------------------\n", getSuspectCards().size());
    }
}

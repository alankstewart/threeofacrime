package alankstewart.threeofacrime;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public final class ThreeOfACrime implements Iterable<SuspectCard> {

    private final List<SuspectCard> suspectCards;
    private final Set<Suspect> innocentSuspects = new HashSet<>();

    private ThreeOfACrime() {
        suspectCards = StreamSupport.stream(spliterator(), false).collect(toList());
    }

    public static ThreeOfACrime start() {
        return new ThreeOfACrime();
    }

    @Override
    public Iterator<SuspectCard> iterator() {
        return new SuspectCardIterator();
    }

    public List<SuspectCard> getSuspectCards() {
        return Collections.unmodifiableList(suspectCards);
    }

    public List<SuspectCard> matchZeroSuspects(final SuspectCard suspectCard) {
        return matchSuspects(suspectCard, 6);
    }

    public List<SuspectCard> matchOneSuspect(final SuspectCard suspectCard) {
        return matchSuspects(suspectCard, 5);
    }

    public List<SuspectCard> matchTwoSuspects(final SuspectCard suspectCard) {
        return matchSuspects(suspectCard, 4);
    }

    public List<SuspectCard> matchAllSuspects(final SuspectCard suspectCard) {
        return matchSuspects(suspectCard, 3);
    }

    private List<SuspectCard> matchSuspects(final SuspectCard suspectCard, final int number) {
        suspectCards.remove(suspectCard);
        suspectCards.retainAll(suspectCards.stream()
                .filter(s -> {
                    Set<Suspect> suspects = new HashSet<>(s.getSuspects());
                    suspects.addAll(suspectCard.getSuspects());
                    return suspects.size() == number;
                })
                .collect(toList()));
        return getSuspectCards();
    }
}

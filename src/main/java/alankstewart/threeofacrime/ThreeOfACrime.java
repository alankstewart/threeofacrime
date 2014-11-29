package alankstewart.threeofacrime;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class ThreeOfACrime implements Iterable<SuspectCard> {

    private final List<SuspectCard> suspectCards;

    public ThreeOfACrime() {
        suspectCards = StreamSupport.stream(spliterator(), false).collect(toList());
        Collections.shuffle(suspectCards);
    }

    @Override
    public Iterator<SuspectCard> iterator() {
        return new SuspectCardIterator();
    }

    public List<SuspectCard> getSuspectCards() {
        return suspectCards;
    }

    public Optional<SuspectCard> getNextSuspectCard() {
        return suspectCards.isEmpty() ? Optional.empty() : Optional.of(suspectCards.remove(0));
    }

    public Optional<SuspectCard> getSuspectCard(final Suspect suspect1, final Suspect suspect2, final Suspect suspect3) {
        final Iterator<SuspectCard> iterator = suspectCards.iterator();
        while (iterator.hasNext()) {
            final SuspectCard suspectCard = iterator.next();
            if (suspectCard.equals(new SuspectCard(suspect1, suspect2, suspect3))) {
                iterator.remove();
                return Optional.of(suspectCard);
            }
        }
        return Optional.empty();
    }
}

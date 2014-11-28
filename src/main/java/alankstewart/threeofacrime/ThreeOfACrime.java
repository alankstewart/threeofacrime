package alankstewart.threeofacrime;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class ThreeOfACrime implements Iterable<SuspectCard> {

    private List<SuspectCard> suspectCards;

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

    public SuspectCard getNextSuspectCard() {
        return suspectCards.isEmpty() ? null : suspectCards.remove(0);
    }
}

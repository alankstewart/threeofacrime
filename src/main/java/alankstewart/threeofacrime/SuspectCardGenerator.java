package alankstewart.threeofacrime;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class SuspectCardGenerator implements Iterable<SuspectCard> {

    public static int combination(final int n, final int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    private static int factorial(final int n) {
        return IntStream.rangeClosed(1, n).reduce((x, y) -> x * y).getAsInt();
    }

    @Override
    public Iterator<SuspectCard> iterator() {
        return new SuspectCardIterator();
    }

    public List<SuspectCard> getSuspectCards() {
        final List<SuspectCard> suspectCards = StreamSupport.stream(spliterator(), false).collect(toList());
        Collections.shuffle(suspectCards);
        return suspectCards;
    }
}

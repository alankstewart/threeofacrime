package alankstewart.threeofacrime;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class SuspectCardGenerator implements Iterable<SuspectCard> {

    public List<SuspectCard> getSuspectCards() {
        return StreamSupport.stream(spliterator(), false).collect(toList());
    }

    @Override
    public Iterator<SuspectCard> iterator() {
        return new SuspectCardIterator();
    }

    public static int combination(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    private static int factorial(int n) {
        return IntStream.rangeClosed(1, n).reduce((x, y) -> x * y).getAsInt();
    }
}

package alankstewart.threeofacrime;

import java.util.Iterator;
import java.util.stream.IntStream;

public final class SuspectCardIterator implements Iterator<SuspectCard> {

    private static final Suspect[] SUSPECTS = Suspect.values();
    private static final int LENGTH_K = 3;
    private static final int LENGTH_N = 7;

    private final SuspectCardBuilder suspectCardBuilder = new SuspectCardBuilder();
    private final int[] intArray = IntStream.rangeClosed(0, LENGTH_K).toArray();
    private int endIndex = 1;

    @Override
    public boolean hasNext() {
        return endIndex > 0;
    }

    @Override
    public SuspectCard next() {
        IntStream.rangeClosed(1, LENGTH_K).forEach(i -> suspectCardBuilder.setSuspect(i - 1, SUSPECTS[intArray[i] - 1]));
        endIndex = LENGTH_K;
        while (intArray[endIndex] == LENGTH_N - LENGTH_K + endIndex) {
            endIndex--;
            if (endIndex == 0) {
                break;
            }
        }

        intArray[endIndex]++;
        IntStream.rangeClosed(endIndex + 1, LENGTH_K).forEach(i -> intArray[i] = intArray[i - 1] + 1);
        return suspectCardBuilder.build();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

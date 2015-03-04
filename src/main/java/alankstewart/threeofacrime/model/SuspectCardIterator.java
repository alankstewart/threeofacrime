package alankstewart.threeofacrime.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.IntStream.rangeClosed;

final class SuspectCardIterator implements Iterator<SuspectCard> {

    private static final int LENGTH_K = 3;
    private static final int LENGTH_N = 7;

    private final List<Suspect> suspects = new ArrayList<>();
    private final int[] array = rangeClosed(0, LENGTH_K).toArray();
    private int endIndex = 1;

    @Override
    public boolean hasNext() {
        return endIndex > 0;
    }

    @Override
    public SuspectCard next() {
        rangeClosed(1, LENGTH_K).forEach(i -> setSuspect(i - 1, Suspect.values()[array[i] - 1]));
        endIndex = LENGTH_K;
        while (array[endIndex] == LENGTH_N - LENGTH_K + endIndex) {
            endIndex--;
            if (endIndex == 0) {
                break;
            }
        }

        array[endIndex]++;
        rangeClosed(endIndex + 1, LENGTH_K).forEach(i -> array[i] = array[i - 1] + 1);
        return SuspectCard.of(suspects.get(0), suspects.get(1), suspects.get(2));
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void setSuspect(final int index, final Suspect suspect) {
        try {
            suspects.set(index, suspect);
        } catch (final IndexOutOfBoundsException e) {
            suspects.add(index, suspect);
        }
    }
}

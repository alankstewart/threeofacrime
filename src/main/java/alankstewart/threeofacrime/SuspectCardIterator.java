package alankstewart.threeofacrime;
import java.util.Iterator;
import java.util.stream.IntStream;

public final class SuspectCardIterator implements Iterator<SuspectCard> {

    private static final Suspect[] SUSPECTS = Suspect.values();
    private static final int LENGTH_K = 3;
    private static final int LENGTH_N = 7;

    private final SuspectCard suspectCard = new SuspectCard();
    private final int[] bitVector = IntStream.rangeClosed(0, LENGTH_K).toArray();
    private int endIndex = 1;

    @Override
    public boolean hasNext() {
        return endIndex > 0;
    }

    @Override
    public SuspectCard next() {
        IntStream.rangeClosed(1, LENGTH_K).forEach(i -> suspectCard.setValue(i - 1, SUSPECTS[bitVector[i] - 1]));
        endIndex = LENGTH_K;
        while (bitVector[endIndex] == LENGTH_N - LENGTH_K + endIndex) {
            endIndex--;
            if (endIndex == 0) {
                break;
            }
        }

        bitVector[endIndex]++;
        IntStream.rangeClosed(endIndex + 1, LENGTH_K).forEach(i -> bitVector[i] = bitVector[i - 1] + 1);
        return new SuspectCard(suspectCard.getSuspects());
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

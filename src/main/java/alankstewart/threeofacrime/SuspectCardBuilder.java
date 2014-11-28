package alankstewart.threeofacrime;

import java.util.ArrayList;
import java.util.List;

public final class SuspectCardBuilder {

    private final List<Suspect> suspects = new ArrayList<>();

    public SuspectCard build() {
        if (suspects.size() != 3) {
            throw new IllegalStateException("Three suspects required");
        }
        return new SuspectCard(suspects.get(0), suspects.get(1), suspects.get(2));
    }

    public SuspectCardBuilder setSuspect(final int index, final Suspect suspect) {
        try {
            suspects.set(index, suspect);
        } catch (final IndexOutOfBoundsException e) {
            suspects.add(index, suspect);
        }
        return this;
    }
}
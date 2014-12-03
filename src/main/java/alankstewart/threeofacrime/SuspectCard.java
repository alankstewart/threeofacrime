package alankstewart.threeofacrime;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SuspectCard {

    private final List<Suspect> suspects;

    public SuspectCard(final Suspect suspect1, final Suspect suspect2, final Suspect suspect3) {
        suspects = Stream.of(suspect1, suspect2, suspect3)
                .distinct()
                .filter(Objects::nonNull)
                .limit(3)
                .collect(Collectors.toList());

        if (suspects.size() != 3) {
            throw new IllegalArgumentException("Must have three non-null unique suspects");
        }
    }

    public List<Suspect> getSuspects() {
        return suspects;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof SuspectCard)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final SuspectCard that = (SuspectCard) obj;
        return suspects.containsAll(that.getSuspects());
    }

    @Override
    public int hashCode() {
        return suspects.hashCode();
    }

    @Override
    public String toString() {
        return suspects.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
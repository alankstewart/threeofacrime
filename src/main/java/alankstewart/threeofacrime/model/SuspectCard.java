package alankstewart.threeofacrime.model;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static alankstewart.threeofacrime.model.Suspect.from;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public final class SuspectCard implements Iterable<Suspect> {

    private final List<Suspect> suspects;

    private SuspectCard(final Suspect suspect1, final Suspect suspect2, final Suspect suspect3) {
        suspects = Stream.of(suspect1, suspect2, suspect3)
                .distinct()
                .filter(Objects::nonNull)
                .limit(3)
                .collect(toList());

        if (suspects.size() != 3) {
            throw new IllegalArgumentException("Must have three non-null unique suspects");
        }
    }

    public static SuspectCard of(final Suspect suspect1, final Suspect suspect2, final Suspect suspect3) {
        return new SuspectCard(suspect1, suspect2, suspect3);
    }

    public static SuspectCard of(final String suspect1, final String suspect2, final String suspect3) {
        return of(from(suspect1), from(suspect2), from(suspect3));
    }

    @Override
    public Iterator<Suspect> iterator() {
        return suspects.iterator();
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
        final var that = (SuspectCard) obj;
        return suspects.containsAll(that.getSuspects());
    }

    @Override
    public int hashCode() {
        return suspects.stream().mapToInt(Enum::hashCode).sum();
    }

    @Override
    public String toString() {
        return suspects.stream().map(Object::toString).collect(joining(", "));
    }
}
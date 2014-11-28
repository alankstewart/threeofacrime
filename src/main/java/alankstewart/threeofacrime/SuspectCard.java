package alankstewart.threeofacrime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class SuspectCard implements Iterable<Suspect> {

    private final List<Suspect> suspects;

    public SuspectCard() {
        suspects = new ArrayList<>();
    }

    public SuspectCard(final List<Suspect> suspects) {
        Objects.requireNonNull(suspects);
        this.suspects = suspects.stream().collect(toList());
    }

    public List<Suspect> getSuspects() {
        return suspects;
    }

    @Override
    public Iterator<Suspect> iterator() {
        return suspects.iterator();
    }

    public void setValue(final int index, final Suspect suspect) {
        try {
            suspects.set(index, suspect);
        } catch (final IndexOutOfBoundsException e) {
            suspects.add(index, suspect);
        }
    }

    @Override
    public String toString() {
        return suspects.toString();
    }
}
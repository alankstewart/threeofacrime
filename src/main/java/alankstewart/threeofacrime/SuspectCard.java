package alankstewart.threeofacrime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class SuspectCard implements Iterable<Suspect> {

//    private final Suspect suspect1;
//    private final Suspect suspect2;
//    private final Suspect suspect3;

    List<Suspect> suspects;

//    public SuspectCard(Suspect suspect1, Suspect suspect2, Suspect suspect3) {
//        this.suspect1 = suspect1;
//        this.suspect2 = suspect2;
//        this.suspect3 = suspect3;
//    }

    public SuspectCard() {
        suspects = new ArrayList<>();
    }

    public SuspectCard(List<Suspect> suspects) {
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

    public void setValue(int index, Suspect suspect) {
        try {
            suspects.set(index, suspect);
        } catch (IndexOutOfBoundsException e) {
            suspects.add(index, suspect);
        }
    }
//    public Suspect getSuspect1() {
//        return suspect1;
//    }
//
//    public Suspect getSuspect2() {
//        return suspect2;
//    }
//
//    public Suspect getSuspect3() {
//        return suspect3;
//    }

    @Override
    public String toString() {
        return "SuspectCard{" +
                "suspects=" + suspects +
                '}';
    }
}

package alankstewart.threeofacrime;

import java.util.StringJoiner;

public final class SuspectCard {

    private final Suspect suspect1;
    private final Suspect suspect2;
    private final Suspect suspect3;

    SuspectCard(final Suspect suspect1, final Suspect suspect2, final Suspect suspect3) {
        this.suspect1 = suspect1;
        this.suspect2 = suspect2;
        this.suspect3 = suspect3;
    }

    public Suspect getSuspect1() {
        return suspect1;
    }

    public Suspect getSuspect2() {
        return suspect2;
    }

    public Suspect getSuspect3() {
        return suspect3;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add(suspect1.toString())
                .add(suspect2.toString())
                .add(suspect3.toString())
                .toString();
    }
}
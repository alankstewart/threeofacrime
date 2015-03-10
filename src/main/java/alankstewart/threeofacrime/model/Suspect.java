package alankstewart.threeofacrime.model;

import java.util.Arrays;

public enum Suspect {

    HUMPTY_BUMPTY("HUMPTY BUMPTY"),
    JONNY_CORTEX("JONNY CORTEX"),
    KID_CASSIDY("KID CASSIDY"),
    LOOSE_EYE_LENNY("LOOSE-EYE LENNY"),
    LOUIE_ST_LOUIS("LOUIE ST. LOUIS"),
    NO_NECK_NICK("NO NECK NICK"),
    PENCIL_TOP("PENCIL TOP");

    private final String name;

    private Suspect(final String name) {
        this.name = name;
    }

    public static Suspect from(final String suspect) {
        return Arrays.stream(values())
                .filter(s -> s.name().equalsIgnoreCase(suspect) || s.name.equalsIgnoreCase(suspect))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unknown suspect '%s'", suspect)));
    }

    @Override
    public String toString() {
        return name;
    }
}

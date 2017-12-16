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

    private final String displayName;

    Suspect(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Suspect from(final String suspect) {
        return Arrays.stream(values())
                .filter(s -> s.name().equalsIgnoreCase(suspect.trim()) || s.displayName.equalsIgnoreCase(suspect.trim()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unknown suspect '%s'", suspect)));
    }

    @Override
    public String toString() {
        return displayName;
    }
}

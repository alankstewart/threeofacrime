package alankstewart.threeofacrime;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

    public static List<String> getAll() {
        return Arrays.asList(Suspect.values()).stream().map(Suspect::toString).collect(toList());
    }

    @Override
    public String toString() {
        return name;
    }
}

package alankstewart.threeofacrime;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public enum Suspect {

    HUMPTY_BUMPTY("HUMPTY BUMPTY", "hb"),
    JONNY_CORTEX("JONNY CORTEX", "jc"),
    KID_CASSIDY("KID CASSIDY", "kc"),
    LOOSE_EYE_LENNY("LOOSE-EYE LENNY", "lel"),
    LOUIE_ST_LOUIS("LOUIE ST. LOUIS", "lsl"),
    NO_NECK_NICK("NO NECK NICK", "nnn"),
    PENCIL_TOP("PENCIL TOP", "pt");

    private final String name;
    private final String abbreviation;

    private Suspect(final String name, final String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public static Suspect from(final String suspect) {
        return Arrays.stream(values())
                .filter(s -> s.name.equalsIgnoreCase(suspect) || s.abbreviation.equalsIgnoreCase(suspect))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported suspect %s", suspect)));
    }

    public static List<String> getAll() {
        return Arrays.stream(values()).map(Suspect::toString).collect(toList());
    }

    @Override
    public String toString() {
        return name;
    }
}

package alankstewart.threeofacrime;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public enum Suspect {

    HUMPTY_BUMPTY,
    JONNY_CORTEX,
    KID_CASSIDY,
    LOOSE_EYE_LENNY,
    LOUIE_ST_LOUIS,
    NO_NECK_NICK,
    PENCIL_TOP;

    public static List<String> getNames() {
        return Arrays.asList(Suspect.values()).stream().map(Suspect::name).collect(toList());
    }
}

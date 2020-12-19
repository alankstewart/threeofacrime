package alankstewart.threeofacrime.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static alankstewart.threeofacrime.model.Suspect.HUMPTY_BUMPTY;
import static alankstewart.threeofacrime.model.Suspect.LOOSE_EYE_LENNY;
import static alankstewart.threeofacrime.model.Suspect.LOUIE_ST_LOUIS;
import static alankstewart.threeofacrime.model.Suspect.NO_NECK_NICK;
import static alankstewart.threeofacrime.model.Suspect.PENCIL_TOP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuspectCardTest {

    @Test
    public void shouldNotCreateSuspectCardWithAnyNullSuspects() {
        shouldNotCreateSuspectCard(null, NO_NECK_NICK, null);
    }

    @Test
    public void shouldNotCreateSuspectCardWithNonUniqueSuspects() {
        shouldNotCreateSuspectCard(NO_NECK_NICK, NO_NECK_NICK, PENCIL_TOP);
    }

    @Test
    public void shouldCreateSuspectCardWithUniqueSuspects() {
        var suspectCard = SuspectCard.of(NO_NECK_NICK, HUMPTY_BUMPTY, PENCIL_TOP);
        assertNotNull( suspectCard);
        assertEquals(3, suspectCard.getSuspects().size());
    }

    @Test
    public void shouldConvertStringsToSuspect() {
        var suspectCard = SuspectCard.of("HUMPTY BUMPTY", "LOOSE-EYE LENNY", "NO NECK NICK");
        assertNotNull( suspectCard);
        suspectCard = SuspectCard.of("humpty bumpty", "LOOSE-EYE lenny", "No Neck Nick");
        assertNotNull( suspectCard);
    }

    @Test
    public void shouldNotConvertStringsToSuspect() {
        var malformedSuspect = "HUMPTY BUMPTY1";
        Assertions.assertThrows(IllegalArgumentException.class, () -> SuspectCard.of(malformedSuspect, "LOOSE-EYE LENNY", "NO NECK NICK"));
    }

    @Test
    public void shouldBeEqual() {
        assertTrue(SuspectCard.of(LOOSE_EYE_LENNY, PENCIL_TOP, LOUIE_ST_LOUIS).equals(SuspectCard.of(LOUIE_ST_LOUIS, LOOSE_EYE_LENNY, PENCIL_TOP)));
    }

    @Test
    public void shouldHaveSameHashCodes() {
        assertEquals(SuspectCard.of(LOOSE_EYE_LENNY, PENCIL_TOP, LOUIE_ST_LOUIS).hashCode(), SuspectCard.of(LOUIE_ST_LOUIS, LOOSE_EYE_LENNY, PENCIL_TOP).hashCode());
    }

    @Test
    public void shouldHaveDifferentHashCodes() {
        assertNotEquals(SuspectCard.of(HUMPTY_BUMPTY, LOOSE_EYE_LENNY, PENCIL_TOP).hashCode(), SuspectCard.of(LOUIE_ST_LOUIS, LOOSE_EYE_LENNY, PENCIL_TOP).hashCode());
    }

    private void shouldNotCreateSuspectCard(final Suspect suspect1, final Suspect suspect2, final Suspect suspect3) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> SuspectCard.of(suspect1, suspect2, suspect3));
    }
}

package alankstewart.threeofacrime.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static alankstewart.threeofacrime.model.Suspect.HUMPTY_BUMPTY;
import static alankstewart.threeofacrime.model.Suspect.LOOSE_EYE_LENNY;
import static alankstewart.threeofacrime.model.Suspect.LOUIE_ST_LOUIS;
import static alankstewart.threeofacrime.model.Suspect.NO_NECK_NICK;
import static alankstewart.threeofacrime.model.Suspect.PENCIL_TOP;
import static alankstewart.threeofacrime.model.SuspectCard.of;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SuspectCardTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
        final SuspectCard suspectCard = of(NO_NECK_NICK, HUMPTY_BUMPTY, PENCIL_TOP);
        assertThat(suspectCard, is(notNullValue()));
        assertEquals(3, suspectCard.getSuspects().size());
    }

    @Test
    public void shouldConvertStringsToSuspect() {
        SuspectCard suspectCard = of("HUMPTY BUMPTY", "LOOSE-EYE LENNY", "NO NECK NICK");
        assertThat(suspectCard, is(notNullValue()));
        suspectCard = of("humpty bumpty", "LOOSE-EYE lenny", "No Neck Nick");
        assertThat(suspectCard, is(notNullValue()));
    }

    @Test
    public void shouldNotConvertStringsToSuspect() {
        final String malformedSuspect = "HUMPTY BUMPTY1";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Unsupported suspect " + malformedSuspect);
        of(malformedSuspect, "LOOSE-EYE LENNY", "NO NECK NICK");
    }

    @Test
    public void shouldBeEqual() {
        assertTrue(of(LOOSE_EYE_LENNY, PENCIL_TOP, LOUIE_ST_LOUIS).equals(of(LOUIE_ST_LOUIS, LOOSE_EYE_LENNY, PENCIL_TOP)));
    }

    @Test
    public void shouldHaveSameHashCodes() {
        assertEquals(of(LOOSE_EYE_LENNY, PENCIL_TOP, LOUIE_ST_LOUIS).hashCode(), of(LOUIE_ST_LOUIS, LOOSE_EYE_LENNY, PENCIL_TOP).hashCode());
    }

    @Test
    public void shouldHaveDifferentHashCodes() {
        assertNotEquals(of(HUMPTY_BUMPTY, LOOSE_EYE_LENNY, PENCIL_TOP).hashCode(), of(LOUIE_ST_LOUIS, LOOSE_EYE_LENNY, PENCIL_TOP).hashCode());
    }

    private void shouldNotCreateSuspectCard(final Suspect suspect1, final Suspect suspect2, final Suspect suspect3) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Must have three non-null unique suspects");
        of(suspect1, suspect2, suspect3);
    }
}

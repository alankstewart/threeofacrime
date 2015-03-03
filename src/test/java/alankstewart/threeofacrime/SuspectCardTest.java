package alankstewart.threeofacrime;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static alankstewart.threeofacrime.Suspect.HUMPTY_BUMPTY;
import static alankstewart.threeofacrime.Suspect.NO_NECK_NICK;
import static alankstewart.threeofacrime.Suspect.PENCIL_TOP;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SuspectCardTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldNotCreateSuspectCardWithAnyNullSuspects() {
        shouldNotCreateSuspectCard(null, NO_NECK_NICK, null);
    }

    @Test
    public void shouldNotCreateSuspectCardWithRepeatedSuspects() {
        shouldNotCreateSuspectCard(NO_NECK_NICK, NO_NECK_NICK, PENCIL_TOP);
    }

    @Test
    public void shouldCreateSuspectCardWithUniqueSuspects() {
        final SuspectCard suspectCard = SuspectCard.of(NO_NECK_NICK, HUMPTY_BUMPTY, PENCIL_TOP);
        assertThat(suspectCard, is(notNullValue()));
        assertEquals(3, suspectCard.getSuspects().size());
    }

    private void shouldNotCreateSuspectCard(Suspect suspect1, Suspect suspect2, Suspect suspect3) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Must have three non-null unique suspects");
        SuspectCard.of(suspect1, suspect2, suspect3);
    }
}

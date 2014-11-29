package alankstewart.threeofacrime;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static alankstewart.threeofacrime.Suspect.HUMPTY_BUMPTY;
import static alankstewart.threeofacrime.Suspect.NO_NECK_NICK;
import static alankstewart.threeofacrime.Suspect.PENCIL_TOP;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SuspectCardTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldNotCreateSuspectCardWitNullSuspects() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Must have three non-null unique suspects");

        new SuspectCard(null, NO_NECK_NICK, null);
    }

    @Test
    public void shouldNotCreateSuspectCardWithNonUniqueSuspects() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Must have three non-null unique suspects");

        new SuspectCard(NO_NECK_NICK, NO_NECK_NICK, PENCIL_TOP);
    }

    @Test
    public void shouldCreateSuspectCardWitUniqueSuspects() {
        final SuspectCard suspectCard = new SuspectCard(NO_NECK_NICK, HUMPTY_BUMPTY, PENCIL_TOP);
        assertThat(suspectCard, is(notNullValue()));
    }
}

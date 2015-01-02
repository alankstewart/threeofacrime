package alankstewart.threeofacrime;

import org.junit.Test;

import static alankstewart.threeofacrime.Suspect.HUMPTY_BUMPTY;
import static alankstewart.threeofacrime.Suspect.JONNY_CORTEX;
import static alankstewart.threeofacrime.Suspect.KID_CASSIDY;
import static alankstewart.threeofacrime.Suspect.LOOSE_EYE_LENNY;
import static alankstewart.threeofacrime.Suspect.NO_NECK_NICK;
import static alankstewart.threeofacrime.Suspect.PENCIL_TOP;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class ThreeOfACrimeTest {

    private static final SuspectCard SUSPECT_CARD = SuspectCard.of(NO_NECK_NICK, KID_CASSIDY, LOOSE_EYE_LENNY);

    @Test
    public void shouldGenerateRandomSuspectCards() {
        assertThat(ThreeOfACrime.start().printSuspectCards().getSuspectCards(), hasSize(35));
    }

    @Test
    public void shouldMatchZeroSuspects() {
        assertThat(ThreeOfACrime.start().matchZeroSuspects(SUSPECT_CARD).printSuspectCards().getSuspectCards(), hasSize(4));
    }

    @Test
    public void shouldMatchOneSuspect() {
        assertThat(ThreeOfACrime.start().matchOneSuspect(SUSPECT_CARD).printSuspectCards().getSuspectCards(), hasSize(18));
    }

    @Test
    public void shouldMatchTwoSuspects() {
        assertThat(ThreeOfACrime.start().matchTwoSuspects(SUSPECT_CARD).printSuspectCards().getSuspectCards(), hasSize(12));
    }

    @Test
    public void shouldPlayGame1() {
        ThreeOfACrime threeOfACrime = ThreeOfACrime.start();
        assertThat(threeOfACrime.matchZeroSuspects(SUSPECT_CARD).getSuspectCards(), hasSize(4));
        assertThat(threeOfACrime.matchTwoSuspects(SuspectCard.of(HUMPTY_BUMPTY, JONNY_CORTEX, PENCIL_TOP))
                .printSuspectCards().getSuspectCards(), hasSize(3));
    }

    @Test
    public void shouldPlayGame2() {
        ThreeOfACrime threeOfACrime = ThreeOfACrime.start();
        assertThat(threeOfACrime.matchOneSuspect(SUSPECT_CARD).getSuspectCards(), hasSize(18));
        assertThat(threeOfACrime.matchOneSuspect(SuspectCard.of(HUMPTY_BUMPTY, JONNY_CORTEX, PENCIL_TOP)).getSuspectCards(), hasSize(9));
        assertThat(threeOfACrime.matchTwoSuspects(SuspectCard.of(HUMPTY_BUMPTY, LOOSE_EYE_LENNY, PENCIL_TOP))
                .printSuspectCards().getSuspectCards(), hasSize(2));
    }
}

package alankstewart.threeofacrime;

import org.junit.Test;

import static alankstewart.threeofacrime.Suspect.HUMPTY_BUMPTY;
import static alankstewart.threeofacrime.Suspect.JONNY_CORTEX;
import static alankstewart.threeofacrime.Suspect.KID_CASSIDY;
import static alankstewart.threeofacrime.Suspect.LOOSE_EYE_LENNY;
import static alankstewart.threeofacrime.Suspect.LOUIE_ST_LOUIS;
import static alankstewart.threeofacrime.Suspect.NO_NECK_NICK;
import static alankstewart.threeofacrime.Suspect.PENCIL_TOP;
import static org.junit.Assert.assertEquals;

public class ThreeOfACrimeTest {

    private static final SuspectCard SUSPECT_CARD1 = SuspectCard.of(NO_NECK_NICK, KID_CASSIDY, LOOSE_EYE_LENNY);
    private static final SuspectCard SUSPECT_CARD2 = SuspectCard.of(HUMPTY_BUMPTY, JONNY_CORTEX, PENCIL_TOP);

    @Test
    public void shouldGenerateRandomSuspectCards() {
        ThreeOfACrime threeOfACrime = new ThreeOfACrime();
        assertEquals(35, threeOfACrime.getSuspectCards().size());
    }

    @Test
    public void shouldMatchZeroSuspects() {
        ThreeOfACrime threeOfACrime = new ThreeOfACrime();
        threeOfACrime.matchZeroSuspects(SUSPECT_CARD1);
        assertEquals(4, threeOfACrime.getSuspectCards().size());
    }

    @Test
    public void shouldMatchOneSuspect() {
        ThreeOfACrime threeOfACrime = new ThreeOfACrime();
        threeOfACrime.matchOneSuspect(SUSPECT_CARD1);
        assertEquals(18, threeOfACrime.getSuspectCards().size());
    }

    @Test
    public void shouldMatchTwoSuspects() {
        ThreeOfACrime threeOfACrime = new ThreeOfACrime();
        threeOfACrime.matchTwoSuspects(SUSPECT_CARD1);
        assertEquals(12, threeOfACrime.getSuspectCards().size());
    }

    @Test
    public void shouldPlayGame1() {
        ThreeOfACrime threeOfACrime = new ThreeOfACrime();
        threeOfACrime.matchZeroSuspects(SUSPECT_CARD1);
        assertEquals(4, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchTwoSuspects(SUSPECT_CARD2);
        assertEquals(3, threeOfACrime.getSuspectCards().size());
    }

    @Test
    public void shouldPlayGame2() {
        ThreeOfACrime threeOfACrime = new ThreeOfACrime(SuspectCard.of(LOOSE_EYE_LENNY, LOUIE_ST_LOUIS, PENCIL_TOP));
        threeOfACrime.matchOneSuspect(SUSPECT_CARD1);
        assertEquals(18, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchOneSuspect(SUSPECT_CARD2);
        assertEquals(9, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchTwoSuspects(SuspectCard.of(HUMPTY_BUMPTY, LOOSE_EYE_LENNY, PENCIL_TOP));
        assertEquals(2, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchTwoSuspects(SuspectCard.of(HUMPTY_BUMPTY, LOOSE_EYE_LENNY, LOUIE_ST_LOUIS));
        assertEquals(1, threeOfACrime.getSuspectCards().size());
        assertEquals(threeOfACrime.getSuspectCards().get(0), threeOfACrime.getSuspectCard());
        threeOfACrime.printSuspectCards();
    }
}

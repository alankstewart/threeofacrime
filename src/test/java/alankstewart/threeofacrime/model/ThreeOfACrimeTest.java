package alankstewart.threeofacrime.model;


import org.junit.jupiter.api.Test;

import static alankstewart.threeofacrime.model.Suspect.HUMPTY_BUMPTY;
import static alankstewart.threeofacrime.model.Suspect.JONNY_CORTEX;
import static alankstewart.threeofacrime.model.Suspect.KID_CASSIDY;
import static alankstewart.threeofacrime.model.Suspect.LOOSE_EYE_LENNY;
import static alankstewart.threeofacrime.model.Suspect.LOUIE_ST_LOUIS;
import static alankstewart.threeofacrime.model.Suspect.NO_NECK_NICK;
import static alankstewart.threeofacrime.model.Suspect.PENCIL_TOP;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ThreeOfACrimeTest {

    private static final SuspectCard SUSPECT_CARD1 = SuspectCard.of(NO_NECK_NICK, KID_CASSIDY, LOOSE_EYE_LENNY);
    private static final SuspectCard SUSPECT_CARD2 = SuspectCard.of(HUMPTY_BUMPTY, JONNY_CORTEX, PENCIL_TOP);

    @Test
    public void shouldGenerateRandomSuspectCards() {
        var threeOfACrime = new ThreeOfACrime();
        assertEquals(35, threeOfACrime.getSuspectCards().size());
    }

    @Test
    public void shouldMatchZeroSuspects() {
        var threeOfACrime = new ThreeOfACrime();
        threeOfACrime.matchSuspects(SUSPECT_CARD1, 0);
        assertEquals(4, threeOfACrime.getSuspectCards().size());
    }

    @Test
    public void shouldMatchOneSuspect() {
        var threeOfACrime = new ThreeOfACrime();
        threeOfACrime.matchSuspects(SUSPECT_CARD1, 1);
        assertEquals(18, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchSuspects(SUSPECT_CARD2, 1);
        assertEquals(9, threeOfACrime.getSuspectCards().size());
    }

    @Test
    public void shouldMatchTwoSuspects() {
        var threeOfACrime = new ThreeOfACrime();
        threeOfACrime.matchSuspects(SUSPECT_CARD1, 2);
        assertEquals(12, threeOfACrime.getSuspectCards().size());
    }

    @Test
    public void shouldPlayGame() {
        var threeOfACrime = new ThreeOfACrime();

        threeOfACrime.matchSuspects(SUSPECT_CARD1, 1);
        assertEquals(18, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchSuspects(SUSPECT_CARD2, 1);
        assertEquals(9, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchSuspects(SuspectCard.of(HUMPTY_BUMPTY, LOOSE_EYE_LENNY, PENCIL_TOP), 2);
        assertEquals(2, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchSuspects(SuspectCard.of(HUMPTY_BUMPTY, LOOSE_EYE_LENNY, LOUIE_ST_LOUIS), 2);
        assertEquals(1, threeOfACrime.getSuspectCards().size());
        assertEquals(SuspectCard.of(LOOSE_EYE_LENNY, LOUIE_ST_LOUIS, PENCIL_TOP), threeOfACrime.getSuspectCards().get(0));
        threeOfACrime.printSuspectCards();

        threeOfACrime.startNewRound();
        threeOfACrime.matchSuspects(SUSPECT_CARD1, 1);
        assertEquals(17, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchSuspects(SUSPECT_CARD2, 1);
        assertEquals(8, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchSuspects(SuspectCard.of(HUMPTY_BUMPTY, LOOSE_EYE_LENNY, PENCIL_TOP), 2);
        assertEquals(1, threeOfACrime.getSuspectCards().size());
        assertEquals(SuspectCard.of(HUMPTY_BUMPTY, LOOSE_EYE_LENNY, LOUIE_ST_LOUIS), threeOfACrime.getSuspectCards().get(0));
        threeOfACrime.printSuspectCards();

        threeOfACrime.startNewRound();
        threeOfACrime.matchSuspects(SUSPECT_CARD1, 1);
        assertEquals(16, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchSuspects(SUSPECT_CARD2, 1);
        assertEquals(7, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchSuspects(SuspectCard.of(HUMPTY_BUMPTY, KID_CASSIDY, PENCIL_TOP), 2);
        assertEquals(2, threeOfACrime.getSuspectCards().size());
        threeOfACrime.matchSuspects(SuspectCard.of(LOUIE_ST_LOUIS, KID_CASSIDY, PENCIL_TOP), 2);
        assertEquals(1, threeOfACrime.getSuspectCards().size());
        assertEquals(SuspectCard.of(HUMPTY_BUMPTY, KID_CASSIDY, LOUIE_ST_LOUIS), threeOfACrime.getSuspectCards().get(0));
        threeOfACrime.printSuspectCards();

        threeOfACrime.startNewRound();
        assertEquals(32, threeOfACrime.getSuspectCards().size());

        threeOfACrime = new ThreeOfACrime();
        assertEquals(35, threeOfACrime.getSuspectCards().size());
    }
}

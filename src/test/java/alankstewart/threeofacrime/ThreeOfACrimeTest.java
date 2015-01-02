package alankstewart.threeofacrime;

import org.junit.Test;

import java.util.List;

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
        ThreeOfACrime threeOfACrime = ThreeOfACrime.start();
        List<SuspectCard> suspectCards = threeOfACrime.getSuspectCards();
        assertThat(suspectCards, hasSize(35));
        suspectCards.forEach(System.out::println);
    }

    @Test
    public void shouldMatchZeroSuspects() {
        ThreeOfACrime threeOfACrime = ThreeOfACrime.start();
        assertThat(threeOfACrime.matchZeroSuspects(SUSPECT_CARD), hasSize(4));
        printSuspectCards(threeOfACrime);
    }

    @Test
    public void shouldMatchOneSuspect() {
        ThreeOfACrime threeOfACrime = ThreeOfACrime.start();
        assertThat(threeOfACrime.matchOneSuspect(SUSPECT_CARD), hasSize(18));
        printSuspectCards(threeOfACrime);
    }

    @Test
    public void shouldMatchTwoSuspects() {
        ThreeOfACrime threeOfACrime = ThreeOfACrime.start();
        assertThat(threeOfACrime.matchTwoSuspects(SUSPECT_CARD), hasSize(12));
        printSuspectCards(threeOfACrime);
    }

    @Test
    public void shouldPlayGame1() {
        ThreeOfACrime threeOfACrime = ThreeOfACrime.start();
        assertThat(threeOfACrime.matchZeroSuspects(SUSPECT_CARD), hasSize(4));
        assertThat(threeOfACrime.matchTwoSuspects(SuspectCard.of(HUMPTY_BUMPTY, JONNY_CORTEX, PENCIL_TOP)), hasSize(3));
        printSuspectCards(threeOfACrime);
    }

    @Test
    public void shouldPlayGame2() {
        ThreeOfACrime threeOfACrime = ThreeOfACrime.start();
        assertThat(threeOfACrime.matchOneSuspect(SUSPECT_CARD), hasSize(18));
        assertThat(threeOfACrime.matchOneSuspect(SuspectCard.of(HUMPTY_BUMPTY, JONNY_CORTEX, PENCIL_TOP)), hasSize(9));
        assertThat(threeOfACrime.matchTwoSuspects(SuspectCard.of(HUMPTY_BUMPTY, LOOSE_EYE_LENNY, PENCIL_TOP)), hasSize(2));
        printSuspectCards(threeOfACrime);
    }

    private void printSuspectCards(ThreeOfACrime threeOfACrime) {
        threeOfACrime.getSuspectCards().forEach(System.out::println);
    }
}

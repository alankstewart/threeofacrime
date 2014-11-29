package alankstewart.threeofacrime;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class ThreeOfACrimeTest {

    @Test
    public void shouldGenerateRandomPerpetrators() {
        ThreeOfACrime threeOfACrime = new ThreeOfACrime();

        List<SuspectCard> suspectCards = threeOfACrime.getSuspectCards();
        assertThat(suspectCards, hasSize(35));
        suspectCards.forEach(System.out::println);

        assertThat(threeOfACrime.getSuspectCard(Suspect.NO_NECK_NICK, Suspect.HUMPTY_BUMPTY, Suspect.PENCIL_TOP), is(true));
        assertThat(threeOfACrime.getSuspectCard(Suspect.KID_CASSIDY, Suspect.HUMPTY_BUMPTY, Suspect.PENCIL_TOP), is(true));

        SuspectCard suspectCard = threeOfACrime.getNextSuspectCard();
        assertThat(suspectCard, is(notNullValue()));

        suspectCards = threeOfACrime.getSuspectCards();
        assertThat(suspectCards, hasSize(34));
        assertThat(suspectCards, not(hasItem(suspectCard)));

        suspectCard = threeOfACrime.getNextSuspectCard();
        assertThat(suspectCard, is(notNullValue()));
        suspectCards = threeOfACrime.getSuspectCards();
        assertThat(suspectCards, hasSize(33));
        assertThat(suspectCards, not(hasItem(suspectCard)));


    }
}

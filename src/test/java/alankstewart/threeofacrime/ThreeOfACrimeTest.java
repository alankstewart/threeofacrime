package alankstewart.threeofacrime;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static alankstewart.threeofacrime.Suspect.HUMPTY_BUMPTY;
import static alankstewart.threeofacrime.Suspect.NO_NECK_NICK;
import static alankstewart.threeofacrime.Suspect.PENCIL_TOP;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class ThreeOfACrimeTest {

    @Test
    public void shouldGenerateRandomSuspects() {
        ThreeOfACrime threeOfACrime = new ThreeOfACrime();

        List<SuspectCard> suspectCards = threeOfACrime.getSuspectCards();
        assertThat(suspectCards, hasSize(35));
        suspectCards.forEach(System.out::println);

        Optional<SuspectCard> suspectCard = threeOfACrime.getNextSuspectCard();
        assertThat(suspectCard.isPresent(), is(true));

        suspectCards = threeOfACrime.getSuspectCards();
        assertThat(suspectCards, hasSize(34));
        assertThat(suspectCards, not(hasItem(suspectCard.get())));

        suspectCard = threeOfACrime.getNextSuspectCard();
        assertThat(suspectCard, is(notNullValue()));
        suspectCards = threeOfACrime.getSuspectCards();
        assertThat(suspectCards, hasSize(33));
        assertThat(suspectCards, not(hasItem(suspectCard.get())));
    }

    @Test
    public void shouldFindAndRemoveSuspectCard() {
        ThreeOfACrime threeOfACrime = new ThreeOfACrime();

        List<SuspectCard> suspectCards = threeOfACrime.getSuspectCards();
        assertThat(suspectCards, hasSize(35));

        Optional<SuspectCard> suspectCard = threeOfACrime.getSuspectCard(NO_NECK_NICK, HUMPTY_BUMPTY, PENCIL_TOP);
        assertThat(suspectCard.isPresent(), is(true));
        suspectCards = threeOfACrime.getSuspectCards();
        assertThat(suspectCards, hasSize(34));
        assertThat(suspectCards, not(hasItem(suspectCard.get())));
    }
}

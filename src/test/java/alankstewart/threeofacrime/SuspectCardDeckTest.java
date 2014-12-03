package alankstewart.threeofacrime;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static alankstewart.threeofacrime.Suspect.HUMPTY_BUMPTY;
import static alankstewart.threeofacrime.Suspect.NO_NECK_NICK;
import static alankstewart.threeofacrime.Suspect.PENCIL_TOP;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class SuspectCardDeckTest {

    @Test
    public void shouldGenerateRandomSuspectCards() {
        SuspectCardDeck deck = new SuspectCardDeck();

        List<SuspectCard> suspectCards = deck.getSuspectCards();
        assertThat(suspectCards, hasSize(35));
        suspectCards.forEach(System.out::println);

        Optional<SuspectCard> suspectCard = deck.getSuspectCard();
        assertThat(suspectCard.isPresent(), is(true));

        suspectCards = deck.getSuspectCards();
        assertThat(suspectCards, hasSize(34));
        assertThat(suspectCards, not(hasItem(suspectCard.get())));

        suspectCard = deck.getSuspectCard();
        assertThat(suspectCard, is(notNullValue()));
        suspectCards = deck.getSuspectCards();
        assertThat(suspectCards, hasSize(33));
        assertThat(suspectCards, not(hasItem(suspectCard.get())));
    }

    @Test
    public void shouldFindAndRemoveSuspectCard() {
        SuspectCardDeck deck = new SuspectCardDeck();

        List<SuspectCard> suspectCards = deck.getSuspectCards();
        assertThat(suspectCards, hasSize(35));

        Optional<SuspectCard> suspectCard = deck.getSuspectCard(NO_NECK_NICK, HUMPTY_BUMPTY, PENCIL_TOP);
        assertThat(suspectCard.isPresent(), is(true));
        suspectCards = deck.getSuspectCards();
        assertThat(suspectCards, hasSize(34));
        assertThat(suspectCards, not(hasItem(suspectCard.get())));
    }

    @Test
    public void shouldFindAndRemoveAllSuspectCards() {
        SuspectCardDeck deck = new SuspectCardDeck();
        List<SuspectCard> suspectCards = deck.getSuspectCards();
        assertThat(suspectCards, hasSize(35));
        IntStream.range(0, 35).forEach(i -> deck.getSuspectCard());
        assertThat(deck.getSuspectCards(), empty());
    }
}

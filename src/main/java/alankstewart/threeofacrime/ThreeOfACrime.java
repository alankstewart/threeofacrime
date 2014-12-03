package alankstewart.threeofacrime;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ThreeOfACrime {

    private SuspectCardDeck deck = new SuspectCardDeck();
    private SuspectCard perpetratorsCard;
    private Set<SuspectCard> twoSuspectsMatched = new HashSet<>();
    private Set<SuspectCard> oneSuspectMatched = new HashSet<>();
    private Set<SuspectCard> zeroSuspectsMatched = new HashSet<>();

    private ThreeOfACrime() {
        perpetratorsCard = deck.getSuspectCard().get();
    }

    public static ThreeOfACrime start() {
        return new ThreeOfACrime();
    }

    public SuspectCardDeck getDeck() {
        return deck;
    }

    public SuspectCard getPerpetratorsCard() {
        return perpetratorsCard;
    }

    public Optional<SuspectCard> getNextSuspectCard() {
        return deck.getSuspectCard();
    }

    public void drawSuspectCard(Suspect suspect1, Suspect suspect2, Suspect suspect3) {
        Optional<SuspectCard> suspectCard = deck.getSuspectCard(suspect1, suspect2, suspect3);
        if (suspectCard.isPresent()) {
            if (Collections.disjoint(perpetratorsCard.getSuspects(), suspectCard.get().getSuspects())) {
                zeroSuspectsMatched.add(suspectCard.get());
            }
        }
    }
}

package alankstewart.threeofacrime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ThreeOfACrime {

    private SuspectCardDeck deck = new SuspectCardDeck();
    private SuspectCard criminalsCard;
    private Set<SuspectCard> twoSuspectsMatched = new HashSet<>();
    private Set<SuspectCard> oneSuspectMatched = new HashSet<>();
    private Set<SuspectCard> zeroSuspectsMatched = new HashSet<>();

    private ThreeOfACrime() {
        criminalsCard = deck.getSuspectCard().get();
    }

    public static ThreeOfACrime start() {
        return new ThreeOfACrime();
    }

    public SuspectCardDeck getDeck() {
        return deck;
    }

    public SuspectCard getCriminalsCard() {
        return criminalsCard;
    }

    public Optional<SuspectCard> getNextSuspectCard() {
        return deck.getSuspectCard();
    }

    public void drawSuspectCard(Suspect suspect1, Suspect suspect2, Suspect suspect3) {
        Optional<SuspectCard> suspectCard = deck.getSuspectCard(suspect1, suspect2, suspect3);
        if (suspectCard.isPresent()) {
            if (Collections.disjoint(criminalsCard.getSuspects(), suspectCard.get().getSuspects())) {
                zeroSuspectsMatched.add(suspectCard.get());
            }
        }
    }
}

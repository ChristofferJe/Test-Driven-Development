package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;

public class CincoDeckStrategy implements DeckStrategy {
    @Override
    public ArrayList<MutableCard> createDeck(Player owner) {
        ArrayList<MutableCard> deck = generateCards(owner);
        return deck;
    }
    public ArrayList<MutableCard> generateCards(Player owner) {
        ArrayList<MutableCard> cards = new ArrayList<>();
        EffectStrategy doNothingEffectStrategy = new DoNothingEffectStrategy();
        for (int i = 0; i < 7; i++) {
            MutableCard card = new StandardCard(GameConstants.CINCO_CARD, 3, 5, 1, owner, doNothingEffectStrategy);
            cards.add(i, card);
        }
        return cards;
    }
}

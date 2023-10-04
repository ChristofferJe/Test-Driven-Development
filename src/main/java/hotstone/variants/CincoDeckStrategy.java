package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.DeckStrategy;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;

public class CincoDeckStrategy implements DeckStrategy {
    @Override
    public ArrayList<Card> createDeck(Player owner) {
        ArrayList<Card> deck = new ArrayList<>();
        addSevenCinco(deck, owner);
        return deck;
    }

    private static void addSevenCinco(ArrayList<Card> deck, Player owner) {
        for (int i=0; i < 7; i++){
            Card card = new StandardCard(GameConstants.CINCO_CARD, 3, 5,1, owner);
            deck.add(i, card);
        }
    }
    @Override
    public void execEffect(Card card) {}
}

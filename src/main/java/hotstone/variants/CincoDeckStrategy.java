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
        Card cinco = new StandardCard(GameConstants.CINCO_CARD, 3, 5,1, owner);
        addSeven(deck, cinco);
        return deck;
    }

    private static void addSeven(ArrayList<Card> deck, Card card) {
        for (int i=0; i < 7; i++){
            deck.add(i, card);
        }
    }
}

package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.CardsStrategy;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;

public class CincoCardsStrategy implements CardsStrategy {
    @Override
    public ArrayList<Card> generateCards(Player owner) {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Card card = new StandardCard(GameConstants.CINCO_CARD, 3, 5, 1, owner);
            cards.add(i, card);
        }
        return cards;
    }
}

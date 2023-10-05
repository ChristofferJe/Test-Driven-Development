package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.CardsStrategy;
import hotstone.framework.DeckStrategy;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;

public class CincoDeckStrategy implements DeckStrategy {
    private final CardsStrategy cardsStrategy;

    public CincoDeckStrategy(){
        this.cardsStrategy = new CincoCardsStrategy();
    }
    @Override
    public ArrayList<Card> createDeck(Player owner) {
        ArrayList<Card> deck = cardsStrategy.generateCards(owner);
        return deck;
    }


    @Override
    public void execEffect(Card card) {}
}

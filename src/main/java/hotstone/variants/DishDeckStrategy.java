package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;
import java.util.Collections;

public class DishDeckStrategy implements DeckStrategy {
    private final CardsStrategy cardsStrategy;
    private final ShuffleStrategy shuffleStrategy;

    public DishDeckStrategy(){
        this.cardsStrategy = new DishCardsStrategy();
        this.shuffleStrategy = new DishDeckShuffleStrategy();
    }

    @Override
    public ArrayList<Card> createDeck(Player owner) {
        ArrayList<Card> deck = cardsStrategy.generateCards(owner);
        shuffleStrategy.orderCards(deck);
        return deck;
    }

    @Override
    public void execEffect(Card card) {}


}

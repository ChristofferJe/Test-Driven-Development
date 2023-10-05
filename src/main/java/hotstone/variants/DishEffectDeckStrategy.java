package hotstone.variants;

import hotstone.framework.*;
import hotstone.variants.*;

import java.util.ArrayList;

public class DishEffectDeckStrategy implements DeckStrategy {
    private final CardsStrategy cardsStrategy;
    private final ShuffleStrategy shuffleStrategy;
    private final EffectStrategy effectStrategy;


    public DishEffectDeckStrategy(){
        cardsStrategy = new DishEffectCardsStrategy();
        shuffleStrategy = new DishDeckShuffleStrategy();
        effectStrategy = new DishDeckEffectStrategy();
    }
    @Override
    public ArrayList<Card> createDeck(Player owner) {
        ArrayList<Card> deck = cardsStrategy.generateCards(owner);
        shuffleStrategy.orderCards(deck);
        return deck;
    }

    @Override
    public void execEffect(Card card) {

    }
}

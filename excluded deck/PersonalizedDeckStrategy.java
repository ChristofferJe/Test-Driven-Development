package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;
import thirdparty.CardPODO;
import thirdparty.PersonalDeckReader;

import java.util.ArrayList;

public class PersonalizedDeckStrategy implements DeckStrategy {
    private final PersonalDeckReader reader;
    private final DishDeckStrategy shuffleStrategy;

    public PersonalizedDeckStrategy(String datafilename){
        reader = new PersonalDeckReader(datafilename);
        shuffleStrategy = new DishDeckStrategy();
    }
    @Override
    public ArrayList<MutableCard> createDeck(Player owner) {
        ArrayList<MutableCard> deck = generateCards(owner);
        shuffleStrategy.orderCards(deck);
        return deck;
    }
    public ArrayList<MutableCard> generateCards(Player owner) {
        ArrayList<MutableCard> cards = new ArrayList<>();
        for (CardPODO acard : reader) {
            for (int i = 0; i < 2; i++) {
                MutableCard card = new StandardCard(acard.name(),
                                                    acard.mana(), acard.attack(), acard.health(),
                                                    owner, new DoNothingEffectStrategy());
                cards.add(card);
            }
        }
        return cards;
    }
}

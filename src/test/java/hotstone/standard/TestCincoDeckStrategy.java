package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.ArrayList;

public class TestCincoDeckStrategy {

    private CincoDeckStrategy deckStrategy;

    @BeforeEach
    public void setUp() {
        CardsStrategy cardsStrategy = new CincoCardsStrategy();
        deckStrategy = new CincoDeckStrategy(cardsStrategy);
    }

    @Test
    public void cincoDeckShouldHaveSizeSeven(){
        // Given Cinco deck
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should have size 7
        int size = deck.size();
        assertThat(size, is(7));
    }

    @Test
    public void allCardsInCincoDeckShouldBeCinco(){
        // Given Cinco deck
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        for (Card c : deck){
            assertThat(c.getName(), is(GameConstants.CINCO_CARD));
        }
    }
}

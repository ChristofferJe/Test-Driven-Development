package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.variants.DishDeckStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;




public class TestDishDeckStrategy {

    private DishDeckStrategy deckStrategy;


    /**
     * Fixture for DeltaStone testing.
     */
    @BeforeEach
    public void setUp() {
        deckStrategy = new DishDeckStrategy();
    }

    @Test
    public void DeckShouldBeSize24(){
        // Given delta deck strategy
        // When a DishDeck is initialized
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain 24 cards
        assertThat(deck.size(), is(24));
    }

    @Test
    public void CardAtIndex0ShouldCost1() {
        // Given delta deck strategy
        // When a DishDeck is initialized
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then the card at index 0 in the deck should cost 1
        int cost = deck.get(0).getManaCost();
        assertThat(cost, is(1));
    }

    @Test
    public void CardAtIndex1ShouldCost2orLess() {
        // Given delta deck strategy
        // When a DishDeck is initialized
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then the card at index 1 in the deck should cost 2 or less
        int cost = deck.get(1).getManaCost();
        assertThat(cost, lessThanOrEqualTo(2));
    }

    @Test
    public void CardAtIndex2ShouldCost4orLess() {
        // Given delta deck strategy
        // When a DishDeck is initialized
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then the card at index 2 in the deck should cost 4 or less
        int cost = deck.get(2).getManaCost();
        assertThat(cost, lessThanOrEqualTo(4));
    }

    @Test
    public void CardAtIndex0ShouldBeBrownRiceOrFrenchFries() {
        // Given delta deck strategy
        // When a DishDeck is initialized
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then the card at index 0 in the deck should be Brown Rice or French Fries
        String cardName = deck.get(0).getName();
        assertThat(cardName, anyOf(is(GameConstants.BROWN_RICE_CARD), is(GameConstants.FRENCH_FRIES_CARD)));
    }

    @Test
    public void ShouldHaveTwoFiletMignonCards() {
        // Given delta deck strategy
        // When we create a delta deck
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain two Filet Mignon cards
        long count = deck.stream().filter(card -> card.getName()
                .equals(GameConstants.FILET_MIGNON_CARD)).count();
        assertThat(count, is(2L));
    }

    @Test
    public void ShouldHaveTwoOfEachCard() {
        // Given delta deck strategy
        // When we create a delta deck
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain two of each card
        for(Card c: deck){
            String cardName = c.getName();
            long count = deck.stream().filter(card -> card.getName()
                    .equals(cardName)).count();
            assertThat(count, is(2L));}
    }
}

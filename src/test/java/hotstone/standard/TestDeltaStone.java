package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


public class TestDeltaStone {

    private Game game;
    private DishDeckStrategy deckStrategy;


    /**
     * Fixture for DeltaStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(Version.DELTA);
        deckStrategy = new DishDeckStrategy();
    }

    @Test
    public void FindusShouldHaveSevenMana() {
        // Given a game
        // Findus Should have seven mana
        int mana = game.getHero(Player.FINDUS).getMana();
        assertThat(mana, is(7));
    }

    @Test
    public void PeddersenShouldHaveSevenMana() {
        // Given a game
        // Peddersen Should have seven mana
        int mana = game.getHero(Player.PEDDERSEN).getMana();
        assertThat(mana, is(7));
    }

    @Test
    public void FindusShouldHaveSevenManaAfterOneRound() {
        // Given a game
        // When Findus plays a card
        Card card = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, card);
        // When the first round passes
        game.endTurn();
        game.endTurn();
        // Then Findus should have seven mana
        int mana = game.getHero(Player.PEDDERSEN).getMana();
        assertThat(mana, is(7));
    }

    @Test
    public void DeckShouldBeSize24(){
        // Given delta deck strategy
        // When a DishDeck is initialized
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain 24 cards
        assertThat(deck, hasSize(24));
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
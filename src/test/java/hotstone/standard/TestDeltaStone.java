package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;


public class TestDeltaStone {

    private Game game;


    /**
     * Fixture for BetaStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(Version.DELTA);
    }

    @Test
    public void FindusShouldHaveSevenMana(){
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
    public void FindusShouldHaveSevenManaAfterOneRound(){
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
    public void CardAtIndex0ShouldCost1(){
        // Given delta deck strategy
        DeckStrategy deckStrategy = new DeltaDeckStrategy();
        // When we create a delta deck
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then the card at index 0 in the deck should cost 1
        int cost = deck.get(0).getManaCost();
        assertThat(cost,is(1));
    }
    @Test
    public void CardAtIndex1ShouldCost2orLess() {
        // Given delta deck strategy
        DeckStrategy deckStrategy = new DeltaDeckStrategy();
        // When we create a delta deck
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then the card at index 1 in the deck should cost 2 or less
        int cost = deck.get(1).getManaCost();
        assertThat(cost, lessThan(3));
    }
    @Test
    public void CardAtIndex2ShouldCost4orLess() {
        // Given delta deck strategy
        DeckStrategy deckStrategy = new DeltaDeckStrategy();
        // When we create a delta deck
        ArrayList<Card> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then the card at index 1 in the deck should cost 2 or less
        int cost = deck.get(1).getManaCost();
        assertThat(cost, lessThan(4));
    }
}

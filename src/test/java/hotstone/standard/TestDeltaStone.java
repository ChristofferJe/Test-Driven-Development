package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


public class TestDeltaStone {

    private Game game;


    /**
     * Fixture for DeltaStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new DeltaGameFactory());
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
    public void FindusShouldHaveBrownRiceOrFrenchFriesAtIndex2InHand(){
        // Given game
        // Then the card at index 2 in FIndus hand should be Brown Rice or French Fries
        Card card = game.getCardInHand(Player.FINDUS, 2);
        assertThat(card.getName(), anyOf(is(GameConstants.BROWN_RICE_CARD), is(GameConstants.FRENCH_FRIES_CARD)));
    }


    @Test
    public void cardAtIndex2InFindusHandShouldCost1Mana(){
        // Given game
        // Then the card at index 2 in FIndus hand should cost 1 mana
        Card card = game.getCardInHand(Player.FINDUS, 2);
        int cost = card.getManaCost();
        assertThat(cost, is(1));
    }
    @Test
    public void cardAtIndex1InFindusHandShouldCost2ManaOrLess(){
        // Given game
        // Then the card at index 1 in FIndus hand should cost 2 mana or less
        Card card = game.getCardInHand(Player.FINDUS, 1);
        int cost = card.getManaCost();
        assertThat(cost, lessThanOrEqualTo(2));
    }
    @Test
    public void cardAtIndex0InFindusHandShouldCost4ManaOrLess(){
        // Given game
        // Then the card at index 0 in FIndus hand should cost 4 or less
        Card card = game.getCardInHand(Player.FINDUS, 2);
        int cost = card.getManaCost();
        assertThat(cost, lessThanOrEqualTo(4));
    }
}


package hotstone.standard;

import hotstone.framework.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


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
}

package hotstone.standard;

import hotstone.framework.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBetaStone {
    private Game game;


    /** Fixture for BetaStone testing. */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(Version.BETA);
    }

    @Test
    public void FindusShouldHave1ManaWhenFirstTurnStarts(){
        // Given a game
        // Then Findus' hero should have 1 mana
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.getMana(), is(1));
    }
    @Test
    public void PeddersenShouldHave1ManaWhenFirstTurnStarts(){
        // Given a game
        // When it is Peddersen's first turn
        game.endTurn();
        // Then Peddersen's hero should have 1 mana
        Hero hero = game.getHero(Player.PEDDERSEN);
        assertThat(hero.getMana(), is(1));
    }

    @Test
    public void FindusShouldHave2ManaWhenSecondTurnStarts(){
        // Given a game
        // When Findus second turn starts
        game.endTurn();
        game.endTurn();
        // Then Findus' hero should have 2 mana
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.getMana(), is(2));
    }

    @Test
    public void PeddersenShouldHave2ManaWhenSecondTurnStarts(){
        // Given a game
        // When Peddersen's second turn starts
        game.endTurn();
        game.endTurn();
        game.endTurn();
        // Then Peddersen's hero should have 2 mana
        Hero hero = game.getHero(Player.PEDDERSEN);
        assertThat(hero.getMana(), is(2));
    }

    @Test
    public void FindusShouldHave3ManaWhenThirdTurnStarts(){
        // Given a game
        // When Findus third turn starts
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        // Then Findus' hero should have 3 mana
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.getMana(), is(3));
    }

}

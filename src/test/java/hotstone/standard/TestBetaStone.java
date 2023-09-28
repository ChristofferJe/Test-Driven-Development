package hotstone.standard;

import hotstone.framework.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBetaStone {
    private Game game;


    /** Fixture for BetaStone testing. */
    @BeforeEach
    public void setUp() {
        game = StandardHotStoneGame.createBetaGame();
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

    @Test
    public void ThereShouldBeNoWinnerWhenGivenGame(){
        // Given game
        // There should be no winner
        Player winner = game.getWinner();
        assertThat(winner, is(nullValue()));
    }

    @Test
    public void FindusShouldWinWhenPeddersenHealthBelowOne(){
        // Given a game
        // When Peddersens hero's health is below one
        StandardHero stdHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        stdHero.decreaseHealth(21);
        // Then Findus should be winner
        assertThat(game.getWinner(), is(Player.FINDUS));
    }

    @Test
    public void PeddersenShouldWinWhenFindusHealthBelowOne(){
        // Given a game
        // When Findus hero's health is below one
        StandardHero stdHero = (StandardHero) game.getHero(Player.FINDUS);
        stdHero.decreaseHealth(21);
        // Then Peddersen should be winner
        assertThat(game.getWinner(), is(Player.PEDDERSEN));
    }

}

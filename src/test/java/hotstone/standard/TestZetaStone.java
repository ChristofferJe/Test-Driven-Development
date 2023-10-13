package hotstone.standard;

import hotstone.framework.*;
import hotstone.utility.TestHelper;
import hotstone.variants.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestZetaStone {
    private Game game;


    /**
     * Fixture for Zeta Stone testing.
     */
    @BeforeEach
    public void setUp(){
        game = new StandardHotStoneGame(new ZetaGameFactory());
    }
    @Test
    public void FindusWinsWhenPeddersenHeroHasLessThan1HealthBeforeRound6(){
        // Given game
        // When Findus plays cinco in first round
        Card findusCinco1 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, findusCinco1);
        // And Findus plays Cinco in second round
        game.endTurn();
        game.endTurn();
        Card findusCinco2 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, findusCinco2);
        // And attacks with Cinco1
        game.attackHero(Player.FINDUS,findusCinco1);
        // And Findus attacks with both cinco's next round
        game.endTurn();
        game.endTurn();
        game.attackHero(Player.FINDUS,findusCinco1);
        game.attackHero(Player.FINDUS,findusCinco2);
        // and next round
        game.endTurn();
        game.endTurn();
        game.attackHero(Player.FINDUS,findusCinco1);
        game.attackHero(Player.FINDUS,findusCinco2);
        // Then Pedersens Hero's health is -4
        int health = game.getHero(Player.PEDDERSEN).getHealth();
        assertThat(health, is(-4));
        // And Findus is the winner
        Player winner = game.getWinner();
        assertThat(winner, is(Player.FINDUS));
    }



    @Test
    public void FindusWinsAfterAttackingMinionTwiceWithCincoAfterSixRounds() {
        // Given game
        // When Findus plays cinco in first round
        Card findusCinco1 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, findusCinco1);
        // And Peddersen plays Cinco
        game.endTurn();
        Card peddersenCinco1 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN, peddersenCinco1);
        // When it is Findus turn again, Findus plays Cinco
        game.endTurn();
        Card findusCinco2 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS,findusCinco2);
        // And Peddersen plays Cinco
        game.endTurn();
        Card peddersenCinco2 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN, peddersenCinco2);
        // When 5 more rounds have passed
        game.endTurn();
        TestHelper.advanceGameNRounds(game, 5);
        // And Findus attacks with both Cinco's
        game.attackCard(Player.FINDUS, findusCinco1,peddersenCinco1);
        Status status = game.attackCard(Player.FINDUS,findusCinco2,peddersenCinco2);
        assertThat(status, is(Status.OK));
        // Then Findus is the winner
        Player winner = game.getWinner();
        assertThat(winner, is(Player.FINDUS));
    }
    @Test
    public void NoWinnerInStartOfGame(){
        // Given game
        // Then no winner is found
        Player winner = game.getWinner();
        assertThat(winner, is(nullValue()));
    }

    @Test
    public void NoWinnerAfter6Rounds(){
        // Given game
        // When 6 rounds passes
        TestHelper.advanceGameNRounds(game,6);
        // Then no winner is found
        Player winner = game.getWinner();
        assertThat(winner, is(nullValue()));
    }



    }


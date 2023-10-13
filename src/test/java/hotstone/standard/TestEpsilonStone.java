package hotstone.standard;

import hotstone.framework.*;
import hotstone.utility.TestHelper;
import hotstone.variants.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEpsilonStone {
    private Game game;
    private GameFactory factory;


    /**
     * Fixture for Epsilon Stone testing.
     */
    @BeforeEach
    public void setUp(){
        factory = new EpsilonTestGameFactory();
        game = new StandardHotStoneGame(factory);
    }

    @Test
    public void FindusHasHeroTypeFrenchChef(){
        // Given game
        // Findus has hero type ItalianChef
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.getType(), is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }

    @Test
    public void PeddersenHasHeroTypeItalianChef(){
        // Given game
        // Peddersen has hero type FrenchChef
        Hero hero = game.getHero(Player.PEDDERSEN);
        assertThat(hero.getType(), is(GameConstants.ITALIAN_CHEF_HERO_TYPE));
    }

    @Test
    public void FindusHeroPowerShouldDecreaseHealthOfDosBy2(){
        // Given game
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        // Dos is in Peddersen's field at index 0
        Card dos = game.getCardInField(Player.PEDDERSEN, 0);
        // Dos has 2 health
        int healthBefore = dos.getHealth();
        assertThat(healthBefore, is(2));
        // When Findus uses hero power and the random number is set to 0
        factory.getNumberStrategy().setNumber(0);
        game.usePower(Player.FINDUS);
        // Then Dos has 0 health
        int healthAfter =  dos.getHealth();
        assertThat(healthAfter, is(0));
    }

    @Test
    public void DosShouldBeRemovedIfDead(){
        // Given game
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        // Dos is in Peddersen's field at index 0
        Card dos = game.getCardInField(Player.PEDDERSEN, 0);
        // When Findus uses hero power and the random number is set to 0
        factory.getNumberStrategy().setNumber(0);
        game.usePower(Player.FINDUS);
        // Then Dos has 0 health
        int health =  dos.getHealth();
        assertThat(health, is(0));
        // and Dos is removed from field
        ArrayList<Card> field = (ArrayList) game.getField(Player.PEDDERSEN);
        boolean isDosInField = field.contains(dos);
        assertThat(isDosInField,is(false));

    }

    @Test
    public void FindusHeroPowerShouldDecreaseHealthOfTresBy2(){
        // Given game
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        game.endTurn();
        Card tres = game.getCardInHand(Player.PEDDERSEN, 2);
        game.playCard(Player.PEDDERSEN, tres);
        // Tres has 3 health
        int healthBefore = tres.getHealth();
        assertThat(healthBefore, is(3));
        // When it is Findus turn and
        // Findus uses hero power and the random number is set to 0
        game.endTurn();
        factory.getNumberStrategy().setNumber(0);
        game.usePower(Player.FINDUS);
        // Then Tres has 1 health
        int healthAfter =  tres.getHealth();
        assertThat(healthAfter, is(1));
    }

    @Test
    public void PeddersensHeroPowerShouldIncreaseDosAttackBy2(){
        // Given game
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        game.endTurn();
        // Dos is in Peddersen's field at index 0
        Card dos = game.getCardInField(Player.PEDDERSEN, 0);
        // Dos has 2 attack
        int attackBefore = dos.getAttack();
        assertThat(attackBefore, is(2));
        // When Peddersen uses hero power and the random number is set to 0
        factory.getNumberStrategy().setNumber(0);
        game.usePower(Player.PEDDERSEN);
        // Then Dos has 4 attack
        int attackAfter =  dos.getAttack();
        assertThat(attackAfter, is(4));
    }

    @Test
    public void PeddersensHeroPowerShouldIncreaseTresAttackBy2(){
        // Given game with Uno and Dos in Peddersen's field
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        game.endTurn();
        Card uno = game.getCardInHand(Player.PEDDERSEN, 3);
        game.playCard(Player.PEDDERSEN, uno);
        // Uno has 1 attack
        int attackBefore = uno.getAttack();
        assertThat(attackBefore, is(1));
        // When Peddersen uses hero power and the random number is set to 0
        factory.getNumberStrategy().setNumber(0);
        game.usePower(Player.PEDDERSEN);
        // Then uno has 3 attack
        int attackAfter =  uno.getAttack();
        assertThat(attackAfter, is(3));
    }

    @Test
    public void FindusShouldWinWhenAttackSumIsMoreThanSeven(){
        // Given game and its Peddersens turn
        // Peddersen playes Uno and Dos
        game.endTurn();
        Card uno = game.getCardInHand(Player.PEDDERSEN, 3);
        Card dos = game.getCardInHand(Player.PEDDERSEN, 2);
        game.playCard(Player.PEDDERSEN, uno);
        game.playCard(Player.PEDDERSEN, dos);
        game.endTurn();
        // When Findus turn, Findus playes Cuatro
        Card cuatro = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, cuatro);
        game.endTurn();
        game.endTurn();
        // When Findus turn again, Findus playes Cinco and attacks uno with cuatro
        Card cinco = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, cinco);
        game.attackCard(Player.FINDUS, cuatro, uno);
        // When Findus turn again, Findus attack dos with cinco
        game.endTurn();
        game.endTurn();
        game.attackCard(Player.FINDUS, cinco, dos);
        // Then Findus should be the winner
        Player winner = game.getWinner();
        assertThat(winner, is(Player.FINDUS));
    }


    @Test
    public void FindusHeroPowerShouldCost2ManaWhenPeddersensFieldIsEmpty(){
        // Given game, Findus has 3 mana
        int manaBefore = game.getHero(Player.FINDUS).getMana();
        assertThat(manaBefore, is(3));
        // When Peddersens field is empty and Findus uses hero power
        game.usePower(Player.FINDUS);
        // Then Findus has 1 mana
        int manaAfter = game.getHero(Player.FINDUS).getMana();
        assertThat(manaAfter, is(1));
    }


    }


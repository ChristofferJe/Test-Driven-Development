package hotstone.standard;

import hotstone.framework.*;
import hotstone.utility.TestHelper;
import hotstone.variants.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEtaStone {

    private StandardHotStoneGame game;
    private GameFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new EtaTestGameFactory();
        game = new StandardHotStoneGame(factory);
    }
    @Test
    public void brownRiceDoesOneDamageToOpponentHero() {
        // Given game
        // When enough rounds has passed that all types of cards are in Findus Hand
        TestHelper.advanceGameNRounds(game, 20);
        // Find Brown Rice in hand
        Card brownRice = TestHelper.findCardInList(game.getHand(Player.FINDUS), GameConstants.BROWN_RICE_CARD);
        assertThat(brownRice.getName(), is(GameConstants.BROWN_RICE_CARD));
        // WHen Peddersen hero has 21 health and Findus plays Brown Rice
        int healthBefore = game.getHero(Player.PEDDERSEN).getHealth();
        assertThat(healthBefore, is(21));
        game.playCard(Player.FINDUS, brownRice);
        // Then Peddersen's hero has 20 health
        int healthAfter = game.getHero(Player.PEDDERSEN).getHealth();
        assertThat(healthAfter, is(20));
    }

    @Test
    public void TomatoSaladAdds1AttackToOwnMinion(){
        // Given game, Findus plays a card with an attack value
        Card card = game.getCardInHand(Player.FINDUS, 0);
        int attackBefore = card.getAttack();
        game.playCard(Player.FINDUS, card);
        // When enough rounds has passed that all types of cards are in Findus Hand
        TestHelper.advanceGameNRounds(game, 21);
        // Find Tomato Salad in hand
        Card tomatoSalad = TestHelper.findCardInList(game.getHand(Player.FINDUS), GameConstants.TOMATO_SALAD_CARD);
        assertThat(tomatoSalad.getName(), is(GameConstants.TOMATO_SALAD_CARD));
        // When Findus plays Tomato Salad
        factory.getNumberStrategy().setNumber(0);
        game.playCard(Player.FINDUS,tomatoSalad);
        // Then the attack value of the card in field is increased by 1
        int attackAfter = card.getAttack();
        assertThat(attackAfter,is(attackBefore + 1));
    }

    @Test
    public void TomatoSaladEffectDoesNothingWhenFieldIsEmpty(){
        // Given game
        // When enough rounds has passed that all types of cards are in Findus Hand
        TestHelper.advanceGameNRounds(game, 20);
        // Find Tomato Salad in hand
        Card tomatoSalad = TestHelper.findCardInList(game.getHand(Player.FINDUS), GameConstants.TOMATO_SALAD_CARD);
        assertThat(tomatoSalad.getName(), is(GameConstants.TOMATO_SALAD_CARD));
        // When Findus plays Tomato Salad
        game.playCard(Player.FINDUS,tomatoSalad);
        // Then Tomato Salad's does not use effect in self
        int attack = tomatoSalad.getAttack();
        assertThat(attack, is(2));
    }

    @Test
    public void pokeBowlIncreaseOwnersHeroHealthBy2() {
        // Given game
        // When enough rounds has passed that all types of cards are in Findus Hand
        TestHelper.advanceGameNRounds(game, 20);
        // Find PokeBowl in hand
        Card pokeBowl = TestHelper.findCardInList(game.getHand(Player.FINDUS), GameConstants.POKE_BOWL_CARD);
        assertThat(pokeBowl.getName(), is(GameConstants.POKE_BOWL_CARD));
        // When Findus hero has 21 health and Findus plays Poke Bowl
        int healthBefore = game.getHero(Player.FINDUS).getHealth();
        assertThat(healthBefore, is(21));
        game.playCard(Player.FINDUS, pokeBowl);
        // Then Findus's hero has 23 health
        int healthAfter = game.getHero(Player.FINDUS).getHealth();
        assertThat(healthAfter, is(23));
    }

    @Test
    public void noodleSoupDrawCard() {
        // Given game
        // When enough rounds has passed that all types of cards are in Findus Hand
        TestHelper.advanceGameNRounds(game, 20);
        // Find PokeBowl in hand
        Card noodleSoup = TestHelper.findCardInList(game.getHand(Player.FINDUS), GameConstants.NOODLE_SOUP_CARD);
        assertThat(noodleSoup.getName(), is(GameConstants.NOODLE_SOUP_CARD));
        // When Findus hero has hand size 23 and decksize 1
        assertThat(game.getHandSize(Player.FINDUS), is(23));
        assertThat(game.getDeckSize(Player.FINDUS), is(1));
        // When Findus plays NoodleSoup
        game.playCard(Player.FINDUS, noodleSoup);
        // Then handsize is still 23 and decksize is 0
        assertThat(game.getHandSize(Player.FINDUS), is(23));
        assertThat(game.getDeckSize(Player.FINDUS), is(0));
    }

    @Test
    public void noodleSoupDamageHero() {
        // Given game
        // When enough rounds has passed that all types of cards are in Findus Hand
        TestHelper.advanceGameNRounds(game, 21);
        // Find PokeBowl in hand
        Card noodleSoup = TestHelper.findCardInList(game.getHand(Player.FINDUS), GameConstants.NOODLE_SOUP_CARD);
        assertThat(noodleSoup.getName(), is(GameConstants.NOODLE_SOUP_CARD));
        // When Findus hero has hand size 24, decksize 0 and health 21
        assertThat(game.getHandSize(Player.FINDUS), is(24));
        assertThat(game.getDeckSize(Player.FINDUS), is(0));
        assertThat(game.getHero(Player.FINDUS).getHealth(), is(21));
        // When Findus plays NoodleSoup
        game.playCard(Player.FINDUS, noodleSoup);
        // Then handsize is 23 and decksize is still 0 and Findus's health is 19
        assertThat(game.getHandSize(Player.FINDUS), is(23));
        assertThat(game.getDeckSize(Player.FINDUS), is(0));
        assertThat(game.getHero(Player.FINDUS).getHealth(), is(19));
    }

    @Test
    public void chickenCurryKillMinion() {
        // Given game and Peddersen plays a minion in his first turn
        game.endTurn();
        Card card = game.getCardInHand(Player.PEDDERSEN, 2);
        game.playCard(Player.PEDDERSEN, card);
        game.endTurn();
        // When enough rounds has passed that all types of cards are in Findus Hand
        TestHelper.advanceGameNRounds(game, 19);
        // Find ChickenCurry in hand
        Card chickenCurry = TestHelper.findCardInList(game.getHand(Player.FINDUS), GameConstants.CHICKEN_CURRY_CARD);
        assertThat(chickenCurry.getName(), is(GameConstants.CHICKEN_CURRY_CARD));
        // When Peddersen's field size is 1
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(1));
        // When Findus plays ChickenCurry and FixedNumberStrategy is set to 0
        factory.getNumberStrategy().setNumber(0);
        game.playCard(Player.FINDUS, chickenCurry);
        // Then Peddersens fielssize is 0
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(0));
        // And Peddersen's card he played in his first turn is in active
        assertThat(card.isActive(), is(false));

    }

    @Test
    public void beefBurgerAdds2AttackToOpponentMinion() {
        // Given game and Peddersen plays a minion with an attack value in his first turn
        game.endTurn();
        Card card = game.getCardInHand(Player.PEDDERSEN, 2);
        game.playCard(Player.PEDDERSEN, card);
        int attackBefore = card.getAttack();
        game.endTurn();
        // When enough rounds has passed that all types of cards are in Findus Hand
        TestHelper.advanceGameNRounds(game, 19);
        // Find BeefBurger in hand
        Card beefBurger = TestHelper.findCardInList(game.getHand(Player.FINDUS), GameConstants.BEEF_BURGER_CARD);
        assertThat(beefBurger.getName(), is(GameConstants.BEEF_BURGER_CARD));
        // When Findus plays BeefBurger
        game.playCard(Player.FINDUS, beefBurger);
        // Then the minion in Peddersen's field's attack is increased by 2
        int attackAfter = card.getAttack();
        assertThat(attackAfter, is(attackBefore+2));
    }


}

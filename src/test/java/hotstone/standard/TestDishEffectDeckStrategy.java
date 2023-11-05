package hotstone.standard;

import hotstone.framework.*;
import hotstone.utility.TestHelper;
import hotstone.variants.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDishEffectDeckStrategy {

    private DeckStrategy deckStrategy;
    private PickNumberStrategy pickNumberStrategy;
    private ArrayList<MutableCard> deck;

    @BeforeEach
    public void setUp() {
        deckStrategy = new DishEffectDeckStrategy(pickNumberStrategy);
        deck = deckStrategy.createDeck(Player.FINDUS);
    }

    @Test
    public void brownRiceShouldHaveRightEffectDescription(){
        // Given DishEffectCardsStrategy and BrownRice
        MutableCard brownRice = (MutableCard) TestHelper.findCardInList(deck, GameConstants.BROWN_RICE_CARD);
        // BrownRice Should have right description
        assertThat(brownRice.getEffectDescription(), is("Opp H: (0,-1)"));
    }
    @Test
    public void tomatoSaladShouldHaveRightEffectDescription(){
        // Given DishEffectCardsStrategy and TomataSalad
        MutableCard tomatoSalad = (MutableCard) TestHelper.findCardInList(deck, GameConstants.TOMATO_SALAD_CARD);
        // TomatoSalad Should have right description
        assertThat(tomatoSalad.getEffectDescription(), is("M: (+1,0)"));
    }
    @Test
    public void pokeBowlShouldHaveRightEffectDescription(){
        // Given DishEffectCardsStrategy and PokeBowl
        MutableCard pokeBowl = (MutableCard) TestHelper.findCardInList(deck, GameConstants.POKE_BOWL_CARD);
        // PokeBowl should have right description
        assertThat(pokeBowl.getEffectDescription(), is("H: (0,+2)"));
    }
    @Test
    public void noodleSoupShouldHaveRightEffectDescription(){
        // Given DishEffectCardsStrategy and NoodleSoup
        MutableCard noodleSoup = (MutableCard) TestHelper.findCardInList(deck, GameConstants.NOODLE_SOUP_CARD);
        // NoodleSoup should have right description
        assertThat(noodleSoup.getEffectDescription(), is("Draw Card"));
    }
    @Test
    public void chickenCurryShouldHaveRightEffectDescription(){
        // Given DishEffectCardsStrategy and ChickenCurry
        MutableCard chickenCurry = (MutableCard) TestHelper.findCardInList(deck, GameConstants.CHICKEN_CURRY_CARD);
        // ChickenCurry should have right description
        assertThat(chickenCurry.getEffectDescription(), is("Kill opp M"));
    }
    @Test
    public void beefBurgerShouldHaveRightEffectDescription(){
        // Given DishEffectCardsStrategy and BeefBurger
        MutableCard beefBurger = (MutableCard) TestHelper.findCardInList(deck, GameConstants.BEEF_BURGER_CARD);
        // ChickenCurry should have right description
        assertThat(beefBurger.getEffectDescription(), is("Opp M: (+2,0)"));
    }


}

package hotstone.standard;

import hotstone.framework.*;
import hotstone.utility.TestHelper;
import hotstone.variants.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEpsilonStone {
    private Game game;
    private FixedNumberStrategy pickNumberStrategy;


    /**
     * Fixture for Epsilon Stone testing.
     */
    @BeforeEach
    public void setUp(){
        WinnerStrategy winnerStrategy = new FindusWinsWinnerStrategy();
        ManaStrategy manaStrategy = new ThreeManaStrategy();
        pickNumberStrategy = new FixedNumberStrategy();
        HeroStrategy heroStrategy = new ItalianFrenchHeroStrategy(pickNumberStrategy);
        DeckStrategy deckStrategy = new SpanishDeckStrategy();
        game = new StandardHotStoneGame(winnerStrategy, manaStrategy, heroStrategy, deckStrategy);
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
    public void FindusHeroPowerShouldDecreaseHealthOfOpponentMinionBy2(){
        // Given game
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        // Dos is in Peddersen's field at index 0
        Card dos = game.getCardInField(Player.PEDDERSEN, 0);
        // Dos has 2 health
        int healthBefore = dos.getHealth();
        assertThat(healthBefore, is(2));
        // When Findus uses hero power and the random number is set to 0
        pickNumberStrategy.setNumber(0);
        game.usePower(Player.FINDUS);
        // Then Dos has 0 health
        int healthAfter =  dos.getHealth();
        assertThat(healthAfter, is(0));
    }


    }


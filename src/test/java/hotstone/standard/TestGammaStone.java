package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.GammaGameFactory;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGammaStone {
    private Game game;


    /**
     * Fixture for GammaStone testing.
     */
    @BeforeEach
    public void setUp(){game = new StandardHotStoneGame(new GammaGameFactory());
    }

    @Test
    public void FindusHasHeroTypeThaiChef(){
        // Given game
        // Findus has hero type ThaiChef
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));

    }

    @Test
    public void PeddersenHasHeroTypeDanishChef(){
        // Given game
        // Peddersen has hero type DanishChef
        Hero hero = game.getHero(Player.PEDDERSEN);
        assertThat(hero.getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));

    }

    @Test
    public void FindusHeroEffectHasDescriptionForThaiHero() {
        // Given game
        // WHen Findus has hero type ThaiChef
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
        // Then Findus' hero has description
        assertThat(hero.getEffectDescription(), is("Opp H: (0,-2)"));
    }
    @Test
    public void PeddersensHeroEffectHasDescriptionDanishChef(){
        // Given game
        // When Peddersen has hero type DanishChef
        Hero hero = game.getHero(Player.PEDDERSEN);
        assertThat(hero.getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));
        // Then Peddersen's hero has description
        assertThat(hero.getEffectDescription(), is("Field Sovs"));
    }

    @Test
    public void ShouldReducePeddersensHeroHealthWith2WhenFindusUsesHeroPower(){
        // Given game, Peddersens has hero with health 21
        Hero hero = game.getHero(Player.PEDDERSEN);
        int healthBefore = hero.getHealth();
        assertThat(healthBefore, is(21));
        // When Findus uses hero power
        game.usePower(Player.FINDUS);
        // Then Peddersen's hero should have 19 health
        int healthAfter = hero.getHealth();
        assertThat(healthAfter, is(19));
    }

    @Test
    public void ShouldFieldMinionSovsWhenPeddersenUsesHeroPower(){
        // Given game, when it is Peddersens turn
        game.endTurn();
        // When Peddersen uses hero power
        game.usePower(Player.PEDDERSEN);
        // Then minion "Sovs" appears on Peddersen's field at index 0
        Card Sovs = game.getCardInField(Player.PEDDERSEN, 0);
        assertThat(Sovs.getName(), is(GameConstants.SOVS_CARD));
    }

    @Test
    public void CardSovsShouldHaveHealth1AndAttack1(){
        // Given game, when it is Peddersens turn
        game.endTurn();
        // When Peddersen uses hero power
        game.usePower(Player.PEDDERSEN);
        // and minion "Sovs" appears on Peddersen's field at index 0
        Card Sovs = game.getCardInField(Player.PEDDERSEN, 0);
        assertThat(Sovs.getName(), is(GameConstants.SOVS_CARD));
        // Then Sovs has health 1
        assertThat(Sovs.getHealth(), is(1));
        // And attack 1
        assertThat(Sovs.getAttack(), is(1));
    }

    }


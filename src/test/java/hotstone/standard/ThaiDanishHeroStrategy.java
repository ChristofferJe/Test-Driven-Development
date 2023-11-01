package hotstone.standard;

import hotstone.framework.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;


public class ThaiDanishHeroStrategy {

    private HeroStrategy heroStrategy;

    @BeforeEach
    public void setUp() {
        heroStrategy = new hotstone.variants.ThaiDanishHeroStrategy();
    }
    @Test
    public void FindusShouldHaveThaiChefHero(){
        // Two hero strategy
        // When Findus is assigned a hero
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        // Then it is Thai Chef Hero
        assertThat(hero.getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
    }

    @Test
    public void PeddersenShouldHaveDanishChefHero(){
        // Two hero strategy
        // When Peddersen is assigned a hero
        Hero hero = heroStrategy.createHero(Player.PEDDERSEN);
        // Then it is Danish Chef Hero
        assertThat(hero.getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));
    }

    @Test
    public void FindusHeroShouldHaveCorrectDescription(){
        // Two hero strategy
        // When Findus is assigned a hero
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        // Then that hero's effect description is "Opp H: (0,-2)"
        assertThat(hero.getEffectDescription(), is("Opp H: (0,-2)"));
    }

    @Test
    public void PeddersenHeroShouldHaveCorrectDescription(){
        // Two hero strategy
        // When Peddersen is assigned a hero
        Hero hero = heroStrategy.createHero(Player.PEDDERSEN);
        // Then that hero's effect description is "Field Sovs"
        assertThat(hero.getEffectDescription(), is("Field Sovs"));
    }

}

package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class TestItalianFrenchHeroStrategy {
    private HeroStrategy heroStrategy;
    private FixedNumberStrategy pickNumberStrategy;

    @BeforeEach
    public void setUp() {
        pickNumberStrategy = new FixedNumberStrategy();
        heroStrategy = new ItalianFrenchHeroStrategy(pickNumberStrategy);
    }

    @Test
    public void FindusShouldHaveFrenchHeroType(){
        // Given ItalianFrench HeroStrategy
        // Findus should have French Hero
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        String type = hero.getType();
        assertThat(type, is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }

    @Test
    public void PeddersenShouldHaveItalianHeroType() {
        // Given ItalianFrench HeroStrategy
        // Peddersen should have Italian Hero
        Hero hero = heroStrategy.createHero(Player.PEDDERSEN);
        String type = hero.getType();
        assertThat(type, is(GameConstants.ITALIAN_CHEF_HERO_TYPE));
    }

    @Test
    public void FindusHeroShouldHaveCorrectDescription(){
        // Given ItalianFrench HeroStrategy
        // Findus hero should have the correct description
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        String description = hero.getEffectDescription();
        assertThat(description, is("Opp M: (0,-2)"));
    }

    @Test
    public void PeddersenHeroShouldHaveCorrectDescription(){
        // Given ItalianFrench HeroStrategy
        // Pedersen hero should have the correct description
        Hero hero = heroStrategy.createHero(Player.PEDDERSEN);
        String description = hero.getEffectDescription();
        assertThat(description, is("M: (+2,0)"));
    }

    @Test
    public void NumberStrategyShouldReturn2WhenGiven2(){
        // Given FixedNumberStrategy
        // It should return the given integer
        pickNumberStrategy.setNumber(2);
        int number = pickNumberStrategy.getNumber(0);
        assertThat(number, is(2));
    }

    @Test
    public void NumberStrategyShouldReturn3WhenGiven3(){
        // Given FixedNumberStrategy
        // It should return the given integer
        pickNumberStrategy.setNumber(3);
        int number = pickNumberStrategy.getNumber(0);
        assertThat(number, is(3));
    }
}

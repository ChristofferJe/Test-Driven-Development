package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class TestFourHeroStrategy {

    private HeroStrategy heroStrategy;
    private PickNumberStrategy pickNumberStrategy;

    @BeforeEach
    public void setUp() {
        pickNumberStrategy = new FixedNumberStrategy();
        heroStrategy = new FourHeroStrategy(pickNumberStrategy);
    }

    @Test
    public void shouldBeDanishHero(){
        pickNumberStrategy.setNumber(3);
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        assertThat(hero.getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));
    }

    @Test
    public void shouldBeThaiHero(){
        pickNumberStrategy.setNumber(2);
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        assertThat(hero.getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
    }
    @Test
    public void shouldBeItalianHero(){
        pickNumberStrategy.setNumber(1);
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        assertThat(hero.getType(), is(GameConstants.ITALIAN_CHEF_HERO_TYPE));
    }
    @Test
    public void shouldBeFrenchHero(){
        pickNumberStrategy.setNumber(0);
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        assertThat(hero.getType(), is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }
    @Test
    public void shouldBeDanishDescription(){
        pickNumberStrategy.setNumber(3);
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        assertThat(hero.getEffectDescription(), is("Field Sovs"));
    }

    @Test
    public void shouldBeThaiDescription(){
        pickNumberStrategy.setNumber(2);
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        assertThat(hero.getEffectDescription(), is("Opp H: (0,-2)"));
    }
    @Test
    public void shouldBeItalianDescription(){
        pickNumberStrategy.setNumber(1);
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        assertThat(hero.getEffectDescription(), is("M: (+2,0)"));
    }
    @Test
    public void shouldBeFrenchDescription(){
        pickNumberStrategy.setNumber(0);
        Hero hero = heroStrategy.createHero(Player.FINDUS);
        assertThat(hero.getEffectDescription(), is("Opp M: (0,-2)"));
    }


}

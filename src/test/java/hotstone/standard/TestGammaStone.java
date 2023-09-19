package hotstone.standard;

import hotstone.framework.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGammaStone {
    private Game game;


    /**
     * Fixture for BetaStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(Version.GAMMA);
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
}


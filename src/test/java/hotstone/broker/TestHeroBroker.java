package hotstone.broker;

import hotstone.broker.client.HeroClientProxy;
import hotstone.framework.*;

import hotstone.standard.GameConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;

import hotstone.broker.client.GameClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;
public class TestHeroBroker {

    private Game game;

    @BeforeEach
    public void setup() {
        // === We start at the server side of the Broker pattern:
        // define the servant, next the invoker

        // Given a Servant game, here a test stub with canned output
        Game servant = new StubGameForBroker();
        // Which is injected into the dedicated Invoker which you must
        // develop
        Invoker invoker = new HotStoneGameInvoker(servant);

        // === Next define the client side of the pattern:
        // the client request handler, the requestor, and the client proxy

        // Instead of a network-based client- and server request handler
        // we make a fake object CRH that talks directly with the injected
        // invoker
        ClientRequestHandler crh =
                new LocalMethodClientRequestHandler(invoker);

        // Which is injected into the standard JSON requestor of the
        // FRDS.Broker library
        Requestor requestor = new StandardJSONRequestor(crh);


        game = new GameClientProxy(requestor);
    }

    @Test
    public void shouldHave34mana(){
        // Stub is hard coded to 34 mana
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.getMana(),is(34));
    }

    @Test
    public void shouldHave26Health(){
        // Stub is hard coded to 26 health
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.getHealth(),is(26));
    }
    @Test
    public void canUsPowerShouldBeTrue(){
        // Stub is hard coded to true
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.canUsePower(),is(true));
    }
    @Test
    public void shouldBeThaiHero(){
        // Stub is hard coded to Thai hero
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
    }
    @Test
    public void ownerShouldBeFindus(){
        // Stub is hard coded to Findus
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.getOwner(), is(Player.FINDUS));
    }
    @Test
    public void shouldBeEfDesc(){
        // Stub is hard coded to "EfDesc"
        Hero hero = game.getHero(Player.FINDUS);
        assertThat(hero.getEffectDescription(), is("EfDesc"));
    }
}

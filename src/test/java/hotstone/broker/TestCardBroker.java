package hotstone.broker;

import hotstone.broker.client.CardClientProxy;
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

public class TestCardBroker {
    private Card card;

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


        card = new CardClientProxy(requestor);
    }

    @Test
    public void shouldHaveNameNoodleSoup(){
        // Stub is hard coded to Noodle Soup
        assertThat(card.getName(), is(GameConstants.NOODLE_SOUP_CARD));
    }

    @Test
    public void shouldHave14ManaCost(){
        // Stub is hard coded to14 mana cost
        assertThat(card.getManaCost(), is(14));
    }
    @Test
    public void shouldHave24Attack(){
        // Stub is hard coded to 24 attack
        assertThat(card.getAttack(), is(24));
    }
    @Test
    public void shouldHave34Attack(){
        // Stub is hard coded to 34 health
        assertThat(card.getHealth(), is(34));
    }
    @Test
    public void shouldBeActive(){
        // Stub is hard coded to be active
        assertThat(card.isActive(), is(true));
    }
    @Test
    public void ownerShouldBeFindus(){
        // Stub is hard coded to owned by Findus
        assertThat(card.getOwner(), is(Player.FINDUS));
    }
    @Test
    public void EffectDescriptionShouldBeEDescript(){
        // Stub is hard coded to owned by Findus
        assertThat(card.getEffectDescription(), is("EDescript"));
    }
}

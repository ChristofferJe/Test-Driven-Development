package hotstone.broker;

import hotstone.framework.*;

import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaGameFactory;
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

import java.util.ArrayList;
import java.util.List;


public class TestAlphaGameBroker {
    private Game game;

    @BeforeEach
    public void setup() {
        // === We start at the server side of the Broker pattern:
        // define the servant, next the invoker

        // Given a Servant game, here AlphaStone
        Game servant = new StandardHotStoneGame(new AlphaGameFactory());
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

        // Which is finally injected into the GameClientProxy that
        // you must develop...
        game = new GameClientProxy(requestor);
    }


    @Test
    public void FindusShouldHaveTresAtIndex0(){
        // Given alpha game
        // Then Findus should have card tres at index 0
        Card tres = game.getCardInHand(Player.FINDUS, 0);
        assertThat(tres, is(notNullValue()));
        assertThat(tres.getName(), is(GameConstants.TRES_CARD));
        assertThat(tres.getAttack(), is(3));
    }
    @Test
    public void UnoShouldBeInFieldAtIndex0ForFindus(){
        // Given alpha game
        // When Findus plays uno
        Card card = game.getCardInHand(Player.FINDUS, 2);
        Status status = game.playCard(Player.FINDUS, card);
        // Then status should be OK and uno should be in field at index 0
        assertThat(status, is(Status.OK));
        Card cardInField = game.getCardInField(Player.FINDUS, 0);
        assertThat(cardInField.getName(), is(GameConstants.UNO_CARD));
    }
    @Test
    public void UnoAttacksDos(){
        // Given alpha game
        // When Findus plays uno
        Card uno = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, uno);
        game.endTurn();
        // When Pedersen plays dos
        Card dos = game.getCardInHand(Player.PEDDERSEN, 2);
        game.endTurn();
        // When Findus attacks dos with uno
        game.attackCard(Player.FINDUS, uno, dos);
        // Then dos should have 1 health
        assertThat(dos.getHealth(), is(1));
        // And Findus's Fieldsize should be 0
        assertThat(game.getFieldSize(Player.FINDUS), is(0));
    }

    @Test
    public void UnoAttacksHero(){
        // Given alpha game
        // When Findus plays uno
        Card uno = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, uno);
        game.endTurn();
        game.endTurn();
        // When Findus attacks Pedersens hero with uno and status is OK
        Status status = game.attackHero(Player.FINDUS, uno);
        assertThat(status, is(Status.OK));
        // Then pedersens hero should have 20 health
        Hero hero = game.getHero(Player.PEDDERSEN);
        assertThat(hero.getHealth(), is(20));
    }
    @Test
    public void shouldHave1ManaWhenUsePower(){
        // Given alpha game
        // When Findus uses hero power and status is OK
        Status status = game.usePower(Player.FINDUS);
        assertThat(status, is(Status.OK));
        // Then Findus' hero has 1 mana
        int mana = game.getHero(Player.FINDUS).getMana();
        assertThat(mana, is(1));
    }

    @Test
    public void handShouldHaveUnoDosTres(){
        // Given alpha game
        // Then Findus hand has uno at index 2
        ArrayList<Card> hand = (ArrayList<Card>) game.getHand(Player.FINDUS);
        assertThat(hand.get(2).getName(), is(GameConstants.UNO_CARD));
        // And dos at index 1
        assertThat(hand.get(1).getName(), is(GameConstants.DOS_CARD));
        // and uno at index 0
        assertThat(hand.get(0).getName(), is(GameConstants.TRES_CARD));
    }
    @Test
    public void fieldShouldHaveUnoAndDos(){
        // Given alpha game
        // When Findus plays uno and dos
        Card uno = game.getCardInHand(Player.FINDUS, 2);
        Card dos = game.getCardInHand(Player.FINDUS, 1);
        game.playCard(Player.FINDUS,uno);
        game.playCard(Player.FINDUS,dos);
        // Then Findus field has uno at index 1
        ArrayList<Card> field = (ArrayList<Card>) game.getField(Player.FINDUS);
        assertThat(field.get(1).getName(), is(GameConstants.UNO_CARD));
        // And dos at index 0
        assertThat(field.get(0).getName(), is(GameConstants.DOS_CARD));
    }


}
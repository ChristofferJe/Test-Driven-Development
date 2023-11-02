package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.TestMode;
import hotstone.observer.GameObserver;
import hotstone.observer.GameObserverSpy;
import hotstone.variants.ProgressiveManaStrategy;
import hotstone.variants.SemiGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestObserverIntegration {

    private StandardHotStoneGame game;
    private GameObserverSpy gameObserver;

    @BeforeEach
    public void setUp() {
         game = new StandardHotStoneGame(new SemiGameFactory(TestMode.IsTest));
         gameObserver = new GameObserverSpy();
         game.addObserver(gameObserver);
    }

    @Test
    public void shouldHaveOnPlayCardAsLastCall(){
        // Given game
        // When Findus plays a card And status is "OK"
        Status status = game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
        assertThat(status, is(Status.OK));
        // Then last call is "onCardPlay"
        assertThat(gameObserver.getLastCall(), is("onCardPlay"));
    }
    @Test
    public void shouldHaveNoneAsLastCallWhenPlayCardNotAllowed(){
        // Given game
        // When Findus tries plays a card And status is not "OK"
        Status status = game.playCard(Player.FINDUS, game.getCardInHand(Player.PEDDERSEN, 2));
        assertThat(status, is(not(Status.OK)));
        // Then last call is "none"
        assertThat(gameObserver.getLastCall(), is("none"));
    }
    @Test
    public void shouldHaveOnTurnChangeToAsLastCalledMethod(){
        // Given game
        // When the turn changes
        game.endTurn();
        // Then last call is "onTurnChange"
        assertThat(gameObserver.getLastCall(), is("onTurnChangeTo"));
    }
    @Test
    public void shouldHaveNoneAsLastCallInStartOfGame(){
        // Given game
        // Then last call is "none"
        assertThat(gameObserver.getLastCall(), is("none"));
    }
    @Test
    public void shouldHaveRightCallsWhenAttackingCard(){
        // Given game
        // When Findus attack on of Peddersens Minions with his own
        Card cardFindus = game.getCardInHand(Player.FINDUS,2);
        game.playCard(Player.FINDUS, cardFindus);
        game.endTurn();
        Card cardPeddersen = game.getCardInHand(Player.PEDDERSEN, 3);
        game.playCard(Player.PEDDERSEN, cardPeddersen);
        game.endTurn();
        game.attackCard(Player.FINDUS, cardFindus, cardPeddersen);
        // Then the last called mehtod is onCardUpdate
        assertThat(gameObserver.getLastCall(), is("onCardUpdate"));
        // Then the second to last called mehtod is onCardUpdate
        assertThat(gameObserver.getXToLastCall(2), is("onCardUpdate"));
        // Then the third to last called method is onAttackCard
        assertThat(gameObserver.getXToLastCall(3), is("onAttackCard"));

    }
    @Test
    public void shouldNotHaveOnAttackCardAsThirdToLastCalledMethodWhenAttackFail(){
        // Given game
        // When Findus attack on of Peddersens Minions with his own
        Card cardFindus = game.getCardInHand(Player.FINDUS,2);
        game.playCard(Player.FINDUS, cardFindus);
        game.endTurn();
        Card cardPeddersen = game.getCardInHand(Player.PEDDERSEN, 3);
        game.playCard(Player.PEDDERSEN, cardPeddersen);
        game.attackCard(Player.FINDUS, cardFindus, cardPeddersen);
        // Then the third to last called method is OnAttackCard
        assertThat(gameObserver.getXToLastCall(3), is(not("onAttackCard")));
    }



}

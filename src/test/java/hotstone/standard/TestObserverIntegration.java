package hotstone.standard;

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
}

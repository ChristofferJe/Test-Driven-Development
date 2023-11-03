package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.observer.GameObserverSpy;
import hotstone.variants.AlphaGameFactory;
import hotstone.variants.GammaGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestObserverGammaStone {

    private StandardHotStoneGame game;
    private GameObserverSpy gameObserver;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new GammaGameFactory());
        gameObserver = new GameObserverSpy();
        game.addObserver(gameObserver);
    }


    @Test
    public void shouldHaveRightCallsOnPeddersenPowerUse() {
        // Given game
        // When Peddersen uses hero power
        game.endTurn();
        Status status = game.usePower(Player.PEDDERSEN);
        assertThat(status, is(Status.OK));
        // Then the last called method should be onCardPlay
        assertThat(gameObserver.getLastCall(), is("onCardPlay"));
        // Then The second to last called method should be onHeroUpdate
        assertThat(gameObserver.getXToLastCall(2), is("onHeroUpdate"));
        // Then the third to last called method should be onUsePower
        assertThat(gameObserver.getXToLastCall(3), is("onUsePower"));
    }
}


package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.TestMode;
import hotstone.observer.GameObserver;
import hotstone.observer.GameObserverSpy;
import hotstone.variants.AlphaGameFactory;
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
         game = new StandardHotStoneGame(new AlphaGameFactory());
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
    public void shouldHaveOnTurnChangeToAsThirdLastCalledMethod(){
        // Given game
        // When the turn changes
        game.endTurn();
        // Then last call is "onTurnChange"
        assertThat(gameObserver.getXToLastCall(3), is("onTurnChangeTo"));
    }
    @Test
    public void shouldHaveNoneAsLastCallInStartOfGame(){
        // Given game
        // Then last call is "none"
        assertThat(gameObserver.getLastCall(), is("none"));
    }
    @Test
    public void shouldHaveRightCallsWhenAttackingCardAndDefendingCardDies(){
        // Given game
        // When Findus attack on of Peddersens Minions with his own
        Card cardFindus = game.getCardInHand(Player.FINDUS,0);
        game.playCard(Player.FINDUS, cardFindus);
        game.endTurn();
        Card cardPeddersen = game.getCardInHand(Player.PEDDERSEN, 2);
        game.playCard(Player.PEDDERSEN, cardPeddersen);
        game.endTurn();
        game.attackCard(Player.FINDUS, cardFindus, cardPeddersen);
        // Then the last called method is onCardRemove
        assertThat(gameObserver.getLastCall(), is("onCardRemove"));
        // Then the second to last called mehtod is onCardUpdate
        assertThat(gameObserver.getXToLastCall(2), is("onCardUpdate"));
        // Then the third to last called mehtod is onCardUpdate
        assertThat(gameObserver.getXToLastCall(3), is("onCardUpdate"));
        // Then the fourth to last called method is onAttackCard
        assertThat(gameObserver.getXToLastCall(4), is("onAttackCard"));

    }
    @Test
    public void shouldNotHaveOnAttackCardAsThirdToLastCalledMethodWhenAttackFail(){
        // Given game
        // When Findus attack on of Peddersens Minions with his own
        Card cardFindus = game.getCardInHand(Player.FINDUS,2);
        game.playCard(Player.FINDUS, cardFindus);
        game.endTurn();
        Card cardPeddersen = game.getCardInHand(Player.PEDDERSEN, 2);
        game.playCard(Player.PEDDERSEN, cardPeddersen);
        game.attackCard(Player.FINDUS, cardFindus, cardPeddersen);
        // Then the third to last called method is OnAttackCard
        assertThat(gameObserver.getXToLastCall(3), is(not("onAttackCard")));
    }
    @Test
    public void shouldHavRightCallsOnAttackHero() {
        // Given game
        // When Findus attacks Peddersen hero with a minion
        Card card = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, card);
        game.endTurn();
        game.endTurn();
        game.attackHero(Player.FINDUS, card);
        // Then last call is onUpdateHero
        assertThat(gameObserver.getLastCall(), is("onHeroUpdate"));
        // And the second to last call is onAttackHero
        assertThat(gameObserver.getXToLastCall(2), is("onAttackHero"));
    }
    @Test
    public void shouldNotHavRightCallsOnAttackHeroWhenNotAllowed() {
        // Given game
        // When Findus attacks Peddersen hero with a minion
        Card card = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, card);
        game.endTurn();
        game.attackHero(Player.FINDUS, card);
        // And the second to last call is onAttackHero
        assertThat(gameObserver.getXToLastCall(2), is(not("onAttackHero")));
    }
    @Test
    public void shouldHaveOnHeroUpdateWhenHeroHealthIncreases(){
        // Given game
        // When Findus' hero's health is increased
        game.increaseHeroHealth(Player.FINDUS,1);
        // Then last call is onHeroUpdate
        assertThat(gameObserver.getLastCall(), is("onHeroUpdate"));
    }

    @Test
    public void shouldHaveOnHeroUpdateWhenHeroManaDecreases(){
        // Given game
        // When Findus' hero's health is increased
        game.decreaseHeroMana(Player.FINDUS,1);
        // Then last call is onHeroUpdate
        assertThat(gameObserver.getLastCall(), is("onHeroUpdate"));
    }
    @Test
    public void shouldHaveOnHeroUpdateWhenHeroManaRestores(){
        // Given game
        // When Findus' hero's health is increased
        game.restoreMana(Player.FINDUS);
        // Then last call is onHeroUpdate
        assertThat(gameObserver.getLastCall(), is("onHeroUpdate"));
    }

    @Test
    public void shouldHaveOnUsePowerAsLastCalledMethod(){
        // Given game
        // When Findus uses hero power
        Status status = game.usePower(Player.FINDUS);
        assertThat(status, is(Status.OK));
        // Then the last called method should be onUsePower
        assertThat(gameObserver.getLastCall(), is("onUsePower"));
    }

    @Test
    public void shouldNotHaveOnUsePowerAsLastCalledMethodIfPowerFails(){
        // Given game
        // Findus plays Tres
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0));
        // When Findus tires to use its hero power it doesn't have enough mana
        Status status = game.usePower(Player.FINDUS);
        assertThat(status, is(Status.NOT_ENOUGH_MANA));
        // Then the last called method should not be onUsePower
        assertThat(gameObserver.getLastCall(), is(not("onUsePower")));
    }
    @Test
    public void shouldHaveOnGameWonAsLastCalledMethod(){
        // Given game
        // When four rounds have passed
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        // Then the last called method should be onGameWon
        assertThat(gameObserver.getLastCall(), is("onGameWon"));
    }
    @Test
    public void shouldHaveOnDrawCardAsSecondToLastCalledMethod(){
        // When given game
        // And Pedersens turn starts and he draws a card
        game.endTurn();
        // Then last called method should be onDrawCard
        assertThat(gameObserver.getXToLastCall(2), is("onCardDraw"));
    }



}

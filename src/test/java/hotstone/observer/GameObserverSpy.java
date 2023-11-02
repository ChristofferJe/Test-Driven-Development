package hotstone.observer;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class GameObserverSpy implements GameObserver {
    private String lastCall;

    public GameObserverSpy(){
        lastCall = "none";
    }

    @Override
    public void onCardPlay(Player who, Card card) {
        lastCall = "onCardPlay";
    }

    @Override
    public void onTurnChangeTo(Player playerBecomingActive) {
        lastCall = "onTurnChangeTo";
    }

    @Override
    public void onAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        lastCall = "onAttackCard";
    }

    @Override
    public void onAttackHero(Player playerAttacking, Card attackingCard) {
        lastCall = "onAttackHero";
    }

    @Override
    public void onUsePower(Player who) {
        lastCall = "onUsePower";
    }

    @Override
    public void onCardDraw(Player who, Card drawnCard) {
        lastCall = "onCardDraw";
    }

    @Override
    public void onCardUpdate(Card card) {
        lastCall = "onCardUpdate";
    }

    @Override
    public void onCardRemove(Player who, Card card) {
        lastCall = "onCardRemove";
    }

    @Override
    public void onHeroUpdate(Player who) {
        lastCall = "onHeroUpdate";
    }

    @Override
    public void onGameWon(Player playerWinning) {
        lastCall = "onGameWon";
    }
    public String getLastCall(){return lastCall;}
}

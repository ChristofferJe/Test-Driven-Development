package hotstone.observer;

import hotstone.framework.Card;
import hotstone.framework.Player;

import java.util.ArrayList;

public class GameObserverSpy implements GameObserver {
    private ArrayList<String> calls = new ArrayList<>();

    public GameObserverSpy(){ calls.add("none"); }

    @Override
    public void onCardPlay(Player who, Card card) {calls.add("onCardPlay"); }

    @Override
    public void onTurnChangeTo(Player playerBecomingActive) {
        calls.add("onTurnChangeTo");
    }

    @Override
    public void onAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        calls.add("onAttackCard");
    }

    @Override
    public void onAttackHero(Player playerAttacking, Card attackingCard) {
        calls.add("onAttackHero");
    }

    @Override
    public void onUsePower(Player who) {
        calls.add("onUsePower");
    }

    @Override
    public void onCardDraw(Player who, Card drawnCard) {
        calls.add("onCardDraw");
    }

    @Override
    public void onCardUpdate(Card card) {
        calls.add("onCardUpdate");
    }

    @Override
    public void onCardRemove(Player who, Card card) {
        calls.add("onCardRemove");
    }

    @Override
    public void onHeroUpdate(Player who) {
        calls.add("onHeroUpdate");
    }

    @Override
    public void onGameWon(Player playerWinning) {
        calls.add("onGameWon");
    }
    public String getLastCall(){return calls.get(calls.size() - 1);}

    public String getXToLastCall(int index){return calls.get(calls.size() - index); }
}

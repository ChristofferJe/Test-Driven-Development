package hotstone.variants;

import hotstone.framework.*;
import hotstone.observer.GameObserver;

import java.util.ArrayList;

public class TranscriptedGame implements Game {
    private final Game innerGame;

    public TranscriptedGame(Game innerGame){
        this.innerGame = innerGame;
    }

    @Override
    public Player getPlayerInTurn() {
        return innerGame.getPlayerInTurn();
    }

    @Override
    public Hero getHero(Player who) {
        return innerGame.getHero(who);
    }

    @Override
    public Player getWinner() {
        return innerGame.getWinner();
    }

    @Override
    public int getTurnNumber() {
        return innerGame.getTurnNumber();
    }

    @Override
    public int getDeckSize(Player who) {
        return innerGame.getDeckSize(who);
    }

    @Override
    public Card getCardInHand(Player who, int indexInHand) {
        return innerGame.getCardInHand(who, indexInHand);
    }

    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return innerGame.getHand(who);
    }

    @Override
    public int getHandSize(Player who) {
        return innerGame.getHandSize(who);
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return innerGame.getCardInField(who, indexInField);
    }

    @Override
    public Iterable<? extends Card> getField(Player who) {
        return innerGame.getField(who);
    }

    @Override
    public int getFieldSize(Player who) {
        return innerGame.getFieldSize(who);
    }

    @Override
    public void endTurn() {
        Player player = innerGame.getPlayerInTurn();
        System.out.println(player + " ended turn.");

        innerGame.endTurn();
    }

    @Override
    public Status playCard(Player who, Card card) {
        Status status = innerGame.playCard(who, card);
        boolean statusOk = status == Status.OK;
        if(statusOk) {
            Player player = innerGame.getPlayerInTurn();
            String cardPlayed = card.getName();
            System.out.println(player + " played " + cardPlayed + ".");
        }
        return status;
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        Status status = innerGame.attackCard(playerAttacking, attackingCard, defendingCard);
        boolean statusOk = status == Status.OK;
        if(statusOk) {
            Player player = innerGame.getPlayerInTurn();
            String cardAtt = attackingCard.getName();
            String cardDef = defendingCard.getName();
            System.out.println(player + " attacked " + cardDef + " with " + cardAtt + ".");
        }
        return status;
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        Status status = innerGame.attackHero(playerAttacking, attackingCard);
        boolean statusOk = status == Status.OK;
        if(statusOk) {
            Player player = innerGame.getPlayerInTurn();
            String cardAtt = attackingCard.getName();
            System.out.println(player + " attacked Hero with " + cardAtt+ ".");
        }
        return status;
    }

    @Override
    public Status usePower(Player who) {
        Status status = innerGame.usePower(who);
        boolean statusOk = status == Status.OK;
        if(statusOk) {
            Player player = innerGame.getPlayerInTurn();
            System.out.println(player + " used Hero power.");
        }
        return status;
    }

    @Override
    public void addObserver(GameObserver observer) {
        innerGame.addObserver(observer);
    }

}


package hotstone.variants;

import hotstone.framework.*;
import hotstone.observer.GameObserver;

import java.util.ArrayList;

public class TranscriptedGame implements Game {
    private final Game game;
    private boolean transcripting;
    private ArrayList<String> transcripts;

    public TranscriptedGame(Game game){
        this.game = game;
        transcripting = true;
        transcripts = new ArrayList<>();
    }

    public void turnTransctiptingOn(){transcripting = true;}
    public void turnTransctiptingOff(){transcripting = false;}
    public void printTranscript(){
        System.out.println(transcripts);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Hero getHero(Player who) {
        return game.getHero(who);
    }

    @Override
    public Player getWinner() {
        return game.getWinner();
    }

    @Override
    public int getTurnNumber() {
        return game.getTurnNumber();
    }

    @Override
    public int getDeckSize(Player who) {
        return game.getDeckSize(who);
    }

    @Override
    public Card getCardInHand(Player who, int indexInHand) {
        return game.getCardInHand(who, indexInHand);
    }

    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return game.getHand(who);
    }

    @Override
    public int getHandSize(Player who) {
        return game.getHandSize(who);
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return game.getCardInField(who, indexInField);
    }

    @Override
    public Iterable<? extends Card> getField(Player who) {
        return game.getField(who);
    }

    @Override
    public int getFieldSize(Player who) {
        return game.getFieldSize(who);
    }

    @Override
    public void endTurn() {
        if(transcripting) {
            String player = game.getPlayerInTurn().toString();
            transcripts.add(player + " ended turn.");
        }
        game.endTurn();
    }

    @Override
    public Status playCard(Player who, Card card) {
        Status status = game.playCard(who, card);
        boolean statusOk = status == Status.OK;
        if(transcripting & statusOk) {
            String player = who.toString();
            String cardPlayed = card.getName();
            transcripts.add(player + " played " + cardPlayed + ".");
        }
        return status;
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        Status status = game.attackCard(playerAttacking, attackingCard, defendingCard);
        boolean statusOk = status == Status.OK;
        if(transcripting & statusOk) {
            String player = playerAttacking.toString();
            String cardAtt = attackingCard.getName();
            String cardDef = defendingCard.getName();
            transcripts.add(player + " attacked " + cardDef + " with " + cardAtt + ".");
        }
        return status;
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        Status status = game.attackHero(playerAttacking, attackingCard);
        boolean statusOk = status == Status.OK;
        if(transcripting & statusOk) {
            String player = playerAttacking.toString();
            String cardAtt = attackingCard.getName();
            transcripts.add(player + " attacked Hero with " + cardAtt+ ".");
        }
        return status;
    }

    @Override
    public Status usePower(Player who) {
        Status status = game.usePower(who);
        boolean statusOk = status == Status.OK;
        if(transcripting & statusOk) {
            String player = who.toString();
            transcripts.add(player + " used Hero power.");
        }
        return status;
    }

    @Override
    public void addObserver(GameObserver observer) {
        game.addObserver(observer);
    }

}


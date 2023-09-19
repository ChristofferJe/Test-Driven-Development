/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.*;

import java.util.*;

/** This is the 'temporary test stub' in TDD
 * terms: the initial empty but compilable implementation
 * of the game interface.
 *
 * It already includes a bit of FAKE-IT code for the first
 * test case about hand management.
 *
 * Start solving the AlphaStone exercise by
 * following the TDD rythm: pick a one-step-test
 * from your test list, quickly add a test,
 * run it to see it fail, and then modify this
 * implementing class (and supporting classes)
 * to make your test case run. Refactor and repeat.
 *
 * While this is the implementation of Game for
 * the AlphaStone game, you will constantly
 * refactor it over the course of the exercises
 * to become the 'core implementation' which will
 * enable a lot of game variants. This is also
 * why it is not called 'AlphaGame'.
 */

public class StandardHotStoneGame implements Game {
  private int turnNumber;
  private Hero findusHero;
  private Hero peddersenHero;
  private ArrayList<Card> findusDeck;
  private ArrayList<Card> peddersenDeck;
  private ArrayList<Card> findusHand;
  private ArrayList<Card> peddersenHand;

  private ArrayList<Card> findusField;
  private ArrayList<Card> peddersenField;
  private HashMap<Player, ArrayList<Card>> handMap;
  private HashMap<Player, ArrayList<Card>> deckMap;
  private HashMap<Player, ArrayList<Card>> fieldMap;
  private  HashMap<Player, Hero> heroMap;
  private HashMap<Player, Player> getOtherPlayer;
  private ManaStrategy manaStrategy;
  private WinnnerStrategy winnerStrategy;
  private HeroStrategy heroStrategy;
  private DeckStrategy deckStrategy;


  public StandardHotStoneGame(Version version) {
    setupGame(version);

    turnNumber = 1;
    findusHero = heroStrategy.assignHero(Player.FINDUS);
    findusDeck = deckStrategy.createDeck(Player.FINDUS);
    findusHand = new ArrayList<>();
    peddersenHero = heroStrategy.assignHero(Player.PEDDERSEN);
    peddersenDeck = deckStrategy.createDeck(Player.PEDDERSEN);
    peddersenHand = new ArrayList<>();
    findusField = new ArrayList<>();
    peddersenField = new ArrayList<>();

    handMap = new HashMap<>();
    handMap.put(Player.FINDUS,findusHand);
    handMap.put(Player.PEDDERSEN,peddersenHand);

    deckMap = new HashMap<>();
    deckMap.put(Player.FINDUS,findusDeck);
    deckMap.put(Player.PEDDERSEN,peddersenDeck);

    fieldMap = new HashMap<>();
    fieldMap.put(Player.FINDUS,findusField);
    fieldMap.put(Player.PEDDERSEN,peddersenField);

    heroMap = new HashMap<>();
    heroMap.put(Player.FINDUS, findusHero);
    heroMap.put(Player.PEDDERSEN, peddersenHero);

    getOtherPlayer = new HashMap<>();
    getOtherPlayer.put(Player.FINDUS, Player.PEDDERSEN);
    getOtherPlayer.put(Player.PEDDERSEN, Player.FINDUS);

    manaStrategy.restoreMana(Player.FINDUS, this);
    manaStrategy.restoreMana(Player.PEDDERSEN, this);

    drawCard(Player.FINDUS, 3);
    drawCard(Player.PEDDERSEN, 3);
  }

  private void setupGame(Version version) {
    if(version == Version.ALPHA){
      manaStrategy = new AlphaManaStrategy();
      winnerStrategy = new AlphaWinnerStrategy();
      heroStrategy = new BabyHeroStrategy();
      deckStrategy = new SpanishDeckStrategy();
    }
    if(version == Version.BETA){
      manaStrategy = new BetaManaStrategy();
      winnerStrategy = new BetaWinnerStrategy();
      heroStrategy = new BabyHeroStrategy();
      deckStrategy = new SpanishDeckStrategy();
    }
    if(version == Version.GAMMA){
      manaStrategy = new AlphaManaStrategy();
      winnerStrategy = new AlphaWinnerStrategy();
      heroStrategy = new GammaHeroStrategy();
      deckStrategy = new SpanishDeckStrategy();
    }
    if(version == Version.DELTA){
      manaStrategy = new DeltaManaStrategy();
      winnerStrategy = new AlphaWinnerStrategy();
      heroStrategy = new BabyHeroStrategy();
      deckStrategy = new DeltaDeckStrategy();
    }
  }

  @Override
  public Player getPlayerInTurn() {
    if (turnNumber % 2 == 1) {
      return Player.FINDUS;
    } else {
      return Player.PEDDERSEN;
    }
  }

  @Override
  public Hero getHero(Player who) { return heroMap.get(who); }

  @Override
  public Player getWinner() { return winnerStrategy.getWinner(this); }

  @Override
  public int getTurnNumber() {return turnNumber;}

  @Override
  public int getDeckSize(Player who) {return deckMap.get(who).size();}

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    return handMap.get(who).get(indexInHand);
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {return handMap.get(who);}

  @Override
  public int getHandSize(Player who) {return handMap.get(who).size();}

  @Override
  public Card getCardInField(Player who, int indexInField) {
    return fieldMap.get(who).get(indexInField);
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {return fieldMap.get(who);}

  @Override
  public int getFieldSize(Player who) {return fieldMap.get(who).size();}

  @Override
  public void endTurn() {
    Player player = getPlayerInTurn();
    Player otherPlayer = getOtherPlayer.get(player);
    // Set hero power to useable again
    StandardHero stdHero = asStandardHero(getHero(player)) ;
    stdHero.setPowerStatus(true);

    // Draw card and activate minions for the player who is now in turn
    drawCard(otherPlayer, 1);
    // Set active

    for (Card c : fieldMap.get(otherPlayer)) {
      StandardCard stdCard = asStandardCard(c);
      stdCard.setStatus(true);
    }
    turnNumber += 1;

    // Restore mana for opponent player's hero
    manaStrategy.restoreMana(otherPlayer, this);
  }

  private void drawCard(Player who, int amount) {
    if(!deckMap.get(who).isEmpty()){
      for (int i = 0; i < amount; i++) {
        Card card = deckMap.get(who).get(0);
        deckMap.get(who).remove(0);
        handMap.get(who).add(0,card);
      }
    } else {
      StandardHero stdHero = asStandardHero(getHero(who));
      stdHero.decreaseHealth(2);
    }
  }

  @Override
  public Status playCard(Player who, Card card) {
    // Check if player in turn
    if(!(getPlayerInTurn() == who)) return Status.NOT_PLAYER_IN_TURN;
    // Check if playing card from own hand
    if(!(card.getOwner() == who)) return Status.NOT_OWNER;
    // Check if enough mana
    if (getHero(who).getMana() < card.getManaCost()) return Status.NOT_ENOUGH_MANA;
    // Add card in field index 0 and remove from hand
    fieldMap.get(who).add(0, card);
    handMap.get(who).remove(card);
    // Decrease mana
    StandardHero stdHero = asStandardHero(getHero(who));
    stdHero.decreaseMana(card.getManaCost());

    return Status.OK;

  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    // Check if attacking player is in turn
    if(!(getPlayerInTurn() == playerAttacking)) return Status.NOT_PLAYER_IN_TURN;
    // Check if attacking player is owner of attacking card
    if(!(attackingCard.getOwner() == playerAttacking)) return Status.NOT_OWNER;
    // Check if card is active
    if(!attackingCard.isActive()) return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    // Check if attacking own minion
    if(attackingCard.getOwner() ==  defendingCard.getOwner()) return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION;
    // Cast attacking and defending card to StandardCard class
    StandardCard stdAttackingCard = asStandardCard(attackingCard);
    StandardCard stdDefendingCard = asStandardCard(defendingCard);
    // Change the health of the attacking card and attacked card
    stdAttackingCard.decreaseHealth(defendingCard.getAttack());
    stdDefendingCard.decreaseHealth(attackingCard.getAttack());
    // Check if card's health are below zero and set inactive
    setInactiveAndRemoveIfDead(defendingCard, getOtherPlayer.get(playerAttacking));
    setInactiveAndRemoveIfDead(attackingCard,playerAttacking);
    // Set inactive if still alive
    stdAttackingCard.setStatus(false);

    return Status.OK;

  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    // Get player whose hero is being attacked
    Player playerAttacked = getOtherPlayer.get(playerAttacking);
    // Check if attacking player is in turn
    if(!(getPlayerInTurn() == playerAttacking)) return Status.NOT_PLAYER_IN_TURN;
    // Check if player attacking owns attacking card
    if(!(attackingCard.getOwner() == playerAttacking)) return Status.NOT_OWNER;
    // Check if card is active
    if(!attackingCard.isActive()) return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    // Cast attacking card to StandardCard class
    StandardCard stdCard = asStandardCard(attackingCard);
    // Reduce the attacked heroes health
    StandardHero stdHero = asStandardHero(getHero(playerAttacked));
    stdHero.decreaseHealth(stdCard.getAttack());
    // Set attacking card inactive
    stdCard.setStatus(false);
    return Status.OK;
  }

  @Override
  public Status usePower(Player who) {
    // Check if player in turn
    if(!(getPlayerInTurn() == who)) return Status.NOT_PLAYER_IN_TURN;
    // Check that the player can use its hero power
    if (!getHero(who).canUsePower()) return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    // Check if enough mana
    if (getHero(who).getMana() < 2) return Status.NOT_ENOUGH_MANA;
    StandardHero stdHero = asStandardHero(getHero(who));
    stdHero.setPowerStatus(false);
    stdHero.decreaseMana(2);
    heroStrategy.execPower(who, this);
    return Status.OK;
    }

    private void setInactiveAndRemoveIfDead(Card card, Player owner){
      if(card.getHealth()<1){
        StandardCard stdCard = asStandardCard(card);
        stdCard.setStatus(false);
        fieldMap.get(owner).remove(card);
      }
    }

    private StandardCard asStandardCard(Card card){return (StandardCard) card;}

    private StandardHero asStandardHero(Hero hero){return (StandardHero) hero;}




}
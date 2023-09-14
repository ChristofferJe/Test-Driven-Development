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
  private StandardHero findusHero;
  private StandardHero peddersenHero;
  private ArrayList<Card> findusDeck;
  private ArrayList<Card> peddersenDeck;
  private ArrayList<Card> findusHand;
  private ArrayList<Card> peddersenHand;

  private ArrayList<StandardCard> findusField;
  private ArrayList<StandardCard> peddersenField;
  private HashMap<Player, ArrayList<Card>> handMap;
  private HashMap<Player, ArrayList<Card>> deckMap;
  private HashMap<Player, ArrayList<StandardCard>> fieldMap;
  private HashMap<Player, Player> getOtherPlayer;


  public StandardHotStoneGame() {
    turnNumber = 1;
    findusHero = new StandardHero();
    findusDeck = createAlphaDeck();
    findusHand = new ArrayList<>();
    peddersenHero = new StandardHero();
    peddersenDeck = createAlphaDeck();
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

    getOtherPlayer = new HashMap<>();
    getOtherPlayer.put(Player.FINDUS, Player.PEDDERSEN);
    getOtherPlayer.put(Player.PEDDERSEN, Player.FINDUS);

    drawCard(Player.FINDUS, 3);
    drawCard(Player.PEDDERSEN, 3);
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
  public Hero getHero(Player who) {
    if (who == Player.FINDUS) {
      return findusHero;
    } else {
      return peddersenHero;
    }
  }


  @Override
  public Player getWinner() {
    if (getTurnNumber() > 8) {
      return Player.FINDUS;
    } else {
      return null;
    }
  }

  @Override
  public int getTurnNumber() {
    return turnNumber;
  }

  @Override
  public int getDeckSize(Player who) {
    return deckMap.get(who).size();
  }


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
    StandardHero hero = (StandardHero) getHero(player);
    hero.setPowerStatus(true);

    // Restore mana
    hero.setMana(3);

    // Draw card and activate minions for the player who is now in turn
    drawCard(otherPlayer, 1);
    // Set active

    for (StandardCard c : fieldMap.get(otherPlayer)) {
      c.setStatus(true);
    }
    turnNumber += 1;
  }

  private void drawCard(Player who, int amount) {
    if(!deckMap.get(who).isEmpty()){
      for (int i = 0; i < amount; i++) {
        Card card = deckMap.get(who).get(0);
        deckMap.get(who).remove(0);
        handMap.get(who).add(0,card);
      }
    } else {
      StandardHero hero = (StandardHero) getHero(who);
      hero.decreaseHealth(2);
    }
  }

  @Override
  public Status playCard(Player who, Card card) {
    if (getHero(who).getMana() < card.getManaCost()) {
      return Status.NOT_ENOUGH_MANA;
    } else{
      fieldMap.get(who).add(0, (StandardCard) card);
      handMap.get(who).remove(card);
      StandardHero hero = (StandardHero) getHero(who);
      hero.decreaseMana(card.getManaCost());
      return Status.OK;
    }
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    // Check if card is active
    if(!attackingCard.isActive()) {
      // If inactive return appropriate status
      return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    } else {
      // If active attack and set inactive
      StandardCard standardAttackingCard = (StandardCard) attackingCard;
      standardAttackingCard.setStatus(false);
      // Change the health of the attacking card and attacked card
      StandardCard standardDefendingCard = (StandardCard) defendingCard;
      standardAttackingCard.decreaseHealth(2);
      standardDefendingCard.decreaseHealth(1);

      return Status.OK;
    }
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    // Get player whose hero is being attacked
    Player playerAttacked = getOtherPlayer.get(playerAttacking);
    // Check if card is active
    if(!attackingCard.isActive()) {
      // If inactive return appropriate status
      return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    } else {
      // If active, attack and set inactive
      StandardCard card = (StandardCard) attackingCard;
      card.setStatus(false);
      // Reduce the attacked heroes health
      StandardHero hero = (StandardHero) getHero(playerAttacked);
      hero.decreaseHealth(card.getAttack());
      return Status.OK;
    }
  }

  @Override
  public Status usePower(Player who) {
    // Control that the player can use its hero power
    if (!getHero(who).canUsePower()){
      return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    }else if(getHero(who).getMana() < 2) {
      return Status.NOT_ENOUGH_MANA;
    } else {
      StandardHero hero = (StandardHero) getHero(who);
      hero.setPowerStatus(false);
      hero.decreaseMana(2);
      return Status.OK;
    }
    }

    private ArrayList<Card> createAlphaDeck(){
      ArrayList<Card> deck = new ArrayList<Card>();
      Card uno = new StandardCard(GameConstants.UNO_CARD, 1, 1,1);
      Card dos = new StandardCard(GameConstants.DOS_CARD, 2, 2,2);
      Card tres = new StandardCard(GameConstants.TRES_CARD, 3, 3,3);
      Card cuatro = new StandardCard(GameConstants.CUATRO_CARD, 2, 3,1);
      Card cinco = new StandardCard(GameConstants.CINCO_CARD, 3, 5,1);
      Card seis = new StandardCard(GameConstants.SEIS_CARD, 2, 1,3);
      Card siete = new StandardCard(GameConstants.SIETE_CARD, 3, 2,4);
      deck.add(0,uno);
      deck.add(1,dos);
      deck.add(2,tres);
      deck.add(3,cuatro);
      deck.add(4,cinco);
      deck.add(5,seis);
      deck.add(6,siete);
      return deck;
    }


}
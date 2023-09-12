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
  private int turnNumber = 1;
  private StandardHero findusHero;
  private StandardDeck findusDeck;
  private StandardHand findusHand;
  private StandardHero peddersenHero;
  private StandardDeck peddersenDeck;
  private StandardHand peddersenHand;
  private StandardField findusField;
  private StandardField peddersenField;

  public StandardHotStoneGame() {
    findusHero = new StandardHero();
    findusDeck = new StandardDeck();
    findusHand = new StandardHand();
    peddersenHero = new StandardHero();
    peddersenDeck = new StandardDeck();
    peddersenHand = new StandardHand();
    findusField = new StandardField();
    peddersenField = new StandardField();
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
    return getDeckObject(who).getSize();
  }


  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    ArrayList<Card> hand = (ArrayList<Card>) getHand(Player.FINDUS);
    return hand.get(indexInHand);
  }


  @Override
  public Iterable<? extends Card> getHand(Player who) {
    return getHandObject(who).getHand();
  }

  @Override
  public int getHandSize(Player who) {
    return getHandObject(who).getSize();
  }


  @Override
  public Card getCardInField(Player who, int indexInField) {
    ArrayList<Card> field = (ArrayList<Card>) getField(Player.FINDUS);
    return field.get(indexInField);
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    return getFieldObject(who).getField();
  }


  @Override
  public int getFieldSize(Player who) {
    return getFieldObject(who).getSize();
  }

  @Override
  public void endTurn() {
    Player player = getPlayerInTurn();
    // Set hero power to useable again
    StandardHero hero = (StandardHero) getHero(player);
    hero.setPowerStatus(true);

    // Restore mana
    hero.setMana(3);

    // Draw card and activate minions for the player who is now in turn
    if (player == Player.FINDUS) {
      // Draw card
      drawCard(Player.PEDDERSEN, 1);
      // Set active
      ArrayList<StandardCard> field = (ArrayList<StandardCard>) getField(Player.PEDDERSEN);
      for (StandardCard c : field) {
        c.setStatus(true);
      }
    } else {
      // Draw card
      drawCard(Player.FINDUS, 1);
      // Set active
      ArrayList<StandardCard> field = (ArrayList<StandardCard>) getField(Player.FINDUS);
      for (StandardCard c : field) {
        c.setStatus(true);
      }
    }
    turnNumber += 1;
  }

  private void drawCard(Player who, int amount) {
    if(getDeckObject(who).getSize() > 0){
      for (int i = 0; i < amount; i++) {
        Card card = getDeckObject(who).draw();
        getHandObject(who).add(card);
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
      getFieldObject(who).add(card);
      getHandObject(who).remove(card);
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
      StandardCard card = (StandardCard) attackingCard;
      card.setStatus(false);
      return Status.OK;
    }
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    return null;
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


  public StandardField getFieldObject(Player who) {
    if (who == Player.FINDUS) {
      return findusField;
    } else {
      return peddersenField;
    }
  }

  public StandardHand getHandObject(Player who) {
    if (who == Player.FINDUS) {
      return findusHand;
    } else {
      return peddersenHand;
    }
  }

  public StandardDeck getDeckObject(Player who) {
    if (who == Player.FINDUS) {
      return findusDeck;
    } else {
      return peddersenDeck;
    }
  }
}
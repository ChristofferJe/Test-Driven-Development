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
  private final HashMap<Player, ArrayList<Card>> hands;
  private final HashMap<Player, ArrayList<Card>> decks;
  private final HashMap<Player, ArrayList<Card>> fields;
  private final HashMap<Player, Hero> heroes;
  private ManaStrategy manaStrategy;
  private WinnnerStrategy winnerStrategy;
  private HeroStrategy heroStrategy;
  private DeckStrategy deckStrategy;


  public StandardHotStoneGame(Version version) {
    setupGame(version);

    turnNumber = 1;

    Hero findusHero = heroStrategy.createHero(Player.FINDUS);
    ArrayList<Card> findusDeck = deckStrategy.createDeck(Player.FINDUS);
    ArrayList<Card> findusHand = new ArrayList<>();
    ArrayList<Card> findusField = new ArrayList<>();


    Hero peddersenHero = heroStrategy.createHero(Player.PEDDERSEN);
    ArrayList<Card> peddersenDeck = deckStrategy.createDeck(Player.PEDDERSEN);
    ArrayList<Card> peddersenHand = new ArrayList<>();
    ArrayList<Card> peddersenField = new ArrayList<>();

    hands = new HashMap<>();
    hands.put(Player.FINDUS, findusHand);
    hands.put(Player.PEDDERSEN, peddersenHand);

    decks = new HashMap<>();
    decks.put(Player.FINDUS, findusDeck);
    decks.put(Player.PEDDERSEN, peddersenDeck);

    fields = new HashMap<>();
    fields.put(Player.FINDUS, findusField);
    fields.put(Player.PEDDERSEN, peddersenField);

    heroes = new HashMap<>();
    heroes.put(Player.FINDUS, findusHero);
    heroes.put(Player.PEDDERSEN, peddersenHero);

    restoreMana(Player.FINDUS);
    restoreMana(Player.PEDDERSEN);

    initializeHands();
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
      deckStrategy = new DishDeckStrategy();
    }
  }

  private void initializeHands() {
    // Each player draws three cards
    for(int i=0; i<3; i++){drawCard(Player.FINDUS); drawCard(Player.PEDDERSEN);}
  }

  @Override
  public Player getPlayerInTurn() {
    boolean turnNumberIsEven = turnNumber % 2 == 0;
    if (turnNumberIsEven) {
      return Player.PEDDERSEN;
    } else {
      return Player.FINDUS;
    }
  }

  @Override
  public Hero getHero(Player who) { return heroes.get(who); }

  @Override
  public Player getWinner() { return winnerStrategy.getWinner(this); }

  @Override
  public int getTurnNumber() {return turnNumber;}

  @Override
  public int getDeckSize(Player who) {return decks.get(who).size();}

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    return hands.get(who).get(indexInHand);
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {return hands.get(who);}

  @Override
  public int getHandSize(Player who) {return hands.get(who).size();}

  @Override
  public Card getCardInField(Player who, int indexInField) {
    return fields.get(who).get(indexInField);
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {return fields.get(who);}

  @Override
  public int getFieldSize(Player who) {return fields.get(who).size();}

  @Override
  public void endTurn() {
    Player player = getPlayerInTurn();
    Player otherPlayer = Utility.computeOpponent(player);
    // Set hero power to useable again
    StandardHero stdHero = asStandardHero(getHero(player)) ;
    stdHero.setPowerStatus(true);

    // Draw card and activate minions for the player who is now in turn
    drawCard(otherPlayer);
    // Set active

    for (Card c : fields.get(otherPlayer)) {
      StandardCard stdCard = asStandardCard(c);
      stdCard.setStatus(true);
    }
    turnNumber += 1;

    // Restore mana for opponent player's hero
    restoreMana(otherPlayer);
  }

  private void drawCard(Player who) {
    if(!decks.get(who).isEmpty()){
        Card card = decks.get(who).get(0);
        decks.get(who).remove(0);
        hands.get(who).add(0,card);
    } else {
      StandardHero stdHero = asStandardHero(getHero(who));
      stdHero.decreaseHealth(2);
    }
  }

  @Override
  public Status playCard(Player who, Card card) {
    Status status = isPlayCardAllowed(who, card);
    if (status != Status.OK) return status;
    moveCardFromHandToField(who, card);
    decreaseHeroMana(who, card.getManaCost());
    return status;

  }

  private void decreaseHeroMana(Player who, int manaAmount) {
    // Cast and decrease mana
    StandardHero stdHero = asStandardHero(getHero(who));
    stdHero.decreaseMana(manaAmount);
  }

  private void moveCardFromHandToField(Player who, Card card) {
    // Add card in field index 0 and remove from hand
    fields.get(who).add(0, card);
    hands.get(who).remove(card);
  }

  private Status isPlayCardAllowed(Player who, Card card) {
    // Check if player in turn
    if(!isPlayerInTurn(who)) return Status.NOT_PLAYER_IN_TURN;
    // Check if playing card from own hand
    if(!isOwner(who,card)) return Status.NOT_OWNER;
    // Check if enough mana
    if (!hasEnoughMana(who, card.getManaCost())) return Status.NOT_ENOUGH_MANA;
    return Status.OK;
  }

  private boolean hasEnoughMana(Player who, int manaAmount) {
      return getHero(who).getMana() >= manaAmount;
  }

  private static boolean isOwner(Player who, Card card) {
      return card.getOwner() == who;
  }

  private boolean isPlayerInTurn(Player who) {
      return getPlayerInTurn() == who;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    Status status = isAttackCardAllowed(playerAttacking, attackingCard, defendingCard);
    if (status != Status.OK) return status;
    executeAttackCard(attackingCard, defendingCard);
    return status;

  }

  private void executeAttackCard(Card attackingCard, Card defendingCard) {
    // Change the health of the attacking card and attacked card
    decreaseCardHealth(defendingCard, attackingCard.getAttack());
    decreaseCardHealth(attackingCard, defendingCard.getAttack());
    // Check if card's health are below zero and set inactive
    setInactiveAndRemoveIfDead(defendingCard);
    setInactiveAndRemoveIfDead(attackingCard);
    // Set inactive if still alive
    deactivateCard(attackingCard);
  }

  private void deactivateCard(Card card) {
    StandardCard stdCard = asStandardCard(card);
    stdCard.setStatus(false);
  }

  private void decreaseCardHealth(Card card, int amount) {
    StandardCard stdCard = asStandardCard(card);
    stdCard.decreaseHealth(amount);
  }

  private Status isAttackCardAllowed(Player playerAttacking, Card attackingCard, Card defendingCard) {
    // Check if attacking player is in turn
    if(!isPlayerInTurn(playerAttacking)) return Status.NOT_PLAYER_IN_TURN;
    // Check if attacking player is owner of attacking card
    if(!isOwner(playerAttacking, attackingCard)) return Status.NOT_OWNER;
    // Check if card is active
    if(!isCardActive(attackingCard)) return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    // Check if attacking own minion
    if(attackingCard.getOwner() ==  defendingCard.getOwner()) return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION;
    return Status.OK;
  }

  private static boolean isCardActive(Card attackingCard) {
    return attackingCard.isActive();
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    Status status = isAttackHeroAllowed(playerAttacking, attackingCard);
    if (status != Status.OK) return status;
    executeAttackHero(attackingCard);
    return status;
  }

  private void executeAttackHero(Card attackingCard) {
    // Reduce the attacked heroes health
    decreaseHeroHealth(Utility.computeOpponent(attackingCard.getOwner()), attackingCard.getAttack());
    // Set attacking card inactive
    deactivateCard(attackingCard);
  }

  private void decreaseHeroHealth(Player who, int amount) {
    StandardHero stdHero = asStandardHero(getHero(who));
    stdHero.decreaseHealth(amount);
  }

  private Status isAttackHeroAllowed(Player playerAttacking, Card attackingCard) {
    // Check if attacking player is in turn
    if(!isPlayerInTurn(playerAttacking)) return Status.NOT_PLAYER_IN_TURN;
    // Check if player attacking owns attacking card
    if(!isOwner(playerAttacking, attackingCard)) return Status.NOT_OWNER;
    // Check if card is active
    if(!isCardActive(attackingCard)) return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    return Status.OK;
  }

  @Override
  public Status usePower(Player who) {
    Status status = isPowerAllowed(who);
    if (status != Status.OK) return status;
    StandardHero stdHero = asStandardHero(getHero(who));
    stdHero.setPowerStatus(false);
    stdHero.decreaseMana(2);
    heroStrategy.execPower(who, this);
    return Status.OK;
    }

  private Status isPowerAllowed(Player who) {
    // Check if player in turn
    if(!isPlayerInTurn(who)) return Status.NOT_PLAYER_IN_TURN;
    // Check that the player can use its hero power
    if (hasUsedPower(who)) return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    // Check if enough mana
    if (!hasEnoughMana(who, 2)) return Status.NOT_ENOUGH_MANA;
    return Status.OK;
  }

  private boolean hasUsedPower(Player who) {
    return !getHero(who).canUsePower();
  }

  private void setInactiveAndRemoveIfDead(Card card){
      boolean isCardDead = card.getHealth() < 1;
      if(isCardDead){
        deactivateCard(card);
        fields.get(card.getOwner()).remove(card);
      }
    }

    private void restoreMana(Player who){
      StandardHero stdHero = asStandardHero(getHero(who));
      int mana = manaStrategy.calculateMana(getTurnNumber());
      stdHero.setMana(mana);
    }

    private StandardCard asStandardCard(Card card){return (StandardCard) card;}

    private StandardHero asStandardHero(Hero hero){return (StandardHero) hero;}

}
/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.broker.client;

import com.google.gson.reflect.TypeToken;
import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.*;
import hotstone.observer.GameObserver;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Template/starter code for your ClientProxy of Game.
 */
public class GameClientProxy implements Game, ClientProxy {
  private final Requestor requestor;
  private final String singletonID;

  public GameClientProxy(Requestor requestor) {
    this.requestor = requestor;
    singletonID = "singleton";
  }

  @Override
  public int getTurnNumber() {
    int turnNumber = requestor.sendRequestAndAwaitReply(singletonID, OperationNames.GAME_GET_TURN_NUMBER, Integer.class);
    return turnNumber;
  }

  @Override
  public Player getPlayerInTurn() {
    Player playerInTurn = requestor.sendRequestAndAwaitReply(singletonID, OperationNames.GAME_GET_PLAYER_IN_TURN, Player.class);
    return playerInTurn;
  }

  @Override
  public Hero getHero(Player who) {
    String id = requestor.sendRequestAndAwaitReply(singletonID,
            OperationNames.GAME_GET_HERO,
            String.class,
            who);
    Hero hero = new HeroClientProxy(requestor, id);
    return hero;
  }

  @Override
  public Player getWinner() {
    Player winner = requestor.sendRequestAndAwaitReply(singletonID, OperationNames.GAME_GET_WINNER, Player.class);
    return winner;
  }

  @Override
  public int getDeckSize(Player who) {
    int decksize = requestor.sendRequestAndAwaitReply(singletonID,
                  OperationNames.GAME_GET_DECK_SIZE,
                  Integer.class,
                  who);
    return decksize;
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    String id = requestor.sendRequestAndAwaitReply(singletonID,
            OperationNames.GAME_GET_CARD_IN_HAND,
            String.class,
            who, indexInHand);
    Card card = new CardClientProxy(requestor, id);
    return card;
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    Type collectionType = new TypeToken<List<String>>(){}.getType();
    List<String> idList = requestor.sendRequestAndAwaitReply(singletonID, OperationNames.GAME_GET_HAND, collectionType, who);
    ArrayList<Card> hand = new ArrayList<>();
    for(String i: idList){
      hand.add(new CardClientProxy(requestor, i));
    }
    return hand;
  }

  @Override
  public int getHandSize(Player who) {
    int handSize = requestor.sendRequestAndAwaitReply(singletonID,
            OperationNames.GAME_GET_HAND_SIZE,
            Integer.class,
            who);
    return handSize;
  }

  @Override
  public Card getCardInField(Player who, int indexInField) {
    String id = requestor.sendRequestAndAwaitReply(singletonID,
            OperationNames.GAME_GET_CARD_IN_FIELD,
            String.class,
            who, indexInField);
    Card card = new CardClientProxy(requestor, id);
    return card;
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    Type collectionType = new TypeToken<List<String>>(){}.getType();
    List<String> idList = requestor.sendRequestAndAwaitReply(singletonID, OperationNames.GAME_GET_FIELD, collectionType, who);
    ArrayList<Card> field = new ArrayList<>();
    for(String i: idList){
      field.add(new CardClientProxy(requestor, i));
    }
    return field;
  }

  @Override
  public int getFieldSize(Player who) {
    int fieldSize = requestor.sendRequestAndAwaitReply(singletonID,
            OperationNames.GAME_GET_FIELD_SIZE,
            Integer.class,
            who);
    return fieldSize;
  }

  @Override
  public void endTurn() {
  requestor.sendRequestAndAwaitReply(singletonID, OperationNames.GAME_END_OF_TURN, String.class);
  }

  @Override
  public Status playCard(Player who, Card card) {
    String id = card.getId();
    Status status = requestor.sendRequestAndAwaitReply(singletonID,
            OperationNames.GAME_PLAY_CARD,
            Status.class,
            who, id);
    return status;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    String attackingId = attackingCard.getId();
    String defendingId = defendingCard.getId();
    Status status = requestor.sendRequestAndAwaitReply(singletonID,
            OperationNames.GAME_ATTACK_CARD,
            Status.class,
            playerAttacking, attackingId, defendingId);
    return status;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    String cardId = attackingCard.getId();
    Status status = requestor.sendRequestAndAwaitReply(singletonID,
            OperationNames.GAME_ATTACK_HERO,
            Status.class,
            playerAttacking, cardId);
    return status;
  }

  @Override
  public Status usePower(Player who) {
    Status status = requestor.sendRequestAndAwaitReply(singletonID,
            OperationNames.GAME_USE_POWER,
            Status.class,
            who);
    return status;
  }

  @Override
  public void addObserver(GameObserver observer) {

  }
}

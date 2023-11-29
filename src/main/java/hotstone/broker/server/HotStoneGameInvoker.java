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

package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.client.CardClientProxy;
import hotstone.broker.common.OperationNames;
import hotstone.broker.doubles.StubCardForBroker;
import hotstone.broker.doubles.StubHeroForBroker;
import hotstone.doubles.StubCard;
import hotstone.framework.*;
import hotstone.standard.StandardCard;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/** Template code for solving the Broker exercises */
public class HotStoneGameInvoker implements Invoker {
  private final Game servant;
  private final Gson gson;
  private final Hero stubHero;
  private final HashMap<String, Card> cardNameService;
  private HashMap<String, Hero> heroNameService;

  public HotStoneGameInvoker(Game servant) {
    this.servant = servant;
    gson = new Gson();
    stubHero = new StubHeroForBroker();
    heroNameService = new HashMap<String, Hero>();
    cardNameService = new HashMap<String, Card>();
  }

  private Hero lookupHero(String objectID){ return heroNameService.get(objectID); }
  private Card lookupCard(String objectID) { return cardNameService.get(objectID); }

  @Override
  public String handleRequest(String request) {

    RequestObject requestObject = gson.fromJson(request,RequestObject.class);
    JsonArray array = JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();
    String objectID = requestObject.getObjectId();

    ReplyObject reply;

    try {
    if(requestObject.getOperationName().equals(OperationNames.GAME_GET_TURN_NUMBER)){
      int turnNumber = servant.getTurnNumber();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(turnNumber));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_GET_WINNER)){
      Player winner = servant.getWinner();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(winner));
      }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_GET_PLAYER_IN_TURN)){
      Player playerInTurn = servant.getPlayerInTurn();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(playerInTurn));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_GET_DECK_SIZE)){
      Player who = gson.fromJson(array.get(0), Player.class);
      int deckSize = servant.getDeckSize(who);
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(deckSize));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_GET_HAND_SIZE)){
      Player who = gson.fromJson(array.get(0), Player.class);
      int handSize = servant.getHandSize(who);
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(handSize));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_GET_FIELD_SIZE)){
      Player who = gson.fromJson(array.get(0), Player.class);
      int fieldSize = servant.getFieldSize(who);                                           
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(fieldSize));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_END_OF_TURN)){
      servant.endTurn();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson("Turn ended"));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_GET_CARD_IN_HAND)){
      Player who = gson.fromJson(array.get(0), Player.class);
      int index = gson.fromJson(array.get(1), Integer.class);
      Card card = servant.getCardInHand(who, index);
      String id = card.getId();
      cardNameService.put(id, card);
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(id));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_GET_CARD_IN_FIELD)){
      Player who = gson.fromJson(array.get(0), Player.class);
      int index = gson.fromJson(array.get(1), Integer.class);
      Card card = servant.getCardInField(who, index);
      String id = card.getId();
      cardNameService.put(id, card);
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(id));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_PLAY_CARD)){
      Player who = gson.fromJson(array.get(0), Player.class);
      String id = gson.fromJson(array.get(1), String.class);
      Card card = lookupCard(id);
      Status status = servant.playCard(who, card);
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_ATTACK_CARD)){
      Player who = gson.fromJson(array.get(0), Player.class);
      String attackingId = gson.fromJson(array.get(1), String.class);
      String defendingId = gson.fromJson(array.get(2), String.class);
      Card attackingCard = lookupCard(attackingId);
      Card defendingCard = lookupCard(defendingId);
      Status status = servant.attackCard(who, attackingCard, defendingCard);
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_ATTACK_HERO)){
      Player who = gson.fromJson(array.get(0), Player.class);
      String cardId = gson.fromJson(array.get(1), String.class);
      Card card = lookupCard(cardId);
      Status status = servant.attackHero(who, card);
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_GET_HERO)){
      Player who = gson.fromJson(array.get(0), Player.class);
      Hero hero = servant.getHero(who);
      String id = hero.getId();
      heroNameService.put(id, hero);
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(id));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_USE_POWER)){
      Player who = gson.fromJson(array.get(0), Player.class);
      Status status = servant.usePower(who);
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_GET_HAND)){
      Player who = gson.fromJson(array.get(0), Player.class);
      Iterable<? extends Card> hand = servant.getHand(who);
      ArrayList<String> idList = new ArrayList<>();
      for(Card c: hand){
        String id = c.getId();
        cardNameService.put(id, c);
        idList.add(id);
      }
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(idList));
    }
    else if(requestObject.getOperationName().equals(OperationNames.GAME_GET_FIELD)){
      Player who = gson.fromJson(array.get(0), Player.class);
      Iterable<? extends Card> field = servant.getField(who);
      ArrayList<String> idList = new ArrayList<>();
      for(Card c: field){
        String id = c.getId();
        cardNameService.put(id, c);
        idList.add(id);
      }
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(idList));
    }
    else if(requestObject.getOperationName().equals(OperationNames.HERO_GET_MANA)){
      Hero hero = lookupHero(objectID);
      int mana = hero.getMana();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(mana));
    }
    else if(requestObject.getOperationName().equals(OperationNames.HERO_GET_HEALTH)){
      Hero hero = lookupHero(objectID);
      int health = hero.getHealth();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(health));
    }
    else if(requestObject.getOperationName().equals(OperationNames.HERO_IS_ACTIVE)){
      Hero hero = lookupHero(objectID);
      boolean isActive = hero.canUsePower();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(isActive));
    }
    else if(requestObject.getOperationName().equals(OperationNames.HERO_GET_TYPE)){
      Hero hero = lookupHero(objectID);
      String type = hero.getType();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(type));
    }
    else if(requestObject.getOperationName().equals(OperationNames.HERO_GET_OWNER)){
      Hero hero = lookupHero(objectID);
      Player owner = hero.getOwner();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(owner));
    }
    else if(requestObject.getOperationName().equals(OperationNames.HERO_GET_DESCRIPTION)){
      Hero hero = lookupHero(objectID);
      String description = hero.getEffectDescription();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(description));
    }
    else if(requestObject.getOperationName().equals(OperationNames.CARD_GET_NAME)){
      Card card = lookupCard(objectID);
      String name = card.getName();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(name));
    }
    else if(requestObject.getOperationName().equals(OperationNames.CARD_GET_MANA_COST)){
      Card card = lookupCard(objectID);
      int manaCost = card.getManaCost();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(manaCost));
    }
    else if(requestObject.getOperationName().equals(OperationNames.CARD_GET_ATTACK)){
      Card card = lookupCard(objectID);
      int attack = card.getAttack();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(attack));
    }
    else if(requestObject.getOperationName().equals(OperationNames.CARD_GET_HEALTH)){
      Card card = lookupCard(objectID);
      int health = card.getHealth();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(health));
    }
    else if(requestObject.getOperationName().equals(OperationNames.CARD_IS_ACTIVE)){
      Card card = lookupCard(objectID);
      boolean isActive = card.isActive();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(isActive));
    }
    else if(requestObject.getOperationName().equals(OperationNames.CARD_GET_OWNER)){
      Card card = lookupCard(objectID);
      Player owner  = card.getOwner();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(owner));
    }
    else if(requestObject.getOperationName().equals(OperationNames.CARD_GET_DESCRIPTION)){
      Card card = lookupCard(objectID);
      String description  = card.getEffectDescription();
      reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(description));
    }
    else {
      // Unknown operation
      reply = new ReplyObject(HttpServletResponse.SC_NOT_IMPLEMENTED,
              "Server received unknown operation name: '"
                      + requestObject.getOperationName() + "'.");
    }

    } catch(Exception e) {
      reply = new ReplyObject(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }

    return gson.toJson(reply);
  }


}

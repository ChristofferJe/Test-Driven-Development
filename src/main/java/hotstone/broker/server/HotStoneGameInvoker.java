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
import hotstone.broker.common.OperationNames;
import hotstone.broker.doubles.StubCardForBroker;
import hotstone.broker.doubles.StubHeroForBroker;
import hotstone.doubles.StubCard;
import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;

import javax.servlet.http.HttpServletResponse;

/** Template code for solving the Broker exercises */
public class HotStoneGameInvoker implements Invoker {
  private final Game servant;
  private final Gson gson;
  private final Hero stubHero;

  public HotStoneGameInvoker(Game servant) {
    this.servant = servant;
    gson = new Gson();
    stubHero = new StubHeroForBroker();
  }

  private Hero lookupHero(String objectID){return stubHero;}

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

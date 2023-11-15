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
import hotstone.framework.Game;
import hotstone.framework.Player;

/** Template code for solving the Broker exercises */
public class HotStoneGameInvoker implements Invoker {
  private final Game servant;
  private final Gson gson;

  public HotStoneGameInvoker(Game servant) {
    this.servant = servant;
    gson = new Gson();
  }

  @Override
  public String handleRequest(String request) {

    RequestObject requestObject = gson.fromJson(request,RequestObject.class);
    JsonArray array = JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

    ReplyObject reply;

    try {
    if(requestObject.getOperationName().equals(OperationNames.GAME_GET_TURN_NUMBER)){
      int turnNumber = servant.getTurnNumber();
      reply = new ReplyObject(200, Integer.toString(turnNumber));
      return gson.toJson(reply);
    }
    if(requestObject.getOperationName().equals(OperationNames.GAME_GET_WINNER)){
        Player winner = servant.getWinner();
        reply = new ReplyObject(200, winner.toString());
        return gson.toJson(reply);
      }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

      return null;
  }

}

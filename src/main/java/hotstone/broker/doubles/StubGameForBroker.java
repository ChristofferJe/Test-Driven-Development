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

package hotstone.broker.doubles;

import frds.broker.Servant;
import hotstone.framework.*;
import hotstone.observer.GameObserver;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;
import hotstone.variants.BabyPowerStrategy;
import hotstone.variants.DanishChefPower;
import hotstone.variants.DoNothingEffectStrategy;

/** A Test Stub for game, to make easily recognizable output
 * to assert on in the Broker test cases. Some methods have
 * already been defined.
 */
public class StubGameForBroker implements Game, Servant {
  @Override
  public int getTurnNumber() {
    return 312;
  }
  @Override
  public Player getPlayerInTurn() {
    return Player.FINDUS;
  }
  @Override
  public Player getWinner() {
    return Player.PEDDERSEN;
  }

  @Override
  public Hero getHero(Player who) {return null;}

  @Override
  public int getDeckSize(Player who) {
    if(who == Player.FINDUS){
      return 101;
    }
    if(who == Player.PEDDERSEN){
      return 102;
    }
    return 0;
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    return new StubCardForBroker();
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {return null;}

  @Override
  public int getHandSize(Player who) {
    if(who == Player.FINDUS){
      return 201;
    }
    if(who == Player.PEDDERSEN){
      return 202;
    }
    return 0;
  }

  @Override
  public Card getCardInField(Player who, int indexInField) {
    return null;
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    return null;
  }

  @Override
  public int getFieldSize(Player who) {
    if(who == Player.FINDUS){
      return 301;
    }
    if(who == Player.PEDDERSEN){
      return 302;
    }
    return 0;
  }

  @Override
  public void endTurn() {
  }

  @Override
  public Status playCard(Player who, Card card) {
    return null;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    return null;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    return null;
  }

  @Override
  public Status usePower(Player who) {
    return null;
  }

  @Override
  public void addObserver(GameObserver observer) {
  }
}

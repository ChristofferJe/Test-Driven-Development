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

/**
 * Skeleton class for AlphaStone test cases
 *
 *    This source code is from the book
 *      "Flexible, Reliable Software:
 *        Using Patterns and Agile Development"
 *      2nd Edition
 *    Author:
 *      Henrik Bærbak Christensen
 *      Department of Computer Science
 *      Aarhus University
 */

import hotstone.framework.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/** Template for your own ongoing TDD process.
 * Fill it out until you have covered all
 * requirements for the alpha stone game.
 */
public class TestAlphaStone {
  private Game game;


  /** Fixture for AlphaStone testing. */
  @BeforeEach
  public void setUp() {
    game = new StandardHotStoneGame();
  }

  // Example of an early, simple test case:
  // Turn handling
  @Test
  public void shouldHaveFindusAsFirstPlayer() {
    // Given a game
    // When I ask for the player in turn
    Player player = game.getPlayerInTurn();
    // Then it should be Findus
    assertThat(player, is(Player.FINDUS));
  }

  @Test
  public void shouldHaveTurn2WHenFirstTurnEnds(){
    //Given a game
    //When Findus ends its turn
    game.endTurn();
    //Then the turn number is 2
    int nr = game.getTurnNumber();
    assertThat(nr, is(2));
  }

  @Test
  public void shouldHaveTurn3WHenTurn2Ends(){
    //Given a game
    //When Findus ends its turn
    game.endTurn();
    game.endTurn();
    //Then the turn number is 3
    int nr = game.getTurnNumber();
    assertThat(nr, is(3));
  }
  @Test
   public void shouldHavePedersenInTurnWhenFindusEndsTurn() {
    //Given a game
    //Given Findus is in turn
    Player player = game.getPlayerInTurn();
    assertThat(player, is(Player.FINDUS));
    //When Findus ends turn
    game.endTurn();
    //Then Pedersen should be in turn
    player = game.getPlayerInTurn();
    assertThat(player, is(Player.PEDDERSEN));

  }

  @Test
  public void FindusShouldHaveHeroBaby(){
    // Given a game
    // Then Findus should have hero Babys
    Hero hero = game.getHero(Player.FINDUS);
    String herotype = hero.getType();
    assertThat(herotype, is("Baby"));
  }

  @Test
  public void PeddersenShouldHaveHeroBaby(){
    // Given a game
    // Then Findus should have hero Babys
    Hero hero = game.getHero(Player.PEDDERSEN);
    String herotype = hero.getType();
    assertThat(herotype, is("Baby"));
  }

  @Test
  public void FindusHeroShouldHave21Health(){
    // Given a game
    // Findus has a hero
    Hero hero = game.getHero(Player.FINDUS);
    // The Hero should have 21 health
    int health = hero.getHealth();
    assertThat(health, is(21));
  }

  @Test
  public void FindusCanUseHeroPowerAtStartOfFirstTurn(){
    // Given a game
    // Findus has a hero
    Hero hero = game.getHero(Player.FINDUS);
    // Then Findus can use its hero power
    assertThat(hero.canUsePower(), is(true));
  }

  @Test
  public void FindusCanOnlyUseHeroPowerOncePerTurn(){
    // Given a game
    // Findus has a hero
    Hero hero = game.getHero(Player.FINDUS);
    // When Findus uses its hero power
    game.usePower(Player.FINDUS);
    // Then Findus cannot use its hero power again in same turn
    assertThat(hero.canUsePower(), is(false));

  }

  @Test
  public void FindusCanUseHeroPowerAtNewTurnAfterUsingHeroPowerAtLastTurn(){
    // Given a game
    // Findus has a hero
    Hero hero = game.getHero(Player.FINDUS);
    // When Findus uses its hero power
    game.usePower(Player.FINDUS);
    // And ends turn
    game.endTurn();
    //And it is Findus turn again
    game.endTurn();
    assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
    // Then Findus can use hero power again
    assertThat(hero.canUsePower(), is(true));
  }

  @Test
  public void manaShouldBeReducedByTwoWhenUsingHeroPower(){
    //Given a game, Findus has a hero with an amount of mana
    Hero hero = game.getHero(Player.FINDUS);
    int manabefore = hero.getMana();
    // When Findus uses its hero power
    assertThat(hero.canUsePower(), is(true));
    game.usePower(Player.FINDUS);
    // Then the hero has two mana less
    int manaafter = hero.getMana();
    assertThat(manaafter, is(manabefore - 2));
  }

  @Test
  public void shouldHave3ManaWhenGameStarts(){
    // Given a game
    // Findus has a hero
    Hero hero = game.getHero(Player.FINDUS);
    // Then the hero has three mana
    assertThat(hero.getMana(), is(3));
  }

  @Test
  public void shouldHave3ManaWhenTurnStarts(){
    // Given a game, Findus has a hero with mana
    Hero hero = game.getHero(Player.FINDUS);
    // When Findus uses mana and a new turn starts
    game.usePower(Player.FINDUS);
    game.endTurn();
    game.endTurn();
    // Then Findus' hero should have 3 mana
    assertThat(hero.getMana(), is(3));
  }


  @Test
  public void shouldHave4CardsInDeckWhenGameStarts(){
    //Given a game
    // Then Findus deck size should be 4
    assertThat(game.getDeckSize(Player.FINDUS), is(4));
  }

  // Example of a later, more complex, test case:
  // Card handling

  // The HotStone specs are quite insisting on how
  // the cards, drawn from the deck, are organized
  // in the hand. So when drawing the top three cards
  // from the deck (uno, dos, tres) they have to
  // be organized in the hand as
  // index 0 = tres; index 1 = dos; index 2 = uno
  // That is, a newly drawn card is 'at the top'
  // of the hand - always entered at position 0
  // and pushing the rest of the cards 1 position
  // 'down'
  @Test
  public void shouldHaveUnoDosTresCardsInitially() {
    // Given a game, Findus has 3 cards in hand
    int count = game.getHandSize(Player.FINDUS);
    assertThat(count, is(3));
    // And these are ordered Tres, Dos, Uno in slot 0,1,2

    // When I pick card 0
    Card card = game.getCardInHand(Player.FINDUS, 0);
    // Then is it Tres
    assertThat(card.getName(), is(GameConstants.TRES_CARD));

    // When I pick card 1
    card = game.getCardInHand(Player.FINDUS, 1);
    // Then is it Dos
    assertThat(card.getName(), is(GameConstants.DOS_CARD));

    // When I pick card 2
    card = game.getCardInHand(Player.FINDUS, 2);
    // Then is it Uno
    assertThat(card.getName(), is(GameConstants.UNO_CARD));

  }

  @Test
  public void shouldDrawCardFromDeckWhenTurnStarts(){
    // Given a game, Findus has 3 cards in hand
    int count = game.getHandSize(Player.FINDUS);
    assertThat(count, is(3));
    // When new turn for Findus starts
    game.endTurn();
    game.endTurn();
    // Then Findus draws a new card
    count = game.getHandSize(Player.FINDUS);
    assertThat(count, is(4));
  }

  @Test
  public void drawingACardShouldDecreasedecksizeByOne(){
    //Given a game, Findus has 4 cards in deck
    int count =  game.getDeckSize(Player.FINDUS);
    assertThat(count, is(4));
    // When new turn for Findus starts
    game.endTurn();
    game.endTurn();
    // Then Findus draws a card from the deck
    count = game.getDeckSize(Player.FINDUS);
    assertThat(count, is(3));
  }
  @Test
  public void unoShouldBeInactiveInStartOfGame(){
    // Given a game, Findus has card uno at index 2
    Card card = game.getCardInHand(Player.FINDUS, 2);
    assertThat(card.getName(), is(GameConstants.UNO_CARD));
    // Then card uno should be inactive
    assertThat(card.isActive(), is(false));
  }

  @Test
  public void shouldDrawCuatro(){
    // Given a game, the first time Findus draws a card
    game.endTurn();
    game.endTurn();
    // Then the card he drew should be cuatro and it should be placed at index 0 in its hand
    Card card = game.getCardInHand(Player.FINDUS, 0);
    assertThat(card.getName(), is(GameConstants.CUATRO_CARD));
  }

  @Test
  public void dosShouldHaveTwoTwoTwo(){
    // Given a game, then Findus has card Dos at index 1 in its hand
    Card dos = game.getCardInHand(Player.FINDUS, 1);
    assertThat(dos.getName(), is(GameConstants.DOS_CARD));
    // Then Dos has the attributes (2,2,2)
    int cost = dos.getManaCost();
    assertThat(cost, is(2));
    int attack = dos.getAttack();
    assertThat(attack, is(2));
    int health = dos.getHealth();
    assertThat(health, is(2));
  }

  @Test
  public void cincoShouldHaveThreeFiveOne() {
    // Given a game after 4 turns, then Findus has card Cinco at index 0 in its hand
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    Card cinco = game.getCardInHand(Player.FINDUS, 0);
    assertThat(cinco.getName(), is(GameConstants.CINCO_CARD));
    // Then Cinco has the attributes (3,5,1)
    int cost = cinco.getManaCost();
    assertThat(cost, is(3));
    int attack = cinco.getAttack();
    assertThat(attack, is(5));
    int health = cinco.getHealth();
    assertThat(health, is(1));
  }

  @Test
  public void findusShouldWinAfter4rounds(){
    // Given a game where 4 rounds have passed
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    // Then Findus is the winner
    Player winner = game.getWinner();
    assertThat(winner, is(Player.FINDUS));
  }

  @Test
  public void UnoInFindusFieldShouldBeInIndex0(){
    // Given game
    // When Findus plays Uno in first round
    assertThat(game.getCardInHand(Player.FINDUS, 2).getName(), is(GameConstants.UNO_CARD));
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
    // Then uno should be in field index 0
    assertThat(game.getCardInField(Player.FINDUS, 0).getName(), is(GameConstants.UNO_CARD));
  }

  @Test
  public void UnoInFindusFieldShouldBeActiveAtTheStartOfTurn(){
    // Given game
    // When Findus plays Uno in first round
    assertThat(game.getCardInHand(Player.FINDUS, 2).getName(), is(GameConstants.UNO_CARD));
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
    // and uno is at index 0 in field
    assertThat(game.getCardInField(Player.FINDUS, 0).getName(), is(GameConstants.UNO_CARD));
    // and its Findus turn again
    game.endTurn();
    game.endTurn();
    // Then uno should be active
    assertThat(game.getCardInField(Player.FINDUS, 0).isActive(), is(true));
}

  @Test
  public void findusManaShouldBeOneLessAfterPlayingUno(){
    // Given a game, Findus have 3 mana
    int manabefore = game.getHero(Player.FINDUS).getMana();
    assertThat(manabefore, is(3));
    // When Findus play card uno
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
    // Then Findus should have one less mana
    int manaafter = game.getHero(Player.FINDUS).getMana();
    assertThat(manaafter, is(manabefore - 1));

  }



  @Test
  public void findusManaShouldBeTwoLessAfterPlayingDos(){
    // Given a game, Findus have 3 mana
    int manabefore = game.getHero(Player.FINDUS).getMana();
    assertThat(manabefore, is(3));
    // When Findus play card dos
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 1));
    // Then Findus should have two less mana
    int manaafter = game.getHero(Player.FINDUS).getMana();
    assertThat(manaafter, is(manabefore - 2));

  }


  @Test
  public void FindusCannotPlayTresIfNotEnoughMana(){
    // Given a game
    // Findus plays card uno
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
    // Then Findus mana is 2
    assertThat(game.getHero(Player.FINDUS).getMana(),is(2));
    // Then Findus should not be allowed to play card tres:
    Status status = game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0));
    assertThat(status, is(Status.NOT_ENOUGH_MANA));
  }

  @Test
  public void FindusCanPlayTresIfEnoughMana(){
    // Given a game
    // When Findus plays card tres
    Status status = game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0));
    // Then it is allowed:
    assertThat(status, is(Status.OK));
  }

  @Test
  public void CannotUseHeroPowerIfNotEnoughMana(){
    // Given a game
    // When Findus plays dos and uses 2 mana
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 1));
    // Then Findus should not be allowed to use hero power
    Status status = game.usePower(Player.FINDUS);
    assertThat(status, is(Status.NOT_ENOUGH_MANA));
  }

  @Test
  public void CanUseHeroPowerIfEnoughMana(){
    // Given a game
    // Then Findus should be allowed to use hero power
    Status status = game.usePower(Player.FINDUS);
    assertThat(status, is(Status.OK));
  }

  /** REMOVE ME. Not a test of HotStone, just an example of the
   matchers that the hamcrest library has... */

  public void shouldDefinitelyBeRemoved() {
    // Matching null and not null values
    // 'is' require an exact match
    // Hamcrest uses the 'equals()' method
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is(notNullValue()));
    assertThat(s, is("Ok"));

    // If you only validate substrings, use containsString
    assertThat("This is a dummy test", containsString("dummy"));

    // You can use is on any type
    int answerToLifeUniverseAndEverything = 42;
    assertThat(answerToLifeUniverseAndEverything, is(42));

    // Match contents of Lists
    List<String> l = new ArrayList<String>();
    l.add("Bimse");
    l.add("Bumse");
    // Note - ordering is ignored when matching using hasItems
    assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

    // Matchers may be combined, like is-not
    assertThat(l.get(0), is(not("Bumse")));
  }

}

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
    Status status = game.usePower(Player.FINDUS);
    assertThat(status, is(Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND));

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

  @Test
  public void FindusLosesTwoHealthWhenDrawWithEmptyDeck(){
    // When 4 rounds have passed
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    // Then Findus's deck is empty
    int deckSize = game.getDeckSize(Player.FINDUS);
    assertThat(deckSize, is(0));
    // When another round passes and Findus draws another card
    int healthBefore = game.getHero(Player.FINDUS).getHealth();
    game.endTurn();
    game.endTurn();
    // Then Findus loses two health
    int healthAfter = game.getHero(Player.FINDUS).getHealth();
    assertThat(healthBefore - healthAfter, is(2));

  }

  @Test
  public void FieldSizeShouldIncreaseByOne(){
    // Given a game, Findus have a field size of zero
    int fieldSize = game.getFieldSize(Player.FINDUS);
    assertThat(fieldSize, is(0));
    // When Findus play card Tres
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0));
    // Then Findus's field size is one
    fieldSize = game.getFieldSize(Player.FINDUS);
    assertThat(fieldSize, is(1));
  }

  @Test
  public void UnoOwnedByFindusCanAttackDosOwnedByPeddersenWhenActive(){
    // Given a game, Findus plays Uno
    Card uno = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, uno);
    // When Findus ends its turn and Peddersen plays dos
    game.endTurn();
    Card dos = game.getCardInHand(Player.PEDDERSEN, 1);
    game.playCard(Player.PEDDERSEN, dos);
    // Then Findus should be able to attack dos with uno in his turn
    game.endTurn();
    assertThat(uno.isActive(), is(true));
    Status status = game.attackCard(Player.FINDUS, uno, dos);
    assertThat(status, is(Status.OK));
  }

  @Test
  public void DosOwnedByPeddersenCannotAttackWhenInactive(){
    // Given a game, Findus plays Uno
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
    // When Findus ends its turn and Peddersen plays dos
    game.endTurn();
    game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN, 1));
    // Then Peddersen isn't allowed to attack with the inactive dos card
    Status status = game.attackCard(Player.PEDDERSEN, game.getCardInField(Player.PEDDERSEN,0), game.getCardInField(Player.FINDUS,0));
    assertThat(status, is(Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION));
  }

  @Test
  public void UnoBecomesInactiveAfterAttack(){
    // Given a game, Findus plays Uno
    Card uno = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, uno);
    // When Findus ends its turn and Peddersen plays dos
    game.endTurn();
    Card dos = game.getCardInHand(Player.PEDDERSEN, 2);
    game.playCard(Player.PEDDERSEN, dos);
    // When Findus attacks dos with uno in his turn
    game.endTurn();
    Status status = game.attackCard(Player.FINDUS, uno, dos);
    assertThat(status, is(Status.OK));
    // Then uno becomes inactive
    assertThat(uno.isActive(), is(false));
  }

  @Test
  public void UnoOwnedByFindusCanAttackPeddersenHero(){
    // Given a game, Findus plays Uno
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
    // When it is Findus' turn again
    game.endTurn();
    game.endTurn();
    //Then Uno can attack Peddersen's hero
    Status status = game.attackHero(Player.FINDUS, game.getCardInField(Player.FINDUS, 0));
    assertThat(status, is(Status.OK));
  }

  @Test
  public void UnoOwnedByFindusCannotAttackPeddersenHeroWhenInactive(){
    // Given a game, Findus plays Uno
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
    // When Uno is inactive
    assertThat(game.getCardInField(Player.FINDUS,0).isActive(), is(false));
    // Then Uno cannot attack Peddersen's hero
    Status status = game.attackHero(Player.FINDUS, game.getCardInField(Player.FINDUS, 0));
    assertThat(status, is(Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION));
  }

  /** REMOVE ME. Not a test of HotStone, just an example of the
   matchers that the hamcrest library has... */

  @Test
  public void PeddersenHeroShouldHave20HealtAfterAttackFromUno(){
    // Given a game, Findus plays Uno
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
    // When it is Findus' turn again and Uno attacks Peddersen's hero
    game.endTurn();
    game.endTurn();
    game.attackHero(Player.FINDUS, game.getCardInField(Player.FINDUS, 0));
    // Then Peddersen's hero should have 20 health
    assertThat(game.getHero(Player.PEDDERSEN).getHealth(), is(20));
  }

  @Test
  public void PeddersenHeroShouldHave19HealtAfterAttackFromDos(){
    // Given a game, Findus plays Dos
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 1));
    // When it is Findus' turn again and Uno attacks Peddersen's hero
    game.endTurn();
    game.endTurn();
    game.attackHero(Player.FINDUS, game.getCardInField(Player.FINDUS, 0));
    // Then Peddersen's hero should have 19 health
    assertThat(game.getHero(Player.PEDDERSEN).getHealth(), is(19));
  }

  @Test
  public void FindusHeroShouldHave19HealtAfterAttackFromDos(){
    // Given a game, Peddersen plays Dos at index 2 after drawing a card
    game.endTurn();
    Card card = game.getCardInHand(Player.PEDDERSEN, 2);
    assertThat(card.getName(), is(GameConstants.DOS_CARD));
    game.playCard(Player.PEDDERSEN, card);
    // When it is Peddersen's turn again and Uno attacks Findus' hero
    game.endTurn();
    game.endTurn();
    game.attackHero(Player.PEDDERSEN, game.getCardInField(Player.PEDDERSEN, 0));
    // Then Findus' hero should have 19 health
    assertThat(game.getHero(Player.FINDUS).getHealth(), is(19));
  }

  @Test
  public void WhenLessThanFourRoundsNoWinner(){
    // Given a game, no winner should be declared
    Player winner = game.getWinner();
    assertThat(winner, is(nullValue()));
    // When 7 turns have passed
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    // Then no winner should have been declared
    winner = game.getWinner();
    assertThat(winner, is(nullValue()));
  }

  @Test
  public void UnoShouldHaveMinusOneHealthAfterAttackingDos(){
    // Given a game, Findus plays Uno
    Card uno = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, uno);
    // When Findus ends its turn and Peddersen plays dos
    game.endTurn();
    Card dos = game.getCardInHand(Player.PEDDERSEN, 2);
    game.playCard(Player.PEDDERSEN, dos);
    // When Findus attacks dos with uno in his turn
    game.endTurn();
    game.attackCard(Player.FINDUS, uno, dos);
    // Then uno should have -1 health
    int health = uno.getHealth();
    assertThat(health, is(-1));
  }

  @Test
  public void DosShouldHaveOneHealthAfterAttackFromUno(){
    // Given a game, Findus plays Uno
    Card uno = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, uno);
    // When Findus ends its turn and Peddersen plays dos
    game.endTurn();
    Card dos = game.getCardInHand(Player.PEDDERSEN, 2);
    game.playCard(Player.PEDDERSEN, dos);
    // When Findus attacks dos with uno in his turn
    game.endTurn();
    game.attackCard(Player.FINDUS, uno, dos);
    // Then uno should have -1 health
    int health = dos.getHealth();
    assertThat(health, is(1));
  }

  @Test
  public void DosShouldHaveOneHealthAfterAttackingUno(){
    // Given a game, Findus plays Dos
    Card dos = game.getCardInHand(Player.FINDUS, 1);
    game.playCard(Player.FINDUS, dos);
    // When Findus ends its turn and Peddersen plays Uno
    game.endTurn();
    Card uno = game.getCardInHand(Player.PEDDERSEN, 3);
    game.playCard(Player.PEDDERSEN, uno);
    // When Findus attacks uno with dos in his turn
    game.endTurn();
    game.attackCard(Player.FINDUS, dos, uno);
    // Then dos should have 1 health
    int health = dos.getHealth();
    assertThat(health, is(1));
  }

  @Test
  public void UnoShouldHaveMinusOneHealthAfterAttackFromDos(){
    // Given a game, Findus plays Dos
    Card dos = game.getCardInHand(Player.FINDUS, 1);
    game.playCard(Player.FINDUS, dos);
    // When Findus ends its turn and Peddersen plays Uno
    game.endTurn();
    Card uno = game.getCardInHand(Player.PEDDERSEN, 3);
    game.playCard(Player.PEDDERSEN, uno);
    // When Findus attacks uno with dos in his turn
    game.endTurn();
    game.attackCard(Player.FINDUS, dos, uno);
    // Then Uno should have 1 health
    int health = uno.getHealth();
    assertThat(health, is(-1));
  }

  @Test
  public void UnoShouldBeInactiveWhenHealthIsBelowOne(){
    // Given a game, Findus plays Dos
    Card dos = game.getCardInHand(Player.FINDUS, 1);
    game.playCard(Player.FINDUS, dos);
    // When Findus ends its turn and Peddersen plays Uno
    game.endTurn();
    Card uno = game.getCardInHand(Player.PEDDERSEN, 3);
    game.playCard(Player.PEDDERSEN, uno);
    // When a round passes and uno is active
    game.endTurn();
    game.endTurn();
    assertThat(uno.isActive(),is(true));
    // When Findus attacks uno with dos in his turn
    game.endTurn();
    game.attackCard(Player.FINDUS, dos, uno);
    // Then uno is inactive
    assertThat(uno.isActive(),is(false));
  }

  @Test
  public void UnoShouldBeRemovedFromFieldWhenAttackedAndHealthIsBelowOne(){
    // Given a game, Findus plays Dos
    Card dos = game.getCardInHand(Player.FINDUS, 1);
    game.playCard(Player.FINDUS, dos);
    // When Findus ends its turn and Peddersen plays Uno
    game.endTurn();
    Card uno = game.getCardInHand(Player.PEDDERSEN, 3);
    game.playCard(Player.PEDDERSEN, uno);
    // When Findus attacks uno with dos in his turn
    game.endTurn();
    game.attackCard(Player.FINDUS, dos, uno);
    // Then uno is removed from field
    ArrayList<Card> field = (ArrayList) game.getField(Player.PEDDERSEN);
    boolean isUnoInField = field.contains(uno);
    assertThat(isUnoInField,is(false));
  }

  @Test
  public void UnoShouldBeRemovedFromFieldWhenAttackingAndHealthIsBelowOne(){
    // Given a game, Findus plays Uno
    Card uno = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, uno);
    // When Findus ends its turn and Peddersen plays dos
    game.endTurn();
    Card dos = game.getCardInHand(Player.PEDDERSEN, 2);
    game.playCard(Player.PEDDERSEN, dos);
    // When Findus attacks dos with uno in his turn
    game.endTurn();
    game.attackCard(Player.FINDUS, uno, dos);
    // Then uno is removed from field
    ArrayList<Card> field = (ArrayList) game.getField(Player.FINDUS);
    boolean isUnoInField = field.contains(uno);
    assertThat(isUnoInField,is(false));
  }
  @Test
  public void PeddersenCannotAttackHeroWithUnoInFieldOwnedByFindus(){
    // Given a game, Findus plays Uno
    Card uno = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, uno);
    // When Findus ends its turn and Peddersen tries to attack Findus hero with Uno
    game.endTurn();
    Status status = game.attackHero(Player.PEDDERSEN, uno);
    // Then it should not be allowed
    assertThat(status, is(Status.NOT_OWNER));
  }
  @Test
  public void FindusCannotAttackHeroWithUnoInFieldOwnedByPeddersen(){
    // Given a game
    // When Findus ends its turn Peddersen plays uno and end his turn
    game.endTurn();
    Card uno = game.getCardInHand(Player.PEDDERSEN, 3);
    game.playCard(Player.PEDDERSEN, uno);
    game.endTurn();
    // When Findus tries to attack Peddersen with uno
    Status status = game.attackHero(Player.FINDUS, uno);
    // Then it should not be allowed
    assertThat(status, is(Status.NOT_OWNER));
  }

  @Test
  public void PeddersenCannotAttackUnoWithDosBothOwnedByFindus(){
    // Given a game where Findus plays uno and dos
    Card uno = game.getCardInHand(Player.FINDUS, 2);
    Card dos = game.getCardInHand(Player.FINDUS, 1);
    game.playCard(Player.FINDUS, uno);
    game.playCard(Player.FINDUS, dos);
    game.endTurn();
    // When Peddersen tries to attack uno with dos
    Status status = game.attackCard(Player.PEDDERSEN, dos, uno);
    // Then this is not allowed
    assertThat(status, is(Status.NOT_OWNER));
  }

  @Test
  public void PeddersenShoulNotBeAllowedToAttackHeroWhenNotHisTurn(){
    // Given a game
    game.endTurn();
    // When Peddersen plays card uno
    Card uno = game.getCardInHand(Player.PEDDERSEN, 3);
    game.playCard(Player.PEDDERSEN, uno);
    game.endTurn();
    // When Peddersen tries to attack a hero with uno and it isn't his turn
    Status status = game.attackHero(Player.PEDDERSEN, uno);
    // Then this shouldn't be allowed
    assertThat(status, is(Status.NOT_PLAYER_IN_TURN));
  }

  @Test
  public void FindusShoulNotBeAllowedToAttackHeroWhenNotHisTurn(){
    // Given a game
    // When Findus plays card dos
    Card dos = game.getCardInHand(Player.FINDUS, 1);
    game.playCard(Player.FINDUS, dos);
    game.endTurn();
    // When Findus tries to attack a hero with dos and it isn't his turn
    Status status = game.attackHero(Player.FINDUS, dos);
    // Then this shouldn't be allowed
    assertThat(status, is(Status.NOT_PLAYER_IN_TURN));
  }

  @Test
  public void FindusShoulNotBeAllowedToAttackMinionWhenNotItsTurn(){
    // Given a game
    // When Findus plays card uno
    Card uno = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, uno);
    game.endTurn();
    // When Peddersen plays card dos
    Card dos = game.getCardInHand(Player.PEDDERSEN, 2);
    game.playCard(Player.PEDDERSEN, dos);
    // When Findus tries to attack dos with uno and it isn't its turn
    Status status = game.attackCard(Player.FINDUS, uno, dos);
    // Then it is not allowed
    assertThat(status, is(Status.NOT_PLAYER_IN_TURN));
  }
  @Test
  public void PeddersenShoulNotBeAllowedToAttackMinionWhenNotHisTurn(){
    // Given a game
    // When Findus plays card uno
    Card uno = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, uno);
    game.endTurn();
    // When Peddersen plays card dos
    Card dos = game.getCardInHand(Player.PEDDERSEN, 2);
    game.playCard(Player.PEDDERSEN, dos);
    game.endTurn();
    // When Peddersen tries to attack uno with dos and it isn't his turn
    Status status = game.attackCard(Player.PEDDERSEN, dos, uno);
    // Then it is not allowed
    assertThat(status, is(Status.NOT_PLAYER_IN_TURN));
  }
  @Test
  public void FindusShouldNotBeAllowedToAttackOwnMinion(){
    // Given a game where Findus plays uno and dos
    Card uno = game.getCardInHand(Player.FINDUS, 2);
    Card dos = game.getCardInHand(Player.FINDUS, 1);
    game.playCard(Player.FINDUS, uno);
    game.playCard(Player.FINDUS, dos);
    game.endTurn();
    game.endTurn();
    // When Findus tries to attack dos with uno
    Status status = game.attackCard(Player.FINDUS, uno, dos);
    // Then this shouldn't be allowed
    assertThat(status, is(Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION));
  }

  @Test
  public void PeddersenShouldNotBeAllowedToAttackOwnMinion(){
    // Given a game where Peddersen plays uno and dos
    game.endTurn();
    Card uno = game.getCardInHand(Player.PEDDERSEN, 3);
    Card dos = game.getCardInHand(Player.PEDDERSEN, 2);
    game.playCard(Player.PEDDERSEN, uno);
    game.playCard(Player.PEDDERSEN, dos);
    game.endTurn();
    game.endTurn();
    // When Peddersen tries to attack uno with dos
    Status status = game.attackCard(Player.PEDDERSEN, dos, uno);
    // Then this shouldn't be allowed
    assertThat(status, is(Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION));
  }

  @Test
  public void PeddersenShouldNotBeAllowedToPlayCardWhenNotHisTurn(){
    // Given game
    // When Peddersen tries to play card tres and it is not his turn
    Card tres = game.getCardInHand(Player.PEDDERSEN,0);
    Status status = game.playCard(Player.PEDDERSEN, tres);
    // THen it is not allowed
    assertThat(status, is(Status.NOT_PLAYER_IN_TURN));
  }
  @Test
  public void FindusShouldNotBeAllowedToPlayCardWhenNotHisTurn(){
    // Given game when it is Pedersen turn
    game.endTurn();
    // When Findus tries to play card dos and it is not its turn
    Card dos = game.getCardInHand(Player.FINDUS,1);
    Status status = game.playCard(Player.FINDUS, dos);
    // Then it is not allowed
    assertThat(status, is(Status.NOT_PLAYER_IN_TURN));
  }

  @Test
  public void PeddersenShouldNotBeAllowedToUsePowerWhenNotHisTurn(){
    // Given game
    // When Peddersen tries to use power
    Status status = game.usePower(Player.PEDDERSEN);
    // Then it is not allowed
    assertThat(status, is(Status.NOT_PLAYER_IN_TURN));
  }

  @Test
  public void FindusShouldNotBeAllowedToUsePowerWhenNotItsTurn(){
    // Given game and it is Peddersen's turn
    game.endTurn();
    // When Findus tries to use power
    Status status = game.usePower(Player.FINDUS);
    // Then it is not allowed
    assertThat(status, is(Status.NOT_PLAYER_IN_TURN));
  }
}

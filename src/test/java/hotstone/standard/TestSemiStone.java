package hotstone.standard;

import hotstone.framework.*;
import hotstone.utility.TestHelper;
import hotstone.variants.SemiStoneTestGameFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSemiStone {
    private Game game;
    private GameFactory factory;

    /**
     * Fixture for Zeta Stone testing.
     */
    @BeforeEach
    public void setUp(){
        factory = new SemiStoneTestGameFactory();
    }

    @Test
    public void FindusShouldHaveThaiHeroPower() {
        factory.getNumberStrategy().setNumber(2);
        game = new StandardHotStoneGame(factory);
        // Given game, Peddersens has hero with health 21
        Hero hero = game.getHero(Player.PEDDERSEN);
        int healthBefore = hero.getHealth();
        assertThat(healthBefore, is(21));
        // When one round have passed Findus uses hero power
        game.endTurn();
        game.endTurn();
        game.usePower(Player.FINDUS);
        // Then Peddersen's hero should have 19 health
        int healthAfter = hero.getHealth();
        assertThat(healthAfter, is(19));
    }
    @Test
    public void PeddersenShouldHaveThaiHeroPower() {
        factory.getNumberStrategy().setNumber(2);
        game = new StandardHotStoneGame(factory);
        // Given game, Findus has hero with health 21
        Hero hero = game.getHero(Player.FINDUS);
        int healthBefore = hero.getHealth();
        assertThat(healthBefore, is(21));
        // When 3 turns have passed Peddersen uses hero power
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.usePower(Player.PEDDERSEN);
        // Then Findus's hero should have 19 health
        int healthAfter = hero.getHealth();
        assertThat(healthAfter, is(19));
    }

    @Test
    public void PeddersenShouldHaveDanishHeroPower(){
        factory.getNumberStrategy().setNumber(3);
        game = new StandardHotStoneGame(factory);
        // Given game, when 3 turns have passed and it is Peddersens turn
        game.endTurn();
        game.endTurn();
        game.endTurn();
        // When Peddersen uses hero power
        game.usePower(Player.PEDDERSEN);
        // Then minion "Sovs" appears on Peddersen's field at index 0
        Card Sovs = game.getCardInField(Player.PEDDERSEN, 0);
        assertThat(Sovs.getName(), is(GameConstants.SOVS_CARD));
    }
    @Test
    public void FindusShouldHaveDanishHeroPower(){
        factory.getNumberStrategy().setNumber(3);
        game = new StandardHotStoneGame(factory);
        // Given game, when 2 turns have passed and it is Findus turn
        game.endTurn();
        game.endTurn();
        // When Findus uses hero power
        game.usePower(Player.FINDUS);
        // Then minion "Sovs" appears on Peddersen's field at index 0
        Card Sovs = game.getCardInField(Player.FINDUS, 0);
        assertThat(Sovs.getName(), is(GameConstants.SOVS_CARD));
    }

    @Test
    public void FindusShouldHaveFrenchHeroPower(){
        factory.getNumberStrategy().setNumber(0);
        game = new StandardHotStoneGame(factory);
        // Given game and it is Peddersens turn
        game.endTurn();
        // Peddersen plays card at index 3
        Card card = game.getCardInHand(Player.PEDDERSEN, 3);
        game.playCard(Player.PEDDERSEN, card);
        // Card has health before
        int healthBefore = card.getHealth();
        // When it is Findus turn and
        // Findus uses hero power and the random number is set to 0
        game.endTurn();
        factory.getNumberStrategy().setNumber(0);
        game.usePower(Player.FINDUS);
        // Then card has 2 health less
        int healthAfter =  card.getHealth();
        assertThat(healthAfter, is(healthBefore - 2));
    }

    @Test
    public void PeddersenShouldHaveFrenchHeroPower(){
        factory.getNumberStrategy().setNumber(0);
        game = new StandardHotStoneGame(factory);
        // Given game Findus plays card at index 2
        Card card = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, card);
        // Card has health before
        int healthBefore = card.getHealth();
        // When 3 turns have passed
        game.endTurn();
        game.endTurn();
        game.endTurn();
        // Peddersen uses hero power and the random number is set to 0
        factory.getNumberStrategy().setNumber(0);
        game.usePower(Player.PEDDERSEN);
        // Then card has 2 health less
        int healthAfter =  card.getHealth();
        assertThat(healthAfter, is(healthBefore - 2));
    }
    @Test
    public void PeddersenShouldHaveItalianHeroPower(){
        factory.getNumberStrategy().setNumber(1);
        game = new StandardHotStoneGame(factory);
        // Given game and it is Peddersens turn
        game.endTurn();
        // Peddersen plays the card at index 3
        Card card = game.getCardInHand(Player.PEDDERSEN, 3);
        game.playCard(Player.PEDDERSEN, card);
        // Card has attack before
        int attackBefore = card.getAttack();
        // When 1 round have passed
        game.endTurn();
        game.endTurn();
        // Peddersen uses hero power and the random number is set to 0
        factory.getNumberStrategy().setNumber(0);
        game.usePower(Player.PEDDERSEN);
        // Card has two attacks more
        int attackAfter =  card.getAttack();
        assertThat(attackAfter, is(attackBefore + 2));
    }
    @Test
    public void FindusShouldHaveItalianHeroPower(){
        factory.getNumberStrategy().setNumber(1);
        game = new StandardHotStoneGame(factory);
        // Given game, Findus plays card at index 2
        Card card = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, card);
        // Card has attack before
        int attackBefore = card.getAttack();
        // When 1 round have passed
        game.endTurn();
        game.endTurn();
        // Findus uses hero power and the random number is set to 0
        factory.getNumberStrategy().setNumber(0);
        game.usePower(Player.FINDUS);
        // Card has two attacks more
        int attackAfter =  card.getAttack();
        assertThat(attackAfter, is(attackBefore + 2));
    }

}

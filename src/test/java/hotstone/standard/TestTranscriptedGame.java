package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestTranscriptedGame {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new AlphaGameFactory());
    }

    @Test
    public void shouldPrintRightTranscript(){
        // Given game
        Game innerGame = game;
        // And the decorator is enabled
        game = new TranscriptedGame(innerGame);
        // Things happen and the decorater prints the right statement
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
        game.usePower(Player.FINDUS);
        game.endTurn();
        game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN, 1));
        game.usePower(Player.PEDDERSEN);
        game.endTurn();
        game.attackCard(Player.FINDUS, game.getCardInField(Player.FINDUS,0), game.getCardInField(Player.PEDDERSEN,0));
        game.endTurn();
        game.attackHero(Player.PEDDERSEN, game.getCardInField(Player.PEDDERSEN,0));
        // When the decorator is disabled
        game = innerGame;
        // Nothing is printed.
        game.endTurn();
    }

}

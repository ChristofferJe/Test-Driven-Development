package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestTranscriptedGame {
    private Game game;
    private TranscriptedGame transGame;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new AlphaGameFactory());
        transGame = new TranscriptedGame(game);
    }

    @Test
    public void shouldCreateRighTranscript(){
        // Given game
        // Things happen
        transGame.playCard(Player.FINDUS, transGame.getCardInHand(Player.FINDUS, 2));
        transGame.usePower(Player.FINDUS);
        transGame.endTurn();
        transGame.playCard(Player.PEDDERSEN, transGame.getCardInHand(Player.PEDDERSEN, 1));
        transGame.usePower(Player.PEDDERSEN);
        transGame.endTurn();
        transGame.attackCard(Player.FINDUS, transGame.getCardInField(Player.FINDUS,0), transGame.getCardInField(Player.PEDDERSEN,0));
        transGame.endTurn();
        transGame.attackHero(Player.PEDDERSEN, transGame.getCardInField(Player.PEDDERSEN,0));
        transGame.printTranscript();
    }

}

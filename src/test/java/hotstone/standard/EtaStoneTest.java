package hotstone.standard;

import org.junit.jupiter.api.BeforeEach;

public class EtaStoneTest {

    private StandardHotStoneGame game;

    @BeforeEach
    public void setUp() {
        game = StandardHotStoneGame.createEtaGame();
    }
}

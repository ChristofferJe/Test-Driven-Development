package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;

import java.util.HashMap;

public class AlphaWinnerStrategy implements WinnnerStrategy {

    @Override
    public Player getWinner(Game game) {
        boolean hasFourRoundsPassed = game.getTurnNumber() > 8;
        if(hasFourRoundsPassed) return Player.FINDUS;
        return null;
    }
}

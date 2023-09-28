package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;

public class AlphaWinnerStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(Game game) {
        boolean hasFourRoundsPassed = game.getTurnNumber() > 8;
        if(hasFourRoundsPassed) return Player.FINDUS;
        return null;
    }
}

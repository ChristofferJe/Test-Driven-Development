package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;

import java.util.HashMap;

public class AlphaWinnerStrategy implements WinnnerStrategy {

    @Override
    public Player getWinner(Game game) {
        if(game.getTurnNumber() > 8) return Player.FINDUS;
        return null;
    }
}

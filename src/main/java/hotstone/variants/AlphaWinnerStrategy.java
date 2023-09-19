package hotstone.variants;

import hotstone.framework.Player;

import java.util.HashMap;

public class AlphaWinnerStrategy implements WinnnerStrategy {

    @Override
    public Player getWinner(HashMap heroes, int turnNumber) {
        if(turnNumber > 8) return Player.FINDUS;
        return null;
    }
}

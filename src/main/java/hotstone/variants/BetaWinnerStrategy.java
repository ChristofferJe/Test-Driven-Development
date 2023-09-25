package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.*;

import java.util.HashMap;

public class BetaWinnerStrategy implements WinnnerStrategy {
    @Override
    public Player getWinner(Game game){
        boolean isFindusDead = game.getHero(Player.FINDUS).getHealth() < 1;
        if(isFindusDead){
            return Player.PEDDERSEN;
        }
        boolean isPeddersenDead = game.getHero(Player.PEDDERSEN).getHealth() < 1;
        if(isPeddersenDead){
            return Player.FINDUS;
        }
        return null;

    }
}

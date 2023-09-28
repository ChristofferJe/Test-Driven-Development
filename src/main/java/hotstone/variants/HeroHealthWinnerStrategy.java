package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.WinnerStrategy;

public class HeroHealthWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(Game game){
        boolean isFindusDead = isPlayerDead(Player.FINDUS, game);
        if(isFindusDead){
            return Player.PEDDERSEN;
        }
        boolean isPeddersenDead = isPlayerDead(Player.PEDDERSEN, game);
        if(isPeddersenDead){
            return Player.FINDUS;
        }
        return null;

    }

    private boolean isPlayerDead(Player who, Game game) {
        return game.getHero(who).getHealth() < 1;
    }

}

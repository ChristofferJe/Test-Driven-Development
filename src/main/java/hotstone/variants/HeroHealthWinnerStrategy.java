package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.WinnerStrategy;

public class HeroHealthWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(Game game){
        if(isPlayerDead(Player.FINDUS, game)){
            return Player.PEDDERSEN;
        }
        if(isPlayerDead(Player.PEDDERSEN, game)){
            return Player.FINDUS;
        }
        return null;
    }

    private boolean isPlayerDead(Player who, Game game) {
        return game.getHero(who).getHealth() < 1;
    }

    @Override
    public void increaseAttackSum(Player who, Card attackinCard){};
}

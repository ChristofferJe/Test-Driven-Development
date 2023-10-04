package hotstone.variants;

import hotstone.framework.*;

public class FindusWinsWinnerStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(Game game) {
        boolean hasFourRoundsPassed = game.getTurnNumber() > 8;
        if(hasFourRoundsPassed) return Player.FINDUS;
        return null;
    }

    @Override
    public void increaseAttackSum(Player who, Card attackinCard){};
}

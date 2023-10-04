package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.WinnerStrategy;

public class AlternatingWinnerStrategy implements WinnerStrategy {
    private final WinnerStrategy before6RoundsStrategy;
    private final WinnerStrategy after6RoundsStrategy;
    private WinnerStrategy currentState;

    public AlternatingWinnerStrategy(WinnerStrategy before6RoundsStrategy, WinnerStrategy after6RoundsStrategy){
        this.before6RoundsStrategy = before6RoundsStrategy;
        this.after6RoundsStrategy = after6RoundsStrategy;
    }
    @Override
    public Player getWinner(Game game) {
        setCurrentState(game.getTurnNumber());
        return currentState.getWinner(game);
    }

    private void setCurrentState(int turnNumber) {
        boolean isBefore6Rounds = turnNumber<13;
        if(isBefore6Rounds){currentState = before6RoundsStrategy;}
        else{currentState = after6RoundsStrategy;}
    }

    @Override
    public void increaseAttackSum(Player who, Card attackingCard, int turnNumber) {
        setCurrentState(turnNumber);
        currentState.increaseAttackSum(who,attackingCard,turnNumber);
    }
}


package hotstone.variants;

import hotstone.framework.*;

import java.util.HashMap;

public class MinionAttackWinnerStrategy implements WinnerStrategy {

    private final HashMap<Player, Integer> attackSums;

    public MinionAttackWinnerStrategy(){
        attackSums = new HashMap<>();
        Integer findusAttackSum = 0;
        Integer peddersenAttackSum = 0;
        attackSums.put(Player.FINDUS, findusAttackSum);
        attackSums.put(Player.PEDDERSEN, peddersenAttackSum);

    }

    @Override
    public Player getWinner(Game game) {
        if (isWinner(Player.FINDUS)){
            return Player.FINDUS;
        }
        if (isWinner(Player.PEDDERSEN)){
            return Player.PEDDERSEN;
        }
        return null;
    }

    private boolean isWinner(Player who) {
        return attackSums.get(who) > 7;
    }

    @Override
    public void increaseAttackSum(Player who, Card attackingCard, int turnNumber){
        Integer currentAttackSum = attackSums.get(who);
        Integer attackAmount = attackingCard.getAttack();
        attackSums.put(who, currentAttackSum + attackAmount);
    }
}

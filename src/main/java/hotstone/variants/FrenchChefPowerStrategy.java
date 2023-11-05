package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.StandardHotStoneGame;

public class FrenchChefPowerStrategy implements PowerStrategy {
    private final PickNumberStrategy pickNumberStrategy;

    FrenchChefPowerStrategy(PickNumberStrategy pickNumberStrategy){
        this.pickNumberStrategy = pickNumberStrategy;
    }
    @Override
    public void execPower(MutableGame game) {
        Player opponent = Utility.computeOpponent(game.getPlayerInTurn());
        int fieldSize = game.getFieldSize(opponent);
        boolean isFieldEmpty = fieldSize == 0;
        if(!isFieldEmpty) {
            int index = pickNumberStrategy.getNumber(fieldSize);
            MutableCard card = game.getCardInField(opponent, index);
            game.decreaseCardHealth(card, 2);
            game.removeIfDead(card);
        }
    }

    @Override
    public String getDescription() {
        return "Opp M: (0,-2)";
    }
}

package hotstone.variants;

import hotstone.framework.*;

public class ItalianChefPowerStrategy implements PowerStrategy {
    private final PickNumberStrategy pickNumberStrategy;

    public ItalianChefPowerStrategy(PickNumberStrategy pickNumberStrategy) {
        this.pickNumberStrategy = pickNumberStrategy;
    }

    @Override
    public void execPower(MutableGame game) {
        Player player = game.getPlayerInTurn();
        int fieldSize = game.getFieldSize(player);
        boolean isFieldEmpty = fieldSize == 0;
        if (!isFieldEmpty) {
            int index = pickNumberStrategy.getNumber(fieldSize);
            MutableCard card = game.getCardInField(player, index);
            card.increaseAttack(2);
        }
    }

    @Override
    public String getDescription() {
        return "M: (+2,0)";
    }
}

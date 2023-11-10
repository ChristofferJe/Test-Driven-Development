package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

public class TomatoSaladEffectStrategy implements EffectStrategy {

    private final PickNumberStrategy pickNumberStrategy;

    public TomatoSaladEffectStrategy(PickNumberStrategy pickNumberStrategy) {
        this.pickNumberStrategy = pickNumberStrategy;
    }

    @Override
    public void execEffect(MutableGame game) {
        Player owner = game.getPlayerInTurn();
        int fieldSize = game.getFieldSize(owner);
        boolean isFieldEmpty = fieldSize == 0;
        if (!isFieldEmpty) {
            MutableCard mutableCard = getCardFromField(game, owner);
            game.increaseCardAttack(mutableCard, 1);
        }
    }

    private MutableCard getCardFromField(MutableGame game, Player owner) {
        int fieldSize = game.getFieldSize(owner);
        int index = pickNumberStrategy.getNumber(fieldSize);
        MutableCard card = game.getCardInField(owner, index);
        return card;
    }

    @Override
    public String getEffectDescription() {
        return "M: (+1,0)";
    }
}

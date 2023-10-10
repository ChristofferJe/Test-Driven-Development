package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

public class TomatoSaladEffectStrategy implements EffectStrategy {

    private final PickNumberStrategy pickNumberStrategy;

    public TomatoSaladEffectStrategy(PickNumberStrategy pickNumberStrategy) {
        this.pickNumberStrategy = pickNumberStrategy;
    }

    @Override
    public void execEffect(StandardHotStoneGame game) {
        Player owner = game.getPlayerInTurn();
        int fieldSize = game.getFieldSize(owner);
        boolean isFieldEmpty = fieldSize == 0;
        if (!isFieldEmpty) {
            MutableCard stdCard = getCardFromField(game, owner);
            stdCard.increaseAttack(1);
        }
    }

    private MutableCard getCardFromField(StandardHotStoneGame game, Player owner) {
        int fieldSize = game.getFieldSize(owner);
        int index = pickNumberStrategy.getNumber(fieldSize);
        Card card = game.getCardInField(owner, index);
        return game.asStandardCard(card);
    }

    @Override
    public String getEffectDescription() {
        return "M: (+1,0)";
    }
}

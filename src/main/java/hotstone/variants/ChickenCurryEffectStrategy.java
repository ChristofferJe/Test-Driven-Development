package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.StandardHotStoneGame;

public class ChickenCurryEffectStrategy implements EffectStrategy {
    private final PickNumberStrategy pickNumberStrategy;

    public ChickenCurryEffectStrategy(PickNumberStrategy pickNumberStrategy) {
        this.pickNumberStrategy = pickNumberStrategy;
    }

    @Override
    public void execEffect(StandardHotStoneGame game) {
        Player owner = game.getPlayerInTurn();
        Player opponent = Utility.computeOpponent(owner);
        int fieldSize = game.getFieldSize(opponent);
        boolean isFieldEmpty = fieldSize == 0;
        if (!isFieldEmpty) {
            MutableCard stdCard = getCardFromField(game, opponent);
            game.killMinion(stdCard);
        }
    }

    private MutableCard getCardFromField(StandardHotStoneGame game, Player who) {
        int fieldSize = game.getFieldSize(who);
        int index = pickNumberStrategy.getNumber(fieldSize);
        Card card = game.getCardInField(who, index);
        return game.asMutableCard(card);
    }

    @Override
    public String getEffectDescription() {return "Kill opp M";
    }
}

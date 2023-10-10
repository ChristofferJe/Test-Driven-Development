package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.StandardCard;
import hotstone.standard.StandardHotStoneGame;

public class BeefBurgerEffectStrategy implements EffectStrategy {
    private final PickNumberStrategy pickNumberStrategy;

    public BeefBurgerEffectStrategy(PickNumberStrategy pickNumberStrategy) {
        this.pickNumberStrategy = pickNumberStrategy;
    }

    @Override
    public void execEffect(StandardHotStoneGame game) {
        Player owner = game.getPlayerInTurn();
        Player opponent = Utility.computeOpponent(owner);
        int fieldSize = game.getFieldSize(opponent);
        boolean isFieldEmpty = fieldSize == 0;
        if (!isFieldEmpty) {
            StandardCard stdCard = getCardFromField(game, opponent);
            stdCard.increaseAttack(2);
        }
    }

    private StandardCard getCardFromField(StandardHotStoneGame game, Player who) {
        int fieldSize = game.getFieldSize(who);
        int index = pickNumberStrategy.getNumber(fieldSize);
        Card card = game.getCardInField(who, index);
        return game.asStandardCard(card);
    }

    @Override
    public String getEffectDescription() {
        return "Opp M: (+2,0)";
    }
}

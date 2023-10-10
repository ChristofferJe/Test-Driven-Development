package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.EffectStrategy;
import hotstone.framework.Player;
import hotstone.framework.Utility;
import hotstone.standard.StandardHotStoneGame;

public class BrownRiceEffectStrategy implements EffectStrategy {
    @Override
    public void execEffect(StandardHotStoneGame game) {
        Player owner = game.getPlayerInTurn();
        Player opponent = Utility.computeOpponent(owner);
        game.decreaseHeroHealth(opponent, 1);
    }

    @Override
    public String getEffectDescription() {
        return "Opp H: (0,-1)";
    }
}

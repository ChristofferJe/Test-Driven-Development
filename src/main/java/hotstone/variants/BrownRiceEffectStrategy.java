package hotstone.variants;

import hotstone.framework.*;

public class BrownRiceEffectStrategy implements EffectStrategy {
    @Override
    public void execEffect(MutableGame game) {
        Player owner = game.getPlayerInTurn();
        Player opponent = Utility.computeOpponent(owner);
        game.decreaseHeroHealth(opponent, 1);
    }

    @Override
    public String getEffectDescription() {
        return "Opp H: (0,-1)";
    }
}

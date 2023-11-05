package hotstone.variants;

import hotstone.framework.EffectStrategy;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;

public class PokeBowlEffectStrategy implements EffectStrategy {
    @Override
    public void execEffect(MutableGame game) {
        Player owner = game.getPlayerInTurn();
        game.increaseHeroHealth(owner, 2);
    }

    @Override
    public String getEffectDescription() {
        return "H: (0,+2)";
    }
}

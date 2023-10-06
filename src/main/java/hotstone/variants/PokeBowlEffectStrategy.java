package hotstone.variants;

import hotstone.framework.EffectStrategy;
import hotstone.framework.Player;
import hotstone.framework.Utility;
import hotstone.standard.StandardHotStoneGame;

public class PokeBowlEffectStrategy implements EffectStrategy {
    @Override
    public void execEffect(StandardHotStoneGame game) {
        Player owner = game.getPlayerInTurn();
        game.increaseHeroHealth(owner, 2);
    }

    @Override
    public String getEffectDescription() {
        return "“H: (0,+2)";
    }
}

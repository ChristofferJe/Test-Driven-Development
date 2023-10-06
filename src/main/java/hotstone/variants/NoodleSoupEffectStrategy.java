package hotstone.variants;

import hotstone.framework.EffectStrategy;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;

public class NoodleSoupEffectStrategy implements EffectStrategy {
    @Override
    public void execEffect(StandardHotStoneGame game) {
        Player owner = game.getPlayerInTurn();
        game.drawCard(owner);
    }

    @Override
    public String getEffectDescription() {
        return "“Draw Card";
    }
}

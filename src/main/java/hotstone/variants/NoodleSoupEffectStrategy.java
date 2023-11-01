package hotstone.variants;

import hotstone.framework.EffectStrategy;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;

public class NoodleSoupEffectStrategy implements EffectStrategy {
    @Override
    public void execEffect(MutableGame game) {
        Player owner = game.getPlayerInTurn();
        game.drawCard(owner);
    }

    @Override
    public String getEffectDescription() {
        return "Draw Card";
    }
}

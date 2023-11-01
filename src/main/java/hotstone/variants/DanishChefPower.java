package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

public class DanishChefPower implements hotstone.framework.PowerStrategy {
    @Override
    public void execPower(MutableGame game) {
        Player player = game.getPlayerInTurn();
        EffectStrategy doNothingEffectStrategy = new DoNothingEffectStrategy();
        Card sovs = new StandardCard(GameConstants.SOVS_CARD, 0, 1, 1, player, doNothingEffectStrategy);
        game.playCard(player, sovs);
    }

    @Override
    public String getDescription() {
        return "Field Sovs";
    }
}

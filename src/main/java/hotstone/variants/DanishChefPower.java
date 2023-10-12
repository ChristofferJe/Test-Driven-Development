package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Utility;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

public class DanishChefPower implements hotstone.framework.PowerStrategy {
    @Override
    public void execPower(MutableGame game) {
        Player player = game.getPlayerInTurn();
        Card sovs = new StandardCard(GameConstants.SOVS_CARD, 0, 1, 1, Player.PEDDERSEN, null);
        game.playCard(player, sovs);
    }

    @Override
    public String getDescription() {
        return "Field Sovs";
    }
}

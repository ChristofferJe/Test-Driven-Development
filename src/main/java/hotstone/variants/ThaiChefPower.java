package hotstone.variants;

import hotstone.framework.MutableGame;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.Utility;

public class ThaiChefPower implements hotstone.framework.PowerStrategy {
    @Override
    public void execPower(MutableGame game) {
        Player opponent = Utility.computeOpponent(game.getPlayerInTurn());
        game.decreaseHeroHealth(opponent, 2);
    }


    @Override
    public String getDescription() {
        return "Opp H: (0,-2)";
    }
}

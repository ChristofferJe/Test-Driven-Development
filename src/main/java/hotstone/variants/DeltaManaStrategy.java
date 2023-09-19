package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHero;

public class DeltaManaStrategy implements ManaStrategy {
    @Override
    public void restoreMana(Player who, Game game) {
        StandardHero stdHero = (StandardHero) game.getHero(who);
        stdHero.setMana(7);
    }
}

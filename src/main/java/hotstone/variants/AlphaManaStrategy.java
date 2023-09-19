package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

public class AlphaManaStrategy implements ManaStrategy {
    @Override
    public void restoreMana(Player who, Game game) {
        StandardHero stdHero = (StandardHero) game.getHero(who);
        stdHero.setMana(3);
    }
}

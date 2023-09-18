package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

public class AlphaManaStrategy implements ManaStrategy {
    @Override
    public void restoreMana(Hero hero, int turnNumber) {
        StandardHero stdHero = (StandardHero) hero;
        stdHero.setMana(3);
    }
}

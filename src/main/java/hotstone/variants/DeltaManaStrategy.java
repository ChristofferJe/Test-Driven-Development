package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHero;

public class DeltaManaStrategy implements ManaStrategy {
    @Override
    public int calculateMana(int turnNumber) {return 7;}
}

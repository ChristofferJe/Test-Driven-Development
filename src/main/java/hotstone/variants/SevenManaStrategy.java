package hotstone.variants;

import hotstone.framework.ManaStrategy;

public class SevenManaStrategy implements ManaStrategy {
    @Override
    public int calculateMana(int turnNumber) {return 7;}
}

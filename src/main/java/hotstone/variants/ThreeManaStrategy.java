package hotstone.variants;

import hotstone.framework.*;

public class ThreeManaStrategy implements ManaStrategy {
    @Override
    public int calculateMana(int turnNumber) { return 3; }
}

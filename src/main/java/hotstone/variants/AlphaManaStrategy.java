package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

public class AlphaManaStrategy implements ManaStrategy {
    @Override
    public int calculateMana(int turnNumber) { return 3; }
}

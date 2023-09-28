package hotstone.variants;

import hotstone.framework.*;

public class ProgressiveManaStrategy implements ManaStrategy {
    @Override
    public int calculateMana(int turnNumber) {
        if(turnNumber % 2 == 0){ return Math.min(7, turnNumber / 2 ); }
        return Math.min(7, turnNumber / 2 + 1);
    }


}

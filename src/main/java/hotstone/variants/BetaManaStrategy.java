package hotstone.variants;

import java.math.*;
import hotstone.standard.*;
import hotstone.framework.*;

public class BetaManaStrategy implements ManaStrategy {
    @Override
    public int calculateMana(int turnNumber) {
        if(turnNumber % 2 == 0){ return Math.min(7, turnNumber / 2 ); }
        return Math.min(7, turnNumber / 2 + 1);
    }


}

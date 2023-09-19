package hotstone.variants;

import java.math.*;
import hotstone.standard.*;
import hotstone.framework.*;

public class BetaManaStrategy implements ManaStrategy {
    @Override
    public void restoreMana(Player who, Game game) {
        StandardHero stdHero = (StandardHero) game.getHero(who);
        stdHero.setMana(calculateMana(game.getTurnNumber()));
    }

    private int calculateMana(int turnNumber) {
        if(turnNumber % 2 == 0){
            return Math.min(7, turnNumber / 2 );

        } else{
            return Math.min(7, turnNumber / 2 + 1);
        }
    }


}

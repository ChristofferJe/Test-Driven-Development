package hotstone.variants;

import java.math.*;
import hotstone.framework.Hero;
import hotstone.standard.StandardHero;

public class BetaManaStrategy implements ManaStrategy {
    @Override
    public void restoreMana(Hero hero, int turnNumber) {
        StandardHero stdHero = (StandardHero) hero;
        stdHero.setMana(calculateMana(turnNumber));
    }

    private int calculateMana(int turnNumber) {
        if(turnNumber % 2 == 0){
            return Math.min(7, turnNumber / 2 );

        } else{
            return Math.min(7, turnNumber / 2 + 1);
        }
    }


}

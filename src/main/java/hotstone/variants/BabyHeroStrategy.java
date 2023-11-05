package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;


public class BabyHeroStrategy implements HeroStrategy {

    @Override
    public MutableHero createHero(Player who) {
        MutableHero mutableHero = new StandardHero(who, GameConstants.BABY_HERO_TYPE, new BabyPowerStrategy());
        return mutableHero;
    }

}

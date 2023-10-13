package hotstone.variants;

import hotstone.framework.HeroStrategy;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;
import hotstone.standard.StandardHotStoneGame;

public class BabyHeroStrategy implements HeroStrategy {

    @Override
    public StandardHero createHero(Player who) {
        StandardHero stdHero = new StandardHero(who, GameConstants.BABY_HERO_TYPE, new BabyPowerStrategy());
        return stdHero;
    }

}

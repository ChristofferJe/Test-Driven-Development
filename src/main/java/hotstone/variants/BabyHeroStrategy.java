package hotstone.variants;

import hotstone.framework.Hero;
import hotstone.framework.HeroStrategy;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;
import hotstone.standard.StandardHotStoneGame;

public class BabyHeroStrategy implements HeroStrategy {

    @Override
    public StandardHero createHero(Player who) {
        StandardHero stdHero = new StandardHero(who, GameConstants.BABY_HERO_TYPE, "cute");
        return stdHero;
    }

    @Override
    public void execPower(Player who, StandardHotStoneGame game) {}
}

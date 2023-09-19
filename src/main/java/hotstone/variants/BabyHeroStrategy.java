package hotstone.variants;

import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;
import hotstone.standard.StandardHotStoneGame;

public class BabyHeroStrategy implements HeroStrategy {

    @Override
    public Hero assignHero(Player who) {
        Hero hero = new StandardHero(who, GameConstants.BABY_HERO_TYPE);
        return hero;
    }

    @Override
    public void execPower(Player who, StandardHotStoneGame game) {}
}

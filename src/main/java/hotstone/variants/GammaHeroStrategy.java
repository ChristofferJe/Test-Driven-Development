package hotstone.variants;

import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;
import hotstone.variants.HeroStrategy;

public class GammaHeroStrategy implements HeroStrategy {
    @Override
    public Hero assignHero(Player who) {

        if(who == Player.FINDUS) {
            Hero hero = new StandardHero(who, GameConstants.THAI_CHEF_HERO_TYPE);
            return hero;
        }
        Hero hero = new StandardHero(who, GameConstants.DANISH_CHEF_HERO_TYPE);
        return hero;
    }
}

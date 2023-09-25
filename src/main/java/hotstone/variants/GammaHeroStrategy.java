package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

public class GammaHeroStrategy implements HeroStrategy {
    @Override
    public Hero createHero(Player who) {

        if(who == Player.FINDUS) {
            Hero hero = new StandardHero(who, GameConstants.THAI_CHEF_HERO_TYPE);
            return hero;
        }
        Hero hero = new StandardHero(who, GameConstants.DANISH_CHEF_HERO_TYPE);
        return hero;
    }

    @Override
    public void execPower(Player who, StandardHotStoneGame game) {
        String type = game.getHero(who).getType();
        boolean isThaiHero = type == GameConstants.THAI_CHEF_HERO_TYPE;
        if(isThaiHero){
            StandardHero stdHero = (StandardHero) game.getHero(Player.PEDDERSEN);
            stdHero.decreaseHealth(2);
        }
        Card sovs = new StandardCard(GameConstants.SOVS_CARD, 0, 1, 1, Player.PEDDERSEN);
        game.playCard(Player.PEDDERSEN, sovs);
    }
}

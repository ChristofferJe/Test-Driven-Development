package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

import java.util.*;

public class GammaHeroStrategy implements HeroStrategy {

    private final HashMap<Player, String> types;

    public GammaHeroStrategy(){
        types = new HashMap<>();
        types.put(Player.FINDUS, GameConstants.THAI_CHEF_HERO_TYPE);
        types.put(Player.PEDDERSEN, GameConstants.DANISH_CHEF_HERO_TYPE);
    }
    @Override
    public Hero createHero(Player who) {
        Hero hero = new StandardHero(who, types.get(who));
        return hero;
    }

    @Override
    public void execPower(Player who, StandardHotStoneGame game) {
        boolean isThaiHero = GameConstants.THAI_CHEF_HERO_TYPE.equals(types.get(who));
        if(isThaiHero){
            execThaiPower(game);
        }
        execDanishPower(game);
    }

    private static void execDanishPower(StandardHotStoneGame game) {
        Card sovs = new StandardCard(GameConstants.SOVS_CARD, 0, 1, 1, Player.PEDDERSEN);
        game.playCard(Player.PEDDERSEN, sovs);
    }

    private void execThaiPower(StandardHotStoneGame game) {
        StandardHero stdHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        stdHero.decreaseHealth(2);
    }
}

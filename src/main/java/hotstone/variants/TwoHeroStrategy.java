package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

import java.util.*;

public class TwoHeroStrategy implements HeroStrategy {

    private final HashMap<Player, String> types;
    private final HashMap<Player, String> descriptions;


    public TwoHeroStrategy(){
        types = new HashMap<>();
        types.put(Player.FINDUS, GameConstants.THAI_CHEF_HERO_TYPE);
        types.put(Player.PEDDERSEN, GameConstants.DANISH_CHEF_HERO_TYPE);

        descriptions = new HashMap<>();
        descriptions.put(Player.FINDUS, "Opp H: (0,-2)");
        descriptions.put(Player.PEDDERSEN, "Field Sovs");

    }
    @Override
    public Hero createHero(Player who) {
        Hero hero = new StandardHero(who, types.get(who), descriptions.get(who));
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
        Card sovs = new StandardCard(GameConstants.SOVS_CARD, 0, 1, 1, Player.PEDDERSEN, null);
        game.playCard(Player.PEDDERSEN, sovs);
    }

    private void execThaiPower(StandardHotStoneGame game) {
        StandardHero stdHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        stdHero.decreaseHealth(2);
    }
}

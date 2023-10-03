package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

import java.util.HashMap;

public class ItalianFrenchHeroStrategy implements HeroStrategy {
    private final HashMap<Player, String> types;
    private final HashMap<Player, String> descriptions;


    public ItalianFrenchHeroStrategy(PickNumberStrategy pickNumberStrategy){
        types = new HashMap<>();
        types.put(Player.FINDUS, GameConstants.FRENCH_CHEF_HERO_TYPE);
        types.put(Player.PEDDERSEN, GameConstants.ITALIAN_CHEF_HERO_TYPE);

        descriptions = new HashMap<>();
        descriptions.put(Player.FINDUS, "Opp M: (0,-2)");
        descriptions.put(Player.PEDDERSEN, "M: (+2,0)");

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
        Card sovs = new StandardCard(GameConstants.SOVS_CARD, 0, 1, 1, Player.PEDDERSEN);
        game.playCard(Player.PEDDERSEN, sovs);
    }

    private void execThaiPower(StandardHotStoneGame game) {
        StandardHero stdHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        stdHero.decreaseHealth(2);
    }
}

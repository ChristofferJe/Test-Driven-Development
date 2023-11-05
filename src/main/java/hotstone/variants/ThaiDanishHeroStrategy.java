package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

import java.util.*;

public class ThaiDanishHeroStrategy implements HeroStrategy {

    private final HashMap<Player, String> types;
    private final HashMap<Player, PowerStrategy> powers;


    public ThaiDanishHeroStrategy(){
        types = new HashMap<>();
        types.put(Player.FINDUS, GameConstants.THAI_CHEF_HERO_TYPE);
        types.put(Player.PEDDERSEN, GameConstants.DANISH_CHEF_HERO_TYPE);


        powers = new HashMap<>();
        powers.put(Player.FINDUS, new ThaiChefPower());
        powers.put(Player.PEDDERSEN, new DanishChefPower());

    }
    @Override
    public MutableHero createHero(Player who) {
        MutableHero mutableHero = new StandardHero(who, types.get(who), powers.get(who));
        return mutableHero;
    }


}

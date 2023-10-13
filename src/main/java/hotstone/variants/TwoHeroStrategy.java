package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

import java.util.*;

public class TwoHeroStrategy implements HeroStrategy {

    private final HashMap<Player, String> types;
    private final HashMap<Player, PowerStrategy> powers;


    public TwoHeroStrategy(){
        types = new HashMap<>();
        types.put(Player.FINDUS, GameConstants.THAI_CHEF_HERO_TYPE);
        types.put(Player.PEDDERSEN, GameConstants.DANISH_CHEF_HERO_TYPE);


        powers = new HashMap<>();
        powers.put(Player.FINDUS, new ThaiChefPower());
        powers.put(Player.PEDDERSEN, new DanishChefPower());

    }
    @Override
    public StandardHero createHero(Player who) {
        StandardHero stdHero = new StandardHero(who, types.get(who), powers.get(who));
        return stdHero;
    }


}

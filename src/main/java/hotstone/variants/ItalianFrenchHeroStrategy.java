package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

import java.util.HashMap;

public class ItalianFrenchHeroStrategy implements HeroStrategy {
    private final HashMap<Player, String> types;
    private final HashMap<Player, PowerStrategy> powers;
    private PickNumberStrategy pickNumberStrategy;


    public ItalianFrenchHeroStrategy(PickNumberStrategy pickNumberStrategy){
        this.pickNumberStrategy = pickNumberStrategy;

        types = new HashMap<>();
        types.put(Player.FINDUS, GameConstants.FRENCH_CHEF_HERO_TYPE);
        types.put(Player.PEDDERSEN, GameConstants.ITALIAN_CHEF_HERO_TYPE);


        powers = new HashMap<>();
        powers.put(Player.FINDUS, new FrenchChefPowerStrategy(pickNumberStrategy));
        powers.put(Player.PEDDERSEN, new ItalianChefPowerStrategy(pickNumberStrategy));


    }
    @Override
    public MutableHero createHero(Player who) {
        MutableHero mutableHero = new StandardHero(who, types.get(who), powers.get(who));
        return mutableHero;
    }
}

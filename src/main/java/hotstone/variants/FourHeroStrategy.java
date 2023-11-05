package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;

import java.util.ArrayList;

public class FourHeroStrategy implements HeroStrategy {
    private final PickNumberStrategy pickNumberStrategy;
    private final ArrayList<String> types;
    private final ArrayList<PowerStrategy> powers;

    public FourHeroStrategy(PickNumberStrategy pickNumberStrategy) {
        this.pickNumberStrategy = pickNumberStrategy;
        types = new ArrayList<String>();
        types.add(GameConstants.FRENCH_CHEF_HERO_TYPE);
        types.add(GameConstants.ITALIAN_CHEF_HERO_TYPE);
        types.add(GameConstants.THAI_CHEF_HERO_TYPE);
        types.add(GameConstants.DANISH_CHEF_HERO_TYPE);

        powers = new ArrayList<PowerStrategy>();
        powers.add(new FrenchChefPowerStrategy(pickNumberStrategy));
        powers.add(new ItalianChefPowerStrategy(pickNumberStrategy));
        powers.add(new ThaiChefPower());
        powers.add(new DanishChefPower());
    }

    @Override
    public MutableHero createHero(Player who) {
        int index = pickNumberStrategy.getNumber(4);
        return new StandardHero(who, types.get(index), powers.get(index));
    }
}

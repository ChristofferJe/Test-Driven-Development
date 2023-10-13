package hotstone.variants;

import hotstone.framework.*;

public class SemiGameFactory implements GameFactory {
    private PickNumberStrategy pickNumberStrategy;
    public SemiGameFactory(){
        pickNumberStrategy = new RandomNumberStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new HeroHealthWinnerStrategy();
    }

    @Override
    public ManaStrategy createManaStrategy() {
        return new ProgressiveManaStrategy();
    }

    @Override
    public HeroStrategy createHeroStrategy() {
        return new FourHeroStrategy(pickNumberStrategy);
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new DishEffectDeckStrategy(pickNumberStrategy);
    }

    @Override
    public PickNumberStrategy getNumberStrategy() {
        return pickNumberStrategy;
    }
}

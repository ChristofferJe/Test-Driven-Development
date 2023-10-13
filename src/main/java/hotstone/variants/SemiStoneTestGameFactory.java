package hotstone.variants;

import hotstone.framework.*;

public class SemiStoneTestGameFactory implements GameFactory {
    private PickNumberStrategy pickNumberStrategy;
    public SemiStoneTestGameFactory(){
        pickNumberStrategy = new FixedNumberStrategy();
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

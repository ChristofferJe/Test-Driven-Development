package hotstone.variants;

import hotstone.framework.*;

public class SemiGameFactory implements GameFactory {

    public final PickNumberStrategy pickNumberStrategy;

    public SemiGameFactory(TestMode mode){
        if (mode == TestMode.IsTest){
            pickNumberStrategy = new FixedNumberStrategy();
        } else {
            pickNumberStrategy = new RandomNumberStrategy();
        }
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

}

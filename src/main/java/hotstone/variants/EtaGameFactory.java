package hotstone.variants;

import hotstone.framework.*;

public class EtaGameFactory implements GameFactory {

    public final PickNumberStrategy pickNumberStrategy;

    public EtaGameFactory(TestMode mode){
        if (mode == TestMode.IsTest){
            pickNumberStrategy = new FixedNumberStrategy();
        } else {
            pickNumberStrategy = new RandomNumberStrategy();
        }
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new FindusWinsWinnerStrategy();
    }

    @Override
    public ManaStrategy createManaStrategy() {
        return new SevenManaStrategy();
    }

    @Override
    public HeroStrategy createHeroStrategy() {
        return new BabyHeroStrategy();
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new DishEffectDeckStrategy(pickNumberStrategy);
    }


}

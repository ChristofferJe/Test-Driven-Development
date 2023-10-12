package hotstone.variants;

import hotstone.framework.*;

public class EtaTestGameFactory implements GameFactory {
    private PickNumberStrategy pickNumberStrategy;

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
        pickNumberStrategy = new RandomNumberStrategy();
        return new DishEffectDeckStrategy(pickNumberStrategy);
    }

    @Override
    public PickNumberStrategy getNumberStrategy(){ return pickNumberStrategy;}
}

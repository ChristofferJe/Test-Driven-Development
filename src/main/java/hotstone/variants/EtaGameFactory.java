package hotstone.variants;

import hotstone.framework.*;

public class EtaGameFactory implements GameFactory {

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
        PickNumberStrategy pickNumberStrategy = new RandomNumberStrategy();
        return new DishEffectDeckStrategy(pickNumberStrategy);
    }

    @Override
    public PickNumberStrategy getNumberStrategy() {
        return null;
    }

}

package hotstone.variants;

import hotstone.framework.*;

public class GammaGameFactory implements GameFactory {
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
        return new ThaiDanishHeroStrategy();
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new DishDeckStrategy();
    }

}

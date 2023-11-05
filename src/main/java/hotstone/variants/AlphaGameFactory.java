package hotstone.variants;

import hotstone.framework.*;

public class AlphaGameFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new FindusWinsWinnerStrategy();
    }

    @Override
    public ManaStrategy createManaStrategy() {
        return new ThreeManaStrategy();
    }

    @Override
    public HeroStrategy createHeroStrategy() {
        return new BabyHeroStrategy();
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new SpanishDeckStrategy();
    }

}

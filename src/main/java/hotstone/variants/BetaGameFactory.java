package hotstone.variants;

import hotstone.framework.*;

public class BetaGameFactory implements GameFactory {

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
        return new BabyHeroStrategy();
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new SpanishDeckStrategy();
    }

    @Override
    public PickNumberStrategy getNumberStrategy() {
        return null;
    }

}

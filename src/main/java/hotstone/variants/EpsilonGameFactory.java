package hotstone.variants;

import hotstone.framework.*;

public class EpsilonGameFactory implements GameFactory {

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new MinionAttackWinnerStrategy();
    }

    @Override
    public ManaStrategy createManaStrategy() {
        return new ThreeManaStrategy();
    }

    @Override
    public HeroStrategy createHeroStrategy() {
        PickNumberStrategy pickNumberStrategy = new RandomNumberStrategy();
        return new ItalianFrenchHeroStrategy(pickNumberStrategy);
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

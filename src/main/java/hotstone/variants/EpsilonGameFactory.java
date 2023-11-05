package hotstone.variants;

import hotstone.framework.*;

public class EpsilonGameFactory implements GameFactory {
    public final PickNumberStrategy pickNumberStrategy;

    public EpsilonGameFactory(TestMode mode){
        if (mode == TestMode.IsTest){
            pickNumberStrategy = new FixedNumberStrategy();
        } else {
            pickNumberStrategy = new RandomNumberStrategy();
        }
    }

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
        return new ItalianFrenchHeroStrategy(pickNumberStrategy);
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new SpanishDeckStrategy();
    }


}

package hotstone.variants;

import hotstone.framework.*;
import hotstone.variants.*;

public class EpsilonTestGameFactory implements GameFactory {
    private PickNumberStrategy pickNumberStrategy;

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
        pickNumberStrategy = new FixedNumberStrategy();
        return new ItalianFrenchHeroStrategy(pickNumberStrategy);
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new SpanishDeckStrategy();
    }
    @Override
    public PickNumberStrategy getNumberStrategy(){
        return pickNumberStrategy;
    }

}

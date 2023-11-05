package hotstone.variants;

import hotstone.framework.*;

public class ZetaGameFactory implements GameFactory {

    @Override
    public WinnerStrategy createWinnerStrategy() {
        WinnerStrategy heroHealthWinnerStrategy = new HeroHealthWinnerStrategy();
        WinnerStrategy minionAttackWinnerStrategy = new MinionAttackWinnerStrategy();
        return new AlternatingWinnerStrategy(heroHealthWinnerStrategy, minionAttackWinnerStrategy);
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
        return new CincoDeckStrategy();
    }

}

package hotstone.variants;

import hotstone.framework.*;
import hotstone.variants.BabyHeroStrategy;
import hotstone.variants.DishDeckStrategy;
import hotstone.variants.FindusWinsWinnerStrategy;
import hotstone.variants.SevenManaStrategy;

public class DeltaGameFactory implements GameFactory {

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
        return new DishDeckStrategy();
    }

    @Override
    public PickNumberStrategy getNumberStrategy() {
        return null;
    }
}

package hotstone.variants;

import hotstone.framework.*;

public class PersonalizedDeckGameFactory implements GameFactory {
    private final String datafilename;

    public PersonalizedDeckGameFactory(String datafilename){
        this.datafilename = datafilename;
    }
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
        return new PersonalizedDeckStrategy(datafilename);
    }

}

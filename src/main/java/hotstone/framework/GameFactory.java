package hotstone.framework;

public interface GameFactory {
    WinnerStrategy createWinnerStrategy();

    ManaStrategy createManaStrategy();

    HeroStrategy createHeroStrategy();

    DeckStrategy createDeckStrategy();

}

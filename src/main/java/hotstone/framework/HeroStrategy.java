package hotstone.framework;

import hotstone.framework.*;
import hotstone.standard.StandardHotStoneGame;

public interface HeroStrategy {
    Hero createHero(Player who);

    void execPower(Player who, StandardHotStoneGame game);
}

package hotstone.framework;

import hotstone.framework.*;
import hotstone.standard.*;

public interface HeroStrategy {
    StandardHero createHero(Player who);

    void execPower(Player who, StandardHotStoneGame game);
}

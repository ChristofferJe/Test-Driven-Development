package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.StandardHotStoneGame;

public interface HeroStrategy {
    Hero assignHero(Player who);

    void execPower(Player who, StandardHotStoneGame game);
}

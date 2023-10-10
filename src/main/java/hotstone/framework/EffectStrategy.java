package hotstone.framework;

import hotstone.standard.StandardHotStoneGame;

public interface EffectStrategy {
    void execEffect(StandardHotStoneGame game);
    String getEffectDescription();
}

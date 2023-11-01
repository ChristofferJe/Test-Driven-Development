package hotstone.variants;

import hotstone.framework.EffectStrategy;
import hotstone.standard.StandardHotStoneGame;

public class DoNothingEffectStrategy implements EffectStrategy {
    @Override
    public void execEffect(StandardHotStoneGame game) {

    }

    @Override
    public String getEffectDescription() {
        return null;
    }
}

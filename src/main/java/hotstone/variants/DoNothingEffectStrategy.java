package hotstone.variants;

import hotstone.framework.EffectStrategy;
import hotstone.framework.MutableGame;

public class DoNothingEffectStrategy implements EffectStrategy {
    @Override
    public void execEffect(MutableGame game) {

    }

    @Override
    public String getEffectDescription() {
        return null;
    }
}

package hotstone.framework;

import hotstone.standard.StandardHotStoneGame;

public interface PowerStrategy {
    void execPower(MutableGame game);
    String getDescription();
}

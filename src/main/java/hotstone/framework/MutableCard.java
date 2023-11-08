package hotstone.framework;

import hotstone.standard.StandardHotStoneGame;

public interface MutableCard extends Card{
    void setStatus(Boolean status);

    void decreaseHealth(int amount);

    void increaseAttack(int amount);

    void useEffect(StandardHotStoneGame game);

}

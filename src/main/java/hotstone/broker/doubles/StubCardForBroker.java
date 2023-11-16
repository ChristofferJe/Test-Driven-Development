package hotstone.broker.doubles;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class StubCardForBroker implements Card {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public String getEffectDescription() {
        return null;
    }
}

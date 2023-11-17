package hotstone.broker.doubles;

import frds.broker.Servant;
import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;

public class StubCardForBroker implements Card, Servant {
    @Override
    public String getName() { return GameConstants.NOODLE_SOUP_CARD; }

    @Override
    public int getManaCost() {
        return 14;
    }

    @Override
    public int getAttack() {
        return 24;
    }

    @Override
    public int getHealth() {
        return 34;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public Player getOwner() {
        return Player.FINDUS;
    }

    @Override
    public String getEffectDescription() {
        return "EDescript";
    }
}

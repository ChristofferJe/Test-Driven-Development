package hotstone.broker.doubles;

import frds.broker.Servant;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;

public class StubHeroForBroker implements Hero, Servant {
    @Override
    public int getMana() {
        return 34;
    }

    @Override
    public int getHealth() {
        return 26;
    }

    @Override
    public boolean canUsePower() {
        return true;
    }

    @Override
    public String getType() {
        return GameConstants.THAI_CHEF_HERO_TYPE;
    }

    @Override
    public Player getOwner() {
        return Player.FINDUS;
    }

    @Override
    public String getEffectDescription() {
        return "EfDesc";
    }
}

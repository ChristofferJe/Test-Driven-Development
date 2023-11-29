package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Hero;
import hotstone.framework.Player;

public class HeroClientProxy implements Hero, ClientProxy {
    private final Requestor requestor;
    private final String id;

    public HeroClientProxy(Requestor requestor, String id) {
        this.requestor = requestor;
        this.id = id;
    }

    @Override
    public int getMana() {
        int mana = requestor.sendRequestAndAwaitReply(id, OperationNames.HERO_GET_MANA, Integer.class);
        return mana;
    }

    @Override
    public int getHealth() {
        int health = requestor.sendRequestAndAwaitReply(id, OperationNames.HERO_GET_HEALTH, Integer.class);
        return health;
    }

    @Override
    public boolean canUsePower() {
        return requestor.sendRequestAndAwaitReply(id, OperationNames.HERO_IS_ACTIVE, boolean.class);
    }

    @Override
    public String getType() {
        String type = requestor.sendRequestAndAwaitReply(id, OperationNames.HERO_GET_TYPE, String.class);
        return type;
    }

    @Override
    public Player getOwner() {
        Player owner = requestor.sendRequestAndAwaitReply(id, OperationNames.HERO_GET_OWNER, Player.class);
        return owner;
    }

    @Override
    public String getEffectDescription() {
        String description = requestor.sendRequestAndAwaitReply(id, OperationNames.HERO_GET_DESCRIPTION, String.class);
        return description;
    }

    @Override
    public String getId() {
        return id;
    }
}

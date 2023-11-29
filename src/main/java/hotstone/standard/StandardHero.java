package hotstone.standard;


import hotstone.framework.*;

import java.util.UUID;

public class StandardHero implements Hero, MutableHero {

    private final String type;
    private final PowerStrategy powerStrategy;
    private final String id;
    private boolean powerStatus;
    private int mana;
    private int health;
    private final Player owner;

    public StandardHero(Player owner, String type, PowerStrategy powerStrategy){
        this.type = type;
        this.owner = owner;
        this.powerStrategy = powerStrategy;
        powerStatus = true;
        health = GameConstants.HERO_MAX_HEALTH;
        id = UUID.randomUUID().toString();

    }
    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean canUsePower() {
        return powerStatus;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Player getOwner() { return owner; }
    @Override
    public String getEffectDescription() { return powerStrategy.getDescription(); }
    @Override
    public void setPowerStatus(boolean bool){
        powerStatus = bool;
    }
    @Override
    public void decreaseMana(int amount) {
        mana -= amount;
    }
    @Override
    public void setMana(int amount) {
        mana = amount;
    }
    @Override
    public void decreaseHealth(int amount) { health -= amount; }
    @Override
    public void increaseHealth(int amount) { health += amount; }

    @Override
    public void execPower(MutableGame game) {powerStrategy.execPower(game);}

    @Override
    public String getId() {
        return id;
    }
}

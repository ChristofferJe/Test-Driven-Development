package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

import java.util.HashMap;

public class StandardHero implements Hero {

    private final String type;
    private final String description;
    private boolean powerStatus;
    private int mana;
    private int health;
    private final Player owner;

    public StandardHero(Player owner, String type, String description){
        this.type = type;
        this.owner = owner;
        powerStatus = true;
        health = GameConstants.HERO_MAX_HEALTH;
        this.description = description;



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
    public String getEffectDescription() { return description; }

    public void setPowerStatus(boolean bool){
        powerStatus = bool;
    }

    public void decreaseMana(int amount) {
        mana -= amount;
    }

    public void setMana(int amount) {
        mana = amount;
    }

    public void decreaseHealth(int amount) { health -= amount; }

    public void increaseHealth(int amount) { health += amount; }
}

package hotstone.standard;

import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StandardHero implements Hero {

    private boolean powerStatus;
    private int mana;
    private int health;
    private Player owner;

    public StandardHero(Player owner){
        powerStatus = true;
        mana = 3;
        health = GameConstants.HERO_MAX_HEALTH;
        this.owner = owner;
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
        return GameConstants.BABY_HERO_TYPE;
    }

    @Override
    public Player getOwner() { return owner; }

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
}

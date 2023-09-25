package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

import java.util.HashMap;

public class StandardHero implements Hero {

    private final String type;
    private boolean powerStatus;
    private int mana;
    private int health;
    private final Player owner;
    private final HashMap<String, String> descriptions;

    public StandardHero(Player owner, String type){
        this.type = type;
        this.owner = owner;
        powerStatus = true;
        health = GameConstants.HERO_MAX_HEALTH;
        descriptions = new HashMap<>();

        descriptions.put(GameConstants.BABY_HERO_TYPE, "cute");
        descriptions.put(GameConstants.THAI_CHEF_HERO_TYPE, "Opp H: (0,-2)");
        descriptions.put(GameConstants.DANISH_CHEF_HERO_TYPE, "Field Sovs");


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
    public String getEffectDescription() { return descriptions.get(type); }

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

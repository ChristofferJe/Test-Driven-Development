package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

import java.util.HashMap;

public class StandardHero implements Hero {

    private String type;
    private boolean powerStatus;
    private int mana;
    private int health;
    private Player owner;
    private HashMap<String, String> descriptionMap;

    public StandardHero(Player owner, String type){
        this.type = type;
        this.owner = owner;
        powerStatus = true;
        health = GameConstants.HERO_MAX_HEALTH;
        descriptionMap = new HashMap<>();

        descriptionMap.put(GameConstants.BABY_HERO_TYPE, "cute");
        descriptionMap.put(GameConstants.THAI_CHEF_HERO_TYPE, "Opp H: (0,-2)");
        descriptionMap.put(GameConstants.DANISH_CHEF_HERO_TYPE, "Field Sovs");


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
    public String getEffectDescription() { return descriptionMap.get(type); }

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

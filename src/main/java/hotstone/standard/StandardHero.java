package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StandardHero implements Hero {

    private boolean powerStatus;
    private int mana;

    public StandardHero(){
        powerStatus = true;
        mana = 3;
    }
    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getHealth() {
        //fake it
        return GameConstants.HERO_MAX_HEALTH;
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
    public Player getOwner() {
        return null;
    }

    public void setPowerStatus(boolean bool){
        powerStatus = bool;
    }

    public void decreaseMana(int amount) {
        mana -= amount;
    }

    public void setMana(int amount) {
        mana = amount;
    }
}

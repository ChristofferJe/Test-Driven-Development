package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class StandardCard implements Card {


    private final String name;
    private final int cost;
    private final int attack;
    private int health;
    private Boolean status;

    public StandardCard(String name, int cost, int attack, int health){
        this.name = name;
        this.cost = cost;
        this.attack =  attack;
        this.health = health;
        status = false;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getManaCost() {
        return cost;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isActive() {
        return status;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    public void setStatus(Boolean status){
        this.status = status;
    }

    public void decreaseHealth(int amount) { health -= amount; }
}

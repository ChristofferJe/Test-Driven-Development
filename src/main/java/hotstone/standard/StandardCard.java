package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class StandardCard implements Card {


    private final String name;
    private final int cost;
    private int attack;
    private int health;
    private Player owner;
    private Boolean status;

    public StandardCard(String name, int cost, int attack, int health, Player owner){
        this.name = name;
        this.cost = cost;
        this.attack =  attack;
        this.health = health;
        this.owner = owner;
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
        return owner;
    }

    public void setStatus(Boolean status){
        this.status = status;
    }

    public void decreaseHealth(int amount) { health -= amount; }

    public void increaseAttack(int amount) { attack += amount; }
}

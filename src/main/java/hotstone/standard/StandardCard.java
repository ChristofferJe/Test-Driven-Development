package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.EffectStrategy;
import hotstone.framework.Player;

public class StandardCard implements Card {


    private final String name;
    private final int cost;
    private int attack;
    private int health;
    private Player owner;
    private Boolean status;
    private EffectStrategy effectStrategy;

    public StandardCard(String name, int cost, int attack, int health, Player owner, EffectStrategy effectStrategy){
        this.name = name;
        this.cost = cost;
        this.attack =  attack;
        this.health = health;
        this.owner = owner;
        this.effectStrategy = effectStrategy;
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
    public void useEffect(StandardHotStoneGame game){
        boolean hasEffect = effectStrategy != null;
        if(hasEffect) {
            effectStrategy.execEffect(game);
        }
    }
    public String getEffectDescription(){
        boolean hasEffect = effectStrategy != null;
        if(hasEffect) {
            return effectStrategy.getEffectDescription();
        }
        return null;
    }
}

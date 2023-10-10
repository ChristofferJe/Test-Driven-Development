package hotstone.framework;

public interface MutableHero {
    void setPowerStatus(boolean bool);

    void decreaseMana(int amount);

    void setMana(int amount);

    void decreaseHealth(int amount);

    void increaseHealth(int amount);
}

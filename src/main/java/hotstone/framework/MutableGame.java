package hotstone.framework;

import java.util.ArrayList;

public interface MutableGame extends Game {

    MutableHero getHero(Player who);
    MutableCard getCardInHand(Player who, int indexInHand);
    MutableCard getCardInField(Player who, int indexInField);
    void drawCard(Player who);

    void decreaseHeroMana(Player who, int manaAmount);

    void decreaseCardHealth(MutableCard card, int amount);

    void increaseCardAttack(MutableCard card, int amount);

    void decreaseHeroHealth(Player who, int amount);

    void increaseHeroHealth(Player who, int amount);

    void removeIfDead(MutableCard card);

    void restoreMana(Player who);
    void killMinion(MutableCard mutableCard);
}

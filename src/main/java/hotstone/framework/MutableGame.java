package hotstone.framework;

import java.util.ArrayList;

public interface MutableGame {
    boolean isOwner(Player who, Card card);

    boolean isCardActive(Card attackingCard);

    void initializeHands();

    MutableHero asMutableHero(Hero hero);

    void activateMinionsInField(Player who);

    void setHeroPowerStatus(Player who, boolean status);

    void drawCard(Player who);

    void addCardToHandFromDeck(Player who);

    ArrayList<Card> getDeck(Player who);

    void useCardEffect(Card card);

    void decreaseHeroMana(Player who, int manaAmount);

    void moveCardFromHandToField(Player who, Card card);

    Status isPlayCardAllowed(Player who, Card card);

    boolean hasEnoughMana(Player who, int manaAmount);

    boolean isPlayerInTurn(Player who);

    void executeAttackCard(Card attackingCard, Card defendingCard);

    void setCardStatus(Card card, boolean status);

    void decreaseCardHealth(Card card, int amount);

    Status isAttackCardAllowed(Player playerAttacking, Card attackingCard, Card defendingCard);

    void executeAttackHero(Card attackingCard);

    void decreaseHeroHealth(Player who, int amount);

    void increaseHeroHealth(Player who, int amount);

    Status isAttackHeroAllowed(Player playerAttacking, Card attackingCard);

    Status isPowerAllowed(Player who);

    boolean hasUsedPower(Player who);

    void removeIfDead(Card card);

    void removeFromField(Card card);

    void restoreMana(Player who);

    void setHeroMana(Player who, int mana);

    MutableCard asMutableCard(Card card);

    void killMinion(MutableCard mutableCard);
}

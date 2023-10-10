package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;

public class DishEffectCardsStrategy implements CardsStrategy {
    private final PickNumberStrategy pickNumberStrategy;

    public DishEffectCardsStrategy(PickNumberStrategy pickNumberStrategy) {
        this.pickNumberStrategy = pickNumberStrategy;
    }

    @Override
    public ArrayList<Card> generateCards(Player owner) {
        ArrayList<Card> cards = new ArrayList<>();
        for(int i=0; i<2; i++){
            EffectStrategy brownRiceEffectStrategy = new BrownRiceEffectStrategy();
            EffectStrategy tomatoSaladEffectStrategy = new TomatoSaladEffectStrategy(pickNumberStrategy);
            EffectStrategy pokeBowlEffectStrategy = new PokeBowlEffectStrategy();
            EffectStrategy noodleSoupEffectStrategy = new NoodleSoupEffectStrategy();
            EffectStrategy chickenCurryEffectStragey = new ChickenCurryEffectStrategy(pickNumberStrategy);
            EffectStrategy beefBurgerEffectStrategy = new BeefBurgerEffectStrategy(pickNumberStrategy);
            Card BrownRice = new StandardCard(GameConstants.BROWN_RICE_CARD, 1,1,1, owner, brownRiceEffectStrategy);
            Card FrenchFries = new StandardCard(GameConstants.FRENCH_FRIES_CARD,1,2,1, owner, null);
            Card GreenSalad = new StandardCard(GameConstants.GREEN_SALAD_CARD, 2, 2, 3, owner, null);
            Card TomatoSalad = new StandardCard(GameConstants.TOMATO_SALAD_CARD, 2, 2, 2, owner, tomatoSaladEffectStrategy);
            Card PokeBowl = new StandardCard(GameConstants.POKE_BOWL_CARD, 3, 2, 3, owner, pokeBowlEffectStrategy);
            Card PumpkinSoup = new StandardCard(GameConstants.PUMPKIN_SOUP_CARD, 4, 2, 7, owner, null);
            Card NoodleSoup = new StandardCard(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, owner, noodleSoupEffectStrategy);
            Card SpringRolls = new StandardCard(GameConstants.SPRING_ROLLS_CARD, 5,3,7, owner, null);
            Card BakedSalmon = new StandardCard(GameConstants.BAKED_SALMON_CARD,5,8,2, owner, null);
            Card ChickenCurry = new StandardCard(GameConstants.CHICKEN_CURRY_CARD,6,4,4, owner, chickenCurryEffectStragey);
            Card BeefBurger = new StandardCard(GameConstants.BEEF_BURGER_CARD,6,8,6, owner, beefBurgerEffectStrategy);
            Card FiletMignon = new StandardCard(GameConstants.FILET_MIGNON_CARD,7,9,5, owner, null);
            cards.add(BrownRice);
            cards.add(FrenchFries);
            cards.add(GreenSalad);
            cards.add(TomatoSalad);
            cards.add(PokeBowl);
            cards.add(PumpkinSoup);
            cards.add(NoodleSoup);
            cards.add(SpringRolls);
            cards.add(BakedSalmon);
            cards.add(ChickenCurry);
            cards.add(BeefBurger);
            cards.add(FiletMignon);
        }
        return cards;
    }
}

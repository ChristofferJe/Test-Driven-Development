package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;

public class DishEffectDeckStrategy implements DeckStrategy {
    private final DishDeckStrategy shuffleStrategy;
    private final PickNumberStrategy pickNumberStrategy;


    public DishEffectDeckStrategy(PickNumberStrategy pickNumberStrategy){
        this.pickNumberStrategy = pickNumberStrategy;
        shuffleStrategy = new DishDeckStrategy();
    }

    @Override
    public ArrayList<MutableCard> createDeck(Player owner) {
        ArrayList<MutableCard> deck = generateCards(owner);
        shuffleStrategy.orderCards(deck);
        return deck;
    }
    public ArrayList<MutableCard> generateCards(Player owner) {
        ArrayList<MutableCard> cards = new ArrayList<>();
        for(int i=0; i<2; i++){
            EffectStrategy brownRiceEffectStrategy = new BrownRiceEffectStrategy();
            EffectStrategy tomatoSaladEffectStrategy = new TomatoSaladEffectStrategy(pickNumberStrategy);
            EffectStrategy pokeBowlEffectStrategy = new PokeBowlEffectStrategy();
            EffectStrategy noodleSoupEffectStrategy = new NoodleSoupEffectStrategy();
            EffectStrategy chickenCurryEffectStragey = new ChickenCurryEffectStrategy(pickNumberStrategy);
            EffectStrategy beefBurgerEffectStrategy = new BeefBurgerEffectStrategy(pickNumberStrategy);
            EffectStrategy doNothingEffectStrategy = new DoNothingEffectStrategy();
            MutableCard BrownRice = new StandardCard(GameConstants.BROWN_RICE_CARD, 1,1,1, owner, brownRiceEffectStrategy);
            MutableCard FrenchFries = new StandardCard(GameConstants.FRENCH_FRIES_CARD,1,2,1, owner, doNothingEffectStrategy);
            MutableCard GreenSalad = new StandardCard(GameConstants.GREEN_SALAD_CARD, 2, 2, 3, owner, doNothingEffectStrategy);
            MutableCard TomatoSalad = new StandardCard(GameConstants.TOMATO_SALAD_CARD, 2, 2, 2, owner, tomatoSaladEffectStrategy);
            MutableCard PokeBowl = new StandardCard(GameConstants.POKE_BOWL_CARD, 3, 2, 3, owner, pokeBowlEffectStrategy);
            MutableCard PumpkinSoup = new StandardCard(GameConstants.PUMPKIN_SOUP_CARD, 4, 2, 7, owner, doNothingEffectStrategy);
            MutableCard NoodleSoup = new StandardCard(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, owner, noodleSoupEffectStrategy);
            MutableCard SpringRolls = new StandardCard(GameConstants.SPRING_ROLLS_CARD, 5,3,7, owner, doNothingEffectStrategy);
            MutableCard BakedSalmon = new StandardCard(GameConstants.BAKED_SALMON_CARD,5,8,2, owner, doNothingEffectStrategy);
            MutableCard ChickenCurry = new StandardCard(GameConstants.CHICKEN_CURRY_CARD,6,4,4, owner, chickenCurryEffectStragey);
            MutableCard BeefBurger = new StandardCard(GameConstants.BEEF_BURGER_CARD,6,8,6, owner, beefBurgerEffectStrategy);
            MutableCard FiletMignon = new StandardCard(GameConstants.FILET_MIGNON_CARD,7,9,5, owner, doNothingEffectStrategy);
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

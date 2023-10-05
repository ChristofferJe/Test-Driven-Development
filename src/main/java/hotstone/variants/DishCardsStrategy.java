package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.CardsStrategy;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;

public class DishCardsStrategy implements CardsStrategy {
    @Override
    public ArrayList<Card> generateCards(Player owner) {
        ArrayList<Card> cards = new ArrayList<>();
        for(int i=0; i<2; i++){
            Card BrownRice = new StandardCard(GameConstants.BROWN_RICE_CARD, 1,1,2, owner, null);
            Card FrenchFries = new StandardCard(GameConstants.FRENCH_FRIES_CARD,1,2,1, owner, null);
            Card GreenSalad = new StandardCard(GameConstants.GREEN_SALAD_CARD, 2, 2, 3, owner, null);
            Card TomatoSalad = new StandardCard(GameConstants.TOMATO_SALAD_CARD, 2, 3, 2, owner, null);
            Card PokeBowl = new StandardCard(GameConstants.POKE_BOWL_CARD, 3, 2, 4, owner, null);
            Card PumpkinSoup = new StandardCard(GameConstants.PUMPKIN_SOUP_CARD, 4, 2, 7, owner, null);
            Card NoodleSoup = new StandardCard(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, owner, null);
            Card SpringRolls = new StandardCard(GameConstants.SPRING_ROLLS_CARD, 5,3,7, owner, null);
            Card BakedSalmon = new StandardCard(GameConstants.BAKED_SALMON_CARD,5,8,2, owner, null);
            Card ChickenCurry = new StandardCard(GameConstants.CHICKEN_CURRY_CARD,6,8,4, owner, null);
            Card BeefBurger = new StandardCard(GameConstants.BEEF_BURGER_CARD,6,5,6, owner, null);
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

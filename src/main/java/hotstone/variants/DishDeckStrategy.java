package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

import java.util.ArrayList;
import java.util.Collections;

public class DishDeckStrategy implements DeckStrategy {

    @Override
    public ArrayList<MutableCard> createDeck(Player owner) {
        ArrayList<MutableCard> deck = generateCards(owner);
        orderCards(deck);
        return deck;
    }
    public ArrayList<MutableCard> generateCards(Player owner) {
        ArrayList<MutableCard> cards = new ArrayList<>();
        for(int i=0; i<2; i++){
            MutableCard BrownRice = new StandardCard(GameConstants.BROWN_RICE_CARD, 1,1,2, owner, null);
            MutableCard FrenchFries = new StandardCard(GameConstants.FRENCH_FRIES_CARD,1,2,1, owner, null);
            MutableCard GreenSalad = new StandardCard(GameConstants.GREEN_SALAD_CARD, 2, 2, 3, owner, null);
            MutableCard TomatoSalad = new StandardCard(GameConstants.TOMATO_SALAD_CARD, 2, 3, 2, owner, null);
            MutableCard PokeBowl = new StandardCard(GameConstants.POKE_BOWL_CARD, 3, 2, 4, owner, null);
            MutableCard PumpkinSoup = new StandardCard(GameConstants.PUMPKIN_SOUP_CARD, 4, 2, 7, owner, null);
            MutableCard NoodleSoup = new StandardCard(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, owner, null);
            MutableCard SpringRolls = new StandardCard(GameConstants.SPRING_ROLLS_CARD, 5,3,7, owner, null);
            MutableCard BakedSalmon = new StandardCard(GameConstants.BAKED_SALMON_CARD,5,8,2, owner, null);
            MutableCard ChickenCurry = new StandardCard(GameConstants.CHICKEN_CURRY_CARD,6,8,4, owner, null);
            MutableCard BeefBurger = new StandardCard(GameConstants.BEEF_BURGER_CARD,6,5,6, owner, null);
            MutableCard FiletMignon = new StandardCard(GameConstants.FILET_MIGNON_CARD,7,9,5, owner, null);
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

    public void orderCards(ArrayList<MutableCard> deck) {
        Collections.shuffle(deck);
        orderDeck(deck);
    }
    private void orderDeck(ArrayList<MutableCard> deck) {
        // Get card that costs one or less at index 0 of the deck
        placeCardAtIndexWithRightManaCost(0, 1, deck);
        // Get card that costs two or less at index 1 of the deck
        placeCardAtIndexWithRightManaCost(1, 2, deck);
        // Get card that costs four or less at index 2 of the deck
        placeCardAtIndexWithRightManaCost(2, 4, deck);
    }
    private void placeCardAtIndexWithRightManaCost(int index,  int manaCost, ArrayList<MutableCard> deck) {
        for (int j = index; j < deck.size(); j++) {
            Card c = deck.get(j);
            boolean isRightPrice = c.getManaCost() <= manaCost;
            if (isRightPrice) {
                swapCardsByIndex(deck, index, j);
                break;
            }
        }
    }
    private void swapCardsByIndex(ArrayList<MutableCard> deck, int index1, int index2) {
        MutableCard temp = deck.get(index1);
        deck.set(index1, deck.get(index2));
        deck.set(index2, temp);
    }
}

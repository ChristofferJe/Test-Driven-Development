package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;
import java.util.Collections;

public class DishDeckStrategy implements DeckStrategy {

    @Override
    public ArrayList<Card> createDeck(Player owner) {
        ArrayList<Card> deck = new ArrayList<>();
        createShuffledDeck(deck, owner);
        orderDeck(deck);
        return deck;
    }

    private void orderDeck(ArrayList<Card> deck) {
        // Get card that costs one or less at index 0 of the deck
        placeCardAtIndexWithRightManaCost(0, 1, deck);
        // Get card that costs two or less at index 1 of the deck
        placeCardAtIndexWithRightManaCost(1, 2, deck);
        // Get card that costs four or less at index 2 of the deck
        placeCardAtIndexWithRightManaCost(2, 4, deck);
    }

    private void placeCardAtIndexWithRightManaCost(int index,  int manaCost, ArrayList<Card> deck) {
        for (int j = index; j < deck.size(); j++) {
            Card c = deck.get(j);
            boolean isRightPrice = c.getManaCost() <= manaCost;
            if (isRightPrice) {
                swapCardsByIndex(deck, index, j);
                break;
            }
        }
    }


    private void swapCardsByIndex(ArrayList<Card> deck, int index1, int index2) {
        Card temp = deck.get(index1);
        deck.set(index1, deck.get(index2));
        deck.set(index2, temp);
    }

    private void createShuffledDeck(ArrayList<Card> deck, Player owner) {
        Card BrownRice = new StandardCard(GameConstants.BROWN_RICE_CARD, 1,1,2, owner);
        Card FrenchFries = new StandardCard(GameConstants.FRENCH_FRIES_CARD,1,2,1, owner);
        Card GreenSalad = new StandardCard(GameConstants.GREEN_SALAD_CARD, 2, 2, 3, owner);
        Card TomatoSalad = new StandardCard(GameConstants.TOMATO_SALAD_CARD, 2, 3, 2, owner);
        Card PokeBowl = new StandardCard(GameConstants.POKE_BOWL_CARD, 3, 2, 4, owner);
        Card PumpkinSoup = new StandardCard(GameConstants.PUMPKIN_SOUP_CARD, 4, 2, 7, owner);
        Card NoodleSoup = new StandardCard(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, owner);
        Card SpringRolls = new StandardCard(GameConstants.SPRING_ROLLS_CARD, 5,3,7, owner);
        Card BakedSalmon = new StandardCard(GameConstants.BAKED_SALMON_CARD,5,8,2, owner);
        Card ChickenCurry = new StandardCard(GameConstants.CHICKEN_CURRY_CARD,6,8,4, owner);
        Card BeefBurger = new StandardCard(GameConstants.BEEF_BURGER_CARD,6,5,6, owner);
        Card FiletMignon = new StandardCard(GameConstants.FILET_MIGNON_CARD,7,9,5, owner);

        addCardTwice(deck, BrownRice);
        addCardTwice(deck, FrenchFries);
        addCardTwice(deck, GreenSalad);
        addCardTwice(deck, TomatoSalad);
        addCardTwice(deck, PokeBowl);
        addCardTwice(deck, PumpkinSoup);
        addCardTwice(deck, NoodleSoup);
        addCardTwice(deck, SpringRolls);
        addCardTwice(deck, BakedSalmon);
        addCardTwice(deck, ChickenCurry);
        addCardTwice(deck, BeefBurger);
        addCardTwice(deck, FiletMignon);

        Collections.shuffle(deck);
    }

    private void addCardTwice(ArrayList<Card> deck, Card card) {
        deck.add(card);
        deck.add(card);
    }

}

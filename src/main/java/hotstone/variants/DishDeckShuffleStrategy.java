package hotstone.variants;

import hotstone.framework.*;

import java.util.ArrayList;
import java.util.Collections;

public class DishDeckShuffleStrategy implements ShuffleStrategy {
    @Override
    public void orderCards(ArrayList<Card> deck) {
        Collections.shuffle(deck);
        orderDeck(deck);
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

}

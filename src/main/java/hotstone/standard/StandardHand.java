package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Hand;

import java.util.ArrayList;

public class StandardHand implements Hand {

    ArrayList<Card> cards;
    public StandardHand(){
        cards = new ArrayList<Card>();
    }


    public void add(Card card) {
        cards.add(0,card);
    }

    @Override
    public int getSize() {
        return cards.size();
    }

    @Override
    public ArrayList<Card> getHand(){
        return cards;
    }

    public void remove(Card card){
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i) == card){
                cards.remove(i);
                break;
            }
        }
    }
}

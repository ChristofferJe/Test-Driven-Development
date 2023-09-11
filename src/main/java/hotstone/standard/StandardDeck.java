package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Deck;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;

public class StandardDeck implements Deck {

    private ArrayList<StandardCard> cards;
    public StandardDeck(){
        cards = new ArrayList<StandardCard>();
        StandardCard uno = new StandardCard(GameConstants.UNO_CARD, 1, 1,1);
        StandardCard dos = new StandardCard(GameConstants.DOS_CARD, 2, 2,2);
        StandardCard tres = new StandardCard(GameConstants.TRES_CARD, 3, 3,3);
        StandardCard cuatro = new StandardCard(GameConstants.CUATRO_CARD, 2, 3,1);
        StandardCard cinco = new StandardCard(GameConstants.CINCO_CARD, 3, 5,1);
        StandardCard seis = new StandardCard(GameConstants.SEIS_CARD, 2, 1,3);
        StandardCard siete = new StandardCard(GameConstants.SIETE_CARD, 3, 2,4);
        cards.add(0,uno);
        cards.add(1,dos);
        cards.add(2,tres);
        cards.add(3,cuatro);
        cards.add(4,cinco);
        cards.add(5,seis);
        cards.add(6,siete);
    }
    @Override
    public Card draw() {
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    @Override
    public int getSize() {
        return cards.size();
    }
}

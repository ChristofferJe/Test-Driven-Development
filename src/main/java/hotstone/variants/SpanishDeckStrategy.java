package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.DeckStrategy;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;

public class SpanishDeckStrategy implements DeckStrategy {
    @Override
    public ArrayList<Card> createDeck(Player owner) {
        ArrayList<Card> deck = new ArrayList<>();
        Card uno = new StandardCard(GameConstants.UNO_CARD, 1, 1,1, owner);
        Card dos = new StandardCard(GameConstants.DOS_CARD, 2, 2,2, owner);
        Card tres = new StandardCard(GameConstants.TRES_CARD, 3, 3,3, owner);
        Card cuatro = new StandardCard(GameConstants.CUATRO_CARD, 2, 3,1, owner);
        Card cinco = new StandardCard(GameConstants.CINCO_CARD, 3, 5,1, owner);
        Card seis = new StandardCard(GameConstants.SEIS_CARD, 2, 1,3, owner);
        Card siete = new StandardCard(GameConstants.SIETE_CARD, 3, 2,4, owner);
        deck.add(0,uno);
        deck.add(1,dos);
        deck.add(2,tres);
        deck.add(3,cuatro);
        deck.add(4,cinco);
        deck.add(5,seis);
        deck.add(6,siete);
        return deck;
    }

    @Override
    public void execEffect(Card card) {

    }
}

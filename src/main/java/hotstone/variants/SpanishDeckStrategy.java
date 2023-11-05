package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;

public class SpanishDeckStrategy implements DeckStrategy {
    @Override
    public ArrayList<MutableCard> createDeck(Player owner) {
        ArrayList<MutableCard> deck = generateCards(owner);
        return deck;
    }
    public ArrayList<MutableCard> generateCards(Player owner) {
        EffectStrategy doNothingEffectStrategy = new DoNothingEffectStrategy();
        ArrayList<MutableCard> cards = new ArrayList<>();
        MutableCard uno = new StandardCard(GameConstants.UNO_CARD, 1, 1,1, owner, doNothingEffectStrategy);
        MutableCard dos = new StandardCard(GameConstants.DOS_CARD, 2, 2,2, owner, doNothingEffectStrategy);
        MutableCard tres = new StandardCard(GameConstants.TRES_CARD, 3, 3,3, owner, doNothingEffectStrategy);
        MutableCard cuatro = new StandardCard(GameConstants.CUATRO_CARD, 2, 3,1, owner, doNothingEffectStrategy);
        MutableCard cinco = new StandardCard(GameConstants.CINCO_CARD, 3, 5,1, owner, doNothingEffectStrategy);
        MutableCard seis = new StandardCard(GameConstants.SEIS_CARD, 2, 1,3, owner, doNothingEffectStrategy);
        MutableCard siete = new StandardCard(GameConstants.SIETE_CARD, 3, 2,4, owner, doNothingEffectStrategy);
        cards.add(0,uno);
        cards.add(1,dos);
        cards.add(2,tres);
        cards.add(3,cuatro);
        cards.add(4,cinco);
        cards.add(5,seis);
        cards.add(6,siete);
        return cards;
    }
}

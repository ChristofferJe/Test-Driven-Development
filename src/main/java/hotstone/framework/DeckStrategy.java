package hotstone.framework;

import java.util.ArrayList;

public interface DeckStrategy {

    ArrayList<MutableCard> createDeck(Player owner);

}

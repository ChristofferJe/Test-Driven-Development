package hotstone.variants;

import hotstone.framework.*;
import java.util.ArrayList;

public interface DeckStrategy {

    ArrayList<Card> createDeck(Player owner);
}

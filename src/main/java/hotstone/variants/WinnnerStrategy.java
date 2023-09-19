package hotstone.variants;

import java.util.HashMap;
import hotstone.framework.*;

public interface WinnnerStrategy {

    public Player getWinner(Game game);
}

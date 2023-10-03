package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

import java.util.HashMap;

public class ItalianFrenchHeroStrategy implements HeroStrategy {
    private final HashMap<Player, String> types;
    private final HashMap<Player, String> descriptions;
    private PickNumberStrategy pickNumberStrategy;


    public ItalianFrenchHeroStrategy(PickNumberStrategy pickNumberStrategy){
        this.pickNumberStrategy = pickNumberStrategy;

        types = new HashMap<>();
        types.put(Player.FINDUS, GameConstants.FRENCH_CHEF_HERO_TYPE);
        types.put(Player.PEDDERSEN, GameConstants.ITALIAN_CHEF_HERO_TYPE);

        descriptions = new HashMap<>();
        descriptions.put(Player.FINDUS, "Opp M: (0,-2)");
        descriptions.put(Player.PEDDERSEN, "M: (+2,0)");

    }
    @Override
    public Hero createHero(Player who) {
        Hero hero = new StandardHero(who, types.get(who), descriptions.get(who));
        return hero;
    }

    @Override
    public void execPower(Player who, StandardHotStoneGame game) {
        boolean isFrenchHero = GameConstants.FRENCH_CHEF_HERO_TYPE.equals(types.get(who));
        if(isFrenchHero){
            execFrenchPower(game);
        }
        execItalianPower(game);
    }

    private void execItalianPower(StandardHotStoneGame game) {
    }

    private void execFrenchPower(StandardHotStoneGame game) {
        int index = pickNumberStrategy.getNumber(game.getFieldSize(Player.PEDDERSEN));
        Card card = game.getCardInField(Player.PEDDERSEN, index);
        StandardCard stdCard = game.asStandardCard(card);
        stdCard.decreaseHealth(2);
    }


}

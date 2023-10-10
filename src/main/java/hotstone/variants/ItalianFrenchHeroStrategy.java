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
    public StandardHero createHero(Player who) {
        StandardHero stdHero = new StandardHero(who, types.get(who), descriptions.get(who));
        return stdHero;
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
        int fieldSize = game.getFieldSize(Player.PEDDERSEN);
        boolean isFieldEmpty = fieldSize == 0;
        if (!isFieldEmpty) {
            int index = pickNumberStrategy.getNumber(fieldSize);
            Card card = game.getCardInField(Player.PEDDERSEN, index);
            MutableCard stdCard = game.asStandardCard(card);
            stdCard.increaseAttack(2);
        }
    }


    private void execFrenchPower(StandardHotStoneGame game) {
        int fieldSize = game.getFieldSize(Player.PEDDERSEN);
        boolean isFieldEmpty = fieldSize == 0;
        if(!isFieldEmpty) {
            int index = pickNumberStrategy.getNumber(fieldSize);
            Card card = game.getCardInField(Player.PEDDERSEN, index);
            game.decreaseCardHealth(card, 2);
            game.removeIfDead(card);
        }
    }


}

package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.*;

import java.util.HashMap;

public class ItalianFrenchHeroStrategy implements HeroStrategy {
    private final HashMap<Player, String> types;
    private final HashMap<Player, PowerStrategy> powers;
    private PickNumberStrategy pickNumberStrategy;


    public ItalianFrenchHeroStrategy(PickNumberStrategy pickNumberStrategy){
        this.pickNumberStrategy = pickNumberStrategy;

        types = new HashMap<>();
        types.put(Player.FINDUS, GameConstants.FRENCH_CHEF_HERO_TYPE);
        types.put(Player.PEDDERSEN, GameConstants.ITALIAN_CHEF_HERO_TYPE);


        powers = new HashMap<>();
        powers.put(Player.FINDUS, new FrenchChefPowerStrategy(pickNumberStrategy));
        powers.put(Player.PEDDERSEN, new ItalianChefPowerStrategy(pickNumberStrategy));


    }
    @Override
    public StandardHero createHero(Player who) {
        StandardHero stdHero = new StandardHero(who, types.get(who), powers.get(who));
        return stdHero;
    }

    @Override
    public void execPower(Player who, StandardHotStoneGame game) {
        powers.get(who).execPower(game);
    }

    private void execItalianPower(StandardHotStoneGame game) {
        int fieldSize = game.getFieldSize(Player.PEDDERSEN);
        boolean isFieldEmpty = fieldSize == 0;
        if (!isFieldEmpty) {
            int index = pickNumberStrategy.getNumber(fieldSize);
            Card card = game.getCardInField(Player.PEDDERSEN, index);
            MutableCard stdCard = game.asMutableCard(card);
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

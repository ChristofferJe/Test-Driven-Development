package hotstone.standard;

import hotstone.framework.*;
import hotstone.utility.TestHelper;
import hotstone.variants.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEtaStone {

    private StandardHotStoneGame game;
    private FixedNumberStrategy pickNumberStrategy;

    @BeforeEach
    public void setUp() {
        ManaStrategy manaStrategy = new SevenManaStrategy();
        WinnerStrategy winnerStrategy = new FindusWinsWinnerStrategy();
        HeroStrategy heroStrategy = new BabyHeroStrategy();
        pickNumberStrategy = new FixedNumberStrategy();
        DeckStrategy deckStrategy = new DishEffectDeckStrategy(pickNumberStrategy);
        game = new StandardHotStoneGame(winnerStrategy, manaStrategy, heroStrategy, deckStrategy);
    }


    @Test
    public void brownRiceDoesOneDamageToOpponentHero() {
        // Given game
        // When enough rounds has passed that all types of cards are in Findus Hand
        TestHelper.advanceGameNRounds(game, 20);
        // Find Brown Rice in hand
        Card brownRice = null;
        for (Card c : game.getHand(Player.FINDUS)) {
            boolean isBrownRice = Objects.equals(c.getName(), GameConstants.BROWN_RICE_CARD);
            if (isBrownRice) {
                brownRice = c;
                break;
            }
        }
        assertThat(brownRice.getName(), is(GameConstants.BROWN_RICE_CARD));
        // WHen Peddersen hero has 21 health and Findus plays Brown Rice
        int healthBefore = game.getHero(Player.PEDDERSEN).getHealth();
        assertThat(healthBefore, is(21));
        game.playCard(Player.FINDUS, brownRice);
        // Then Peddersen's hero has 20 health
        int healthAfter = game.getHero(Player.PEDDERSEN).getHealth();
        assertThat(healthAfter, is(20));
    }

    @Test
    public void TomatoSaladAdds1AttackToOwnMinion(){
        // Given game, Findus plays a card with an attack value
        Card card = game.getCardInHand(Player.FINDUS, 0);
        int attackBefore = card.getAttack();
        game.playCard(Player.FINDUS, card);
        // When enough rounds has passed that all types of cards are in Findus Hand
        TestHelper.advanceGameNRounds(game, 20);
        // Find Tomato Salad in hand
        Card tomatoSalad = null;
        for (Card c : game.getHand(Player.FINDUS)) {
            boolean isTomatoSalad = Objects.equals(c.getName(), GameConstants.TOMATO_SALAD_CARD);
            if (isTomatoSalad) {
                tomatoSalad = c;
                break;
            }
        }
        assertThat(tomatoSalad.getName(), is(GameConstants.TOMATO_SALAD_CARD));
        // When Findus plays Tomato Salad
        pickNumberStrategy.setNumber(0);
        game.playCard(Player.FINDUS,tomatoSalad);
        // Then the attack value of the card in field is increased by 1
        int attackAfter = card.getAttack();
        assertThat(attackAfter,is(attackBefore + 1));
    }

    @Test
    public void TomatoSaladEffectDoesNothingWhenFieldIsEmpty(){
        // Given game
        // When enough rounds has passed that all types of cards are in Findus Hand
        TestHelper.advanceGameNRounds(game, 20);
        // Find Tomato Salad in hand
        Card tomatoSalad = null;
        for (Card c : game.getHand(Player.FINDUS)) {
            boolean isTomatoSalad = Objects.equals(c.getName(), GameConstants.TOMATO_SALAD_CARD);
            if (isTomatoSalad) {
                tomatoSalad = c;
                break;
            }
        }
        assertThat(tomatoSalad.getName(), is(GameConstants.TOMATO_SALAD_CARD));
        // When Findus plays Tomato Salad
        game.playCard(Player.FINDUS,tomatoSalad);
        // Then Tomato Salad's does not use effect in self
        int attack = tomatoSalad.getAttack();
        assertThat(attack, is(2));
    }

}

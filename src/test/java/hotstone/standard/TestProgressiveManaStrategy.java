package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.DishDeckStrategy;
import hotstone.variants.ProgressiveManaStrategy;
import hotstone.variants.TwoHeroStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


public class TestProgressiveManaStrategy {

    private ManaStrategy manaStrategy;

    @BeforeEach
    public void setUp() {
        manaStrategy = new ProgressiveManaStrategy();
    }

    @Test
    public void shouldHave1ManaInFirstRound(){
        // Given ProgressiveManaStrategy
        // When the turnnumber is 1
        int turnNumber = 1;
        // Then the mana calculated should be 1
        int mana = manaStrategy.calculateMana(turnNumber);
        assertThat(mana, is(1));
    }

    @Test
    public void shouldHave2ManaInSecondRound(){
        // Given ProgressiveManaStrategy
        // When the turnnumber is 3
        int turnNumber = 3;
        // Then the mana calculated should be 2
        int mana = manaStrategy.calculateMana(turnNumber);
        assertThat(mana, is(2));
    }
}

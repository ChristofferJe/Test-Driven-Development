package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.SevenManaStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;


public class TestSevenManaStrategy {

    private ManaStrategy manaStrategy;

    @BeforeEach
    public void setUp() {
        manaStrategy = new SevenManaStrategy();
    }

    @Test
    public void shouldHave1ManaInFirstRound(){
        // Given SevenManaStrategy
        // When the turnnumber is 1
        int turnNumber = 1;
        // Then the mana calculated should be 7
        int mana = manaStrategy.calculateMana(turnNumber);
        assertThat(mana, is(7));
    }

    @Test
    public void shouldHave2ManaInSecondRound(){
        // Given SevenManaStrategy
        // When the turnnumber is 3
        int turnNumber = 3;
        // Then the mana calculated should be 7
        int mana = manaStrategy.calculateMana(turnNumber);
        assertThat(mana, is(7));
    }
}

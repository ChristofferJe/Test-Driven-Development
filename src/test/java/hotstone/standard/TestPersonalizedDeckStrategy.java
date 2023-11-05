package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.PersonalizedDeckGameFactory;
import hotstone.variants.PersonalizedDeckStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


public class TestPersonalizedDeckStrategy {

    private PersonalizedDeckStrategy deckStrategy;

    @BeforeEach
    public void setUp() {
        deckStrategy = new PersonalizedDeckStrategy("norsedeck.json");
    }

    @Test
    public void DeckShouldBeSize16(){
        // Given personalized deck strategy
        // When a norse deck is initialized
        ArrayList<MutableCard> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain 16 cards
        assertThat(deck.size(), is(16));
    }



    @Test
    public void CardAtIndex0ShouldBeHeimdallur() {
        // Given personalized deck strategy
        // When a norse deck is initialized
        ArrayList<MutableCard> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then the card at index 0 in the deck should be Heimdallur
        String cardName = deck.get(0).getName();
        assertThat(cardName, is("Heimdallur"));
    }

    @Test
    public void ShouldHaveTwoLokiCards() {
        // Given personalized deck strategy
        // When a norse deck is initialized
        ArrayList<MutableCard> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain two Loki cards
        long count = deck.stream().filter(card -> card.getName()
                .equals("Loki")).count();
        assertThat(count, is(2L));
    }
    @Test
    public void ShouldHaveTwoHeimdallurCards() {
        // Given personalized deck strategy
        // When a norse deck is initialized
        ArrayList<MutableCard> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain two Heimdallur cards
        long count = deck.stream().filter(card -> card.getName()
                .equals("Heimdallur")).count();
        assertThat(count, is(2L));
    }
    @Test
    public void ShouldHaveTwoHelCards() {
        // Given personalized deck strategy
        // When a norse deck is initialized
        ArrayList<MutableCard> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain two Hel cards
        long count = deck.stream().filter(card -> card.getName()
                .equals("Hel")).count();
        assertThat(count, is(2L));
    }
    @Test
    public void ShouldHaveTwoSifCards() {
        // Given personalized deck strategy
        // When a norse deck is initialized
        ArrayList<MutableCard> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain two Sif cards
        long count = deck.stream().filter(card -> card.getName()
                .equals("Sif")).count();
        assertThat(count, is(2L));
    }
    @Test
    public void ShouldHaveTwoBaldurCards() {
        // Given personalized deck strategy
        // When a norse deck is initialized
        ArrayList<MutableCard> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain two Baldur cards
        long count = deck.stream().filter(card -> card.getName()
                .equals("Baldur")).count();
        assertThat(count, is(2L));
    }
    @Test
    public void ShouldHaveTwoFreyjaCards() {
        // Given personalized deck strategy
        // When a norse deck is initialized
        ArrayList<MutableCard> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain two Freyja cards
        long count = deck.stream().filter(card -> card.getName()
                .equals("Freyja")).count();
        assertThat(count, is(2L));
    }
    @Test
    public void ShouldHaveTwoThorCards() {
        // Given personalized deck strategy
        // When a norse deck is initialized
        ArrayList<MutableCard> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain two Thor cards
        long count = deck.stream().filter(card -> card.getName()
                .equals("Thor")).count();
        assertThat(count, is(2L));
    }
    @Test
    public void ShouldHaveTwoOdinCards() {
        // Given personalized deck strategy
        // When a norse deck is initialized
        ArrayList<MutableCard> deck = deckStrategy.createDeck(Player.FINDUS);
        // Then it should contain two Odin cards
        long count = deck.stream().filter(card -> card.getName()
                .equals("Odin")).count();
        assertThat(count, is(2L));
    }
    @Test
    public void ShouldHaveHeimdallurAtIndex2InHand(){
        // Given game with personalized norse deck
        Game game = new StandardHotStoneGame(new PersonalizedDeckGameFactory("norsedeck.json"));
        // Then the card at index 2 in Findus hand should be Heimdallur
        Card card = game.getCardInHand(Player.FINDUS, 2);
        assertThat(card.getName(), is("Heimdallur"));
    }
}
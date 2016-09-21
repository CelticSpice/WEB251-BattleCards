/**
 This class represents a deck of cards

 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Random;

public class Deck {
    // Fields
    private ArrayDeque<Card> cards;

    /**
     Constructor
     */

    public Deck() {
        cards = new ArrayDeque<>();
    }

    /**
     The push method adds a card to the top of the deck

     @param card The card to add to the top of the deck
     */

    public void push(Card card) {
        cards.push(card);
    }

    /**
     The pop method removes and returns the card at the top of
     the deck
     */

    public Card pop() {
        return cards.pop();
    }

    /**
     The remove method removes the specifed card from the deck

     @param cardToRemove The card to remove
     */

    public void remove(Card cardToRemove) {
        cards.remove(cardToRemove);
    }

    /**
     The getNumCards method returns the number of cards currently
     in the deck

     @return The number of cards currently in the deck
     */

    public int getNumCards() {
        return cards.size();
    }

    /**
     The shuffle method shuffles the cards in the deck
     */

    public void shuffle() {
        Random rng = new Random();
        Card[] shuffledCards = (Card[]) cards.toArray();

        // Shuffle cards
        for (int i = 0; i < shuffledCards.length / 2; i++) {
            Card toShuffle, tmpCard;
            int toShuffleIndex;

            /*
                We are shuffling out the first and last cards with random
                cards chosen from somewhere in the deck. It is possible
                that no cards will be shuffled in an iteration, and
                it is also possible that the first and last cards will
                simply switch places in an iteration
            */

            // Shuffling out first card in deck with random card from deck
            toShuffleIndex = rng.nextInt(shuffledCards.length);
            toShuffle = shuffledCards[toShuffleIndex];
            tmpCard = shuffledCards[0];
            shuffledCards[0] = toShuffle;
            shuffledCards[toShuffleIndex] = tmpCard;

            // Shuffling out last card in deck with random card from deck
            toShuffleIndex = rng.nextInt(shuffledCards.length);
            toShuffle = shuffledCards[toShuffleIndex];
            tmpCard = shuffledCards[shuffledCards.length - 1];
            shuffledCards[shuffledCards.length - 1] = toShuffle;
            shuffledCards[toShuffleIndex] = tmpCard;
        }

        // Remake the deck of cards as the new shuffled deck
        cards = new ArrayDeque<>(Arrays.asList(shuffledCards));
    }
}

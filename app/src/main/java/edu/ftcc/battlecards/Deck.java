/**
 This class represents a deck of Cards
 9/23/2016
 WEB 251 0001 - Battle Cards (M5HW1)
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
        The getCardNames method returns the names of every card in the Deck

        @return names The name of every card in the deck
     */

    public String[] getCardNames() {
        ArrayDeque<Card> deck = new ArrayDeque<>(cards);
        String[] names = new String[deck.size()];
        for (int i = 0; i < names.length; i++)
            names[i] = deck.pop().getName();
        return names;
    }

    /**
     The getNumCards method returns the number of Cards currently
     in the deck

     @return The number of Cards currently in the deck
     */

    public int getNumCards() {
        return cards.size();
    }

    /**
     The pop method removes and returns the Card at the top of
     the deck
     */

    public Card pop() {
        return cards.pop();
    }

    /**
     The push method adds a Card to the top of the deck

     @param card The Card to add to the top of the deck
     */

    public void push(Card card) {
        cards.push(card);
    }

    /**
     The remove method removes the specified Card from the deck

     @param toRemove The Card to remove
     */

    public void remove(Card toRemove) {
        cards.remove(toRemove);
    }

    /**
     The shuffle method shuffles the Cards in the deck
     */

    public void shuffle() {
        Random rng = new Random();
        Card[] shuffledCards = (Card[]) cards.toArray();

        // Shuffle Cards
        for (int i = 0; i < shuffledCards.length / 2; i++) {
            Card toShuffle, tmp;
            int toShuffleIndex;

            /*
                We are shuffling out the first and last Cards with random
                Cards chosen from somewhere in the deck. It is possible
                that no Cards will be shuffled in an iteration, and
                it is also possible that the first and last Cards will
                simply switch places in an iteration
            */

            // Shuffling out first Card in deck with random Card from deck
            toShuffleIndex = rng.nextInt(shuffledCards.length);
            toShuffle = shuffledCards[toShuffleIndex];
            tmp = shuffledCards[0];
            shuffledCards[0] = toShuffle;
            shuffledCards[toShuffleIndex] = tmp;

            // Shuffling out last Card in deck with random Card from deck
            toShuffleIndex = rng.nextInt(shuffledCards.length);
            toShuffle = shuffledCards[toShuffleIndex];
            tmp = shuffledCards[shuffledCards.length - 1];
            shuffledCards[shuffledCards.length - 1] = toShuffle;
            shuffledCards[toShuffleIndex] = tmp;
        }

        // Remake the deck of Cards as the new shuffled deck
        cards = new ArrayDeque<>(Arrays.asList(shuffledCards));
    }
}

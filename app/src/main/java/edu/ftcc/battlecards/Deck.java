/**
 This class represents a deck of Cards
 9/23/2016
 WEB 251 0001 - Battle Cards (M5HW1)
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class Deck {
    // Fields
    private ArrayList<Card> cards;

    /**
     Constructor
     */

    public Deck() {
        cards = new ArrayList<>();
    }

    /**
     The getCard method returns the card with the specified name

     @param name The name of the card to get
     @return card The card with the specified name; else, null
     */

    public Card getCard(String name) {
        Card card = null;
        for (Card c : cards)
            if (c.getName().equalsIgnoreCase(name)) {
                card = c;
                break;
            }
        return card;
    }

    /**
     The getCardAt method returns the card at the specified index

     @param index Index of card to get
     @return The card specified by the index
     */

    public Card getCardAt(int index) {
        return cards.get(index);
    }

    /**
        The getCardIndex method returns the first index of the card with the specified name; else,
        -1

        @param name The name of the card to get the first index of
        @return index The index of the named card; else, -1
     */

    public int getCardIndex(String name) {
        int index = -1;
        for (int i = 0; i < cards.size() && index == -1; i++)
            if (cards.get(i).getName().equalsIgnoreCase(name))
                index = i;
        return index;
    }

    /**
        The getCardNames method returns the name of every card in the deck,
        alphabetically sorted

        @return names The name of every card in the deck in alphabetical order
     */

    public String[] getCardNames() {
        String[] names = new String[cards.size()];
        for (int i = 0; i < names.length; i++)
            names[i] = cards.get(i).getName();
        Arrays.sort(names);
        return names;
    }

    /**
        The toArray method returns the deck as an array

        @return The deck as an array
     */

    public Card[] toArray() {
        return cards.toArray(new Card[cards.size()]);
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
        return cards.remove(cards.size() - 1);
    }

    /**
     The push method adds a Card to the top of the deck

     @param card The Card to add to the top of the deck
     */

    public void push(Card card) {
        cards.add(card);
    }

    /**
     The remove method removes the specified Card from the deck

     @param toRemove The Card to remove
     */

    public void remove(Card toRemove) {
        cards.remove(toRemove);
    }

    /**
        The removeAt method removes the card at the specified position
        in the deck
     */

    public void removeAt(int position) {
        cards.remove(position);
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
        cards.clear();
        cards.addAll(Arrays.asList(shuffledCards));
    }
}

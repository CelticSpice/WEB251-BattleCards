/**
 This class represents a deck of cards
 WEB 251 0001 - Battle Cards
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Deck {
    // Fields
    private final int MAX_SIZE = 15;

    private ArrayList<Card> cards;

    /**
     Constructor
     */

    public Deck() {
        cards = new ArrayList<>(MAX_SIZE);
    }

    /**
        The clear method removes all cards from the deck
     */

    public void clear() {
        cards.clear();
    }

    /**
     The getCard method returns the card with the specified name

     @param name The name of the card to get
     @return card The card with the specified name; else, if card with name does not exist, null
     */

    public Card getCard(String name) {
        Card card = null;
        for (Card c : cards)
            if (c.getName().equalsIgnoreCase(name))
                card = c;
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
        The getMaxSize method returns the maximum number of cards the deck can contain

        @return The maximum number of cards the deck can contain
     */

    public int getMaxSize() {
        return MAX_SIZE;
    }

    /**
        The indexOf method returns the index of the first occurance of the card with the
        specified name; else, if card with name does not exist, -1

        @param name The name of the card to get the first index of
        @return index The index of the named card; else, -1
     */

    public int indexOf(String name) {
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
     The getNumCards method returns the number of cards currently
     in the deck

     @return The number of cards currently in the deck
     */

    public int getNumCards() {
        return cards.size();
    }

    /**
     The pop method removes and returns the card at the top of
     the deck
     */

    public Card pop() {
        return cards.remove(cards.size() - 1);
    }

    /**
     The push method adds a card to the top of the deck

     @param card The card to add to the top of the deck
     */

    public void push(Card card) {
        cards.add(card);
    }

    /**
     The remove method removes the specified card from the deck

     @param toRemove The card to remove
     */

    public void remove(Card toRemove) {
        cards.remove(toRemove);
    }

    /**
        The removeAt method removes the card at the specified position
        in the deck

        @param position The index of the card to remove
     */

    public void removeAt(int position) {
        cards.remove(position);
    }

    /**
     The shuffle method shuffles the cards in the deck
     */

    public void shuffle() {
        Random rng = new Random();
        Card[] shuffledCards = (Card[]) cards.toArray();

        // Shuffle cards
        for (int i = 0; i < shuffledCards.length / 2; i++) {
            Card toShuffle, tmp;
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
            tmp = shuffledCards[0];
            shuffledCards[0] = toShuffle;
            shuffledCards[toShuffleIndex] = tmp;

            // Shuffling out last card in deck with random card from deck
            toShuffleIndex = rng.nextInt(shuffledCards.length);
            toShuffle = shuffledCards[toShuffleIndex];
            tmp = shuffledCards[shuffledCards.length - 1];
            shuffledCards[shuffledCards.length - 1] = toShuffle;
            shuffledCards[toShuffleIndex] = tmp;
        }

        // Remake the deck of cards as the new shuffled deck
        cards.clear();
        cards.addAll(Arrays.asList(shuffledCards));
    }
}

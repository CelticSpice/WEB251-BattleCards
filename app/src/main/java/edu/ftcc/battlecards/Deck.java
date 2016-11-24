/**
 This class represents a deck of cards
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.Arrays;
import java.util.Random;

public class Deck {
    // Fields
    private final int DEFAULT_MAX = 15;

    private Card[] cards;
    private int maxSize, numCards;

    /**
     Constructor
     */

    public Deck() {
        cards = new Card[DEFAULT_MAX];
        maxSize = DEFAULT_MAX;
        numCards = 0;
    }

    /**
     Constructor - Accepts the maximum number of cards
     */

    public Deck(int max) {
        cards = new Card[max];
        maxSize = max;
        numCards = 0;
    }

    /**
     Clear - Removes every card from the deck
     */

    public void clear() {
        while (numCards != 0)
            cards[--numCards] = null;
    }

    /**
     GetCard - Returns the first instance of the card from the deck with the specified name

     @param name The name of the card to get
     @return card The card with the specified name
     */

    public Card getCard(String name) {
        Card card = null;
        boolean found = false;
        for (int i = 0; i < numCards && !found; i++)
            if (cards[i].getName().equalsIgnoreCase(name)) {
                card = cards[i];
                found = true;
            }
        return card;
    }

    /**
     GetCards - Returns the cards in the deck

     @return toReturn The cards in the deck
     */

    public Card[] getCards() {
        Card[] toReturn = new Card[numCards];
        for (int i = 0; i < numCards; i++)
            toReturn[i] = cards[i];
        return toReturn;
    }

    /**
     GetCardAt - Returns the card at the specified index of the deck

     @param index The index of the card to get
     @return The card at the specified index of the deck
     */

    public Card getCardAt(int index) {
        return cards[index];
    }

    /**
     GetCardNames - Returns the name of every card in the deck,
     alphabetically sorted

     @return names The name of every card in the deck in alphabetical order
     */

    public String[] getCardNames() {
        String[] names = new String[numCards];
        for (int i = 0; i < numCards; i++)
            names[i] = cards[i].getName();
        Arrays.sort(names);
        return names;
    }

    /**
    GetMaxSize - Returns the maximum number of cards the deck can contain

    @return The maximum number of cards the deck can contain
     */

    public int getMaxSize() {
        return maxSize;
    }

    /**
     GetNumCards - Returns the number of cards currently
     in the deck

     @return The number of cards currently in the deck
     */

    public int getNumCards() {
        return numCards;
    }

    /**
     IndexOf - Returns the index of the first card in the deck with the specified name

     @param name The name of the card to get the first index of
     @return index The index of the card in the deck
     */

    public int indexOf(String name) {
        int index = -1;
        boolean found = false;
        for (int i = 0; i < numCards && !found; i++)
            if (cards[i].getName().equalsIgnoreCase(name)) {
                found = true;
                index = i;
            }
        return index;
    }

    /**
     Pop - Removes and returns the card at the top of
     the deck

     @return card The card at the top of the deck
     */

    public Card pop() {
        Card card = cards[numCards - 1];
        cards[numCards--] = null;
        return card;
    }

    /**
     Push - Adds a card to the top of the deck

     @param card The card to add to the top of the deck
     */

    public void push(Card card) {
        cards[numCards++] = card;
    }

    /**
     RemoveAt - Removes the card at the specified position
     in the deck

     @param index The index of the card to remove
     */

    public void removeAt(int index) {
        for (int i = index; i < numCards - 1; i++)
            cards[i] = cards[i + 1];
        cards[--numCards] = null;
    }

    /**
     Shuffle - Shuffles the cards in the deck
     */

    public void shuffle() {
        Random rng = new Random();

        // Shuffle cards
        for (int i = 0; i < maxSize / 2; i++) {
            /*
                We are shuffling out the first and last cards with random
                cards chosen from somewhere in the deck. It is possible
                that no cards will be shuffled in an iteration, and
                it is also possible that the first and last cards will
                simply switch places in an iteration
            */

            // Shuffling out first card in deck with random card from deck
            int toShuffleIndex = rng.nextInt(maxSize);
            Card toShuffle = cards[toShuffleIndex];
            Card tmp = cards[0];
            cards[0] = toShuffle;
            cards[toShuffleIndex] = tmp;

            // Shuffling out last card in deck with random card from deck
            toShuffleIndex = rng.nextInt(maxSize);
            toShuffle = cards[toShuffleIndex];
            tmp = cards[maxSize - 1];
            cards[maxSize - 1] = toShuffle;
            cards[toShuffleIndex] = tmp;
        }
    }
}

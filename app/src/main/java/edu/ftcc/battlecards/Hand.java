/**
 A player's hand of cards
 12/8/2016
 WEB 251 0001 - M5PROJ
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.Random;

public class Hand {
    // Fields
    private final int HAND_SIZE = 3;

    private Card[] cards;
    private int numInHand;
    private int selectedIndex;

    /**
     Constructor
     */

    public Hand() {
        cards = new Card[HAND_SIZE];
        numInHand = 0;
        selectedIndex = -1;
    }

    /**
     Add - Adds the specified card to the first available position in the hand
     If there are no available positions, the card to add replaces the first card in the hand
     The method returns the index in the hand the card is placed

     @param cardToAdd The card to add to the hand
     @return index Where in the hand the card is placed
     */

    public int add(Card cardToAdd) {
        int index = 0;
        boolean placedInHand = false;
        for (int i = 0; i < HAND_SIZE && !placedInHand; i++)
            if (cards[i] == null) {
                cards[i] = cardToAdd;
                placedInHand = true;
                numInHand++;
                index = i;
            }
        if (!placedInHand)
            cards[0] = cardToAdd;
        return index;
    }

    /**
     AutoSelect - Automatically selects a card in the hand
     */

    public void autoSelect() {
        Random rng = new Random();
        int indicesIndex = 0;
        int[] indices = new int[numInHand];
        for (int i = 0; i < HAND_SIZE; i++)
            if (cards[i] != null)
                indices[indicesIndex++] = i;
        selectedIndex = indices[rng.nextInt(indices.length)];
    }

    /**
     GetHandSize - Returns the size of the hand

     @return The size of the hand
     */

    public int getHandSize() {
        return HAND_SIZE;
    }

    /**
     GetNumInHand - Returns the number of cards in the hand

     @return The number of cards in the hand
     */

    public int getNumInHand() {
        return numInHand;
    }

    /**
     GetSelectedIndex - Returns the selected index

     @return The selected index
     */

    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     RemoveSelected - Removes and returns the card in the hand pointed to by the selected index

     @return card The card removed
     */

    public Card removeSelected() {
        Card card = cards[selectedIndex];
        cards[selectedIndex] = null;
        numInHand--;
        selectedIndex = -1;
        return card;
    }

    /**
     SetSelectedIndex - Sets the index of the selected card in the hand

     @param index The index of the selected card
     */

    public void setSelectedIndex(int index) {
        selectedIndex = index;
    }
}
/**
 A player's hand of cards
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

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
     IsCardAt - Returns whether a card exists at a specified index

     @param index The index to check if a card exists in
     @return Whether a card exists at the index
     */

    public boolean isCardAt(int index) {
        return index >= 0 && index < HAND_SIZE && cards[index] != null;
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
     If the index is not valid, selected index defaults to -1 (nothing selected)

     @param index The index of the selected card
     */

    public void setSelectedIndex(int index) {
        if (index >= 0 && index < HAND_SIZE)
            selectedIndex = index;
        else
            selectedIndex = -1;
    }
}
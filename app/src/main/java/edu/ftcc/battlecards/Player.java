/**
 This class represents a player in the game
 WEB 251 0001 - Battle Cards
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.Random;

public class Player {
    // Fields
    public static final int HAND_SIZE = 3;

    private Card[] hand;
    private Deck deck;
    private int gold, numInHand, resources;

    /**
     Constructor
     */

    public Player() {
        hand = new Card[HAND_SIZE];
        deck = new Deck();
        gold = 100;
        numInHand = 0;
        resources = 30;
    }

    /**
        The addToHand method adds a card to the player's hand at the first available space
        The method returns the index of the location in the hand the card was placed

        @param card The card to add
        @return The index of where the card was placed
     */

    public int addToHand(Card card) {
        assert(numInHand != 3);
        int index = -1;
        boolean placedInHand = false;
        for (int i = 0; i < HAND_SIZE && !placedInHand; i++)
            if (hand[i] == null) {
                hand[i] = card;
                placedInHand = true;
                numInHand++;
                index = i;
            }
        return index;
    }

    /**
        The autoBuy method automatically buys cards to add to the player's deck
     */

    public void autoBuy() {
        // Get a reference to the game instance
        Game game = Game.getInstance();

        // For random
        Random rng = new Random();

        // Buy cards until max deck size is reached
        int maxCards = deck.getMaxSize();
        while (deck.getNumCards() != maxCards) {
            Card[] buyable = game.getCardsAvailableToBuy(gold);
            do {
                // Buy card
                buyCard(buyable[rng.nextInt(buyable.length)]);

                // Refresh set of buyable cards
                buyable = game.getCardsAvailableToBuy(gold);
            } while (buyable.length != 0);

            if (deck.getNumCards() != 15)
            {
                deck.clear();
                gold = 100;
            }
        }
    }

    /**
     The buyCard method purchases the specified card with the player's gold
     and adds the card to the player's deck

     @param toBuy The card to purchase
     */

    public void buyCard(Card toBuy) {
        gold -= toBuy.getGoldCost();
        deck.push(toBuy);
    }

    /**
     The cast method simulates the player casting the card specified
     and updates the player's resources appropriately

     @param toCast The card to cast
     */

    public void cast(Card toCast) {
        resources -= toCast.getResourceCost();
    }

    /**
        The getCard method returns the first instance of a
        card in the player's deck with the specified name

        @param name The name of the card to get
     */

    public Card getCard(String name) {
        return deck.getCard(name);
    }

    /**
        The getCards method returns the cards in the player's deck as an array

        @return An array of the player's cards
     */

    public Card[] getCards() {
        return deck.toArray();
    }

    /**
     The getCardNames method returns the name of every card in the player's deck

     @return The name of every card in the player's deck
     */

    public String[] getCardNames() {
        return deck.getCardNames();
    }

    /**
        The getDeckSize method returns the number of cards currently in the player's deck

        @return The number of cards in the player's deck
     */

    public int getDeckSize() {
        return deck.getNumCards();
    }

    /**
     The sellCard method sells the specified card from the player's deck

     @param toSell The card to sell
     */

    public void sellCard(Card toSell) {
        gold += toSell.getGoldCost();
        deck.remove(toSell);
    }

    /**
        The shuffleDeck method shuffles the player's deck
     */

    public void shuffleDeck() {
        deck.shuffle();
    }

    /**
     The draw method removes and returns the card from the top of the
     player's deck

     @return The card from the top of the player's deck
     */

    public Card draw() {
        return deck.pop();
    }

    /**
        The removeFromHand method removes and returns the card at the specified index
        from the player's hand

        @param index The index of the card in the player's hand to remove
        @return card The card from the player's hand
     */

    public Card removeFromHand(int index) {
        assert(index >= 0 && index < HAND_SIZE && numInHand != 0);
        Card card = hand[index];
        hand[index] = null;
        return card;
    }

    /**
     The replenishResources method adds the specified number of
     resources to the player's current resource pool

     @param toReplenish The number of resources to replenish
     */

    public void replenishResources(int toReplenish) {
        resources += toReplenish;
    }

    /**
     The getGold method returns the current amount of gold that the player has

     @return The current amount of gold that the player has
     */

    public int getGold() {
        return gold;
    }

    /**
        NumInHand Getter

        @return The number of cards in the player's hands
     */

    public int getNumInHand() {
        return numInHand;
    }

    /**
        The getResources method returns the current amount of resources that the player has

        @return The current amount of resources that the player has
     */

    public int getResources() {
        return resources;
    }
}

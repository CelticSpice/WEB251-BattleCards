/**
 This class represents a player in the game
 9/23/2016
 WEB 251 0001 - Battle Cards (M5HW1)
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

public class Player {
    // Fields
    private Deck deck;
    private int gold, resources;

    /**
     Constructor
     */

    public Player() {
        deck = new Deck();
        gold = 100;
        resources = 30;
    }

    /**
     The buyCard method purchases the specified Card with the Player's gold
     and adds the Card to the Player's Deck

     @param toBuy The Card to purchase
     */

    public void buyCard(Card toBuy) {
        gold -= toBuy.getGoldCost();
        deck.push(toBuy);
    }

    /**
        The getDeck method returns a reference to the Player's deck

        @return A reference to the Player's deck
     */

    public Deck getDeck() { return deck; }

    /**
     The sellCard method sells the specified Card from the Player's Deck

     @param toSell The Card to sell
     */

    public void sellCard(Card toSell) {
        gold += toSell.getGoldCost();
        deck.remove(toSell);
    }

    /**
     The draw method removes and returns the Card from the top of the
     Player's Deck

     @return The Card from the top of the Player's Deck
     */

    public Card draw() {
        return deck.pop();
    }

    /**
     The cast method simulates the Player casting the Card specified
     and updates the Player's resources appropriately

     @param toCast The Card to cast
     */

    public void cast(Card toCast) {
        resources -= toCast.getResourceCost();
    }

    /**
     The replenishResources method adds the specified number of
     resources to the Player's current resources

     @param toReplenish The number of resources to replenish
     */

    public void replenishResources(int toReplenish) {
        resources += toReplenish;
    }

    /**
     The getGold method returns the current amount of the gold that the Player has

     @return The current amount of gold that the Player has
     */

    public int getGold() {
        return gold;
    }
}

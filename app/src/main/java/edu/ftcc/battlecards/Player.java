/**
    This class represents a player in the game

    @author James Alves, Timothy Burns
*/

package edu.ftcc.battlecards;

public class Player {
    // Fields
    private Deck deck;
    private int gold, hp, resources;

    /**
        Constructor
    */

    public Player() {
        deck = new Deck();
        gold = 100;
        hp = 30;
        resources = 30;
    }

    /**
        The buyCard method purchases the specified card with the player's gold
        and adds the card to the player's deck

        @param cardToBuy The card to purchase
    */

    public void buyCard(Card cardToBuy) {
        gold -= cardToBuy.getResourceCost();
        deck.push(cardToBuy);
    }

    /**
        The sellCard method sells the specified card from the player's deck

        @param cardToSell The card to sell
    */

    public void sellCard(Card cardToSell) {
        gold += cardToSell.getResourceCost();
        deck.remove(cardToSell);
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
        The cast method simulates the player casting the card specified
        and updates the player's resources appropriately

        @param cardToCast The card to cast
    */

    public void cast(Card cardToCast) {
        cardToCast.beCast();
        resources -= cardToCast.getResourceCost();
    }

    /**
        The replenishResources method adds the specified number of
        resources to the player's current resources

        @param numResToReplenish The number of resources to replenish
    */

    public void replenishResources(int numResToReplenish) {
        resources += numResToReplenish;
    }

    /**
        The takeDamage method simulates the player taking the specified
        amount of damage

        @param damage The number of damage to take
    */

    public void takeDamage(int damage) {
        hp -= damage;

        // Ensure clean 0
        if (hp < 0) {
            hp = 0;
        }
    }

    /**
        The getGold method returns the current amount of the gold that the player has

        @return The current amount of gold that the player has
     */

    public int getGold() {
        return gold;
    }
}

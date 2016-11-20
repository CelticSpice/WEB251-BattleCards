/**
 This class represents a card in the game
 WEB 251 0001 - Battle Cards
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

public class Card {
    // Fields
    private Ability ability;
    private boolean active, canAttack;
    private int attack, defense, goldCost, resourceCost;
    private Integer image;
    private String name;

    /**
     Constructor
     Accepts the card's properties

     @param atk The card's attack
     @param def The card's defense
     @param abil The card's ability
     @param gCost The card's gold cost
     @param rCost The card's resource cost
     @param img Integer resource for card's image
     @param n The card's name
     */

    public Card(int atk, int def, Ability abil, int gCost,
                int rCost, Integer img, String n) {
        active = false;
        canAttack = false;
        attack = atk;
        defense = def;
        ability = abil;
        goldCost = gCost;
        resourceCost = rCost;
        image = img;
        name = n;
    }

    /**
     Copy Constructor

     @param card The card to make a copy of
     */

    public Card(Card card) {
        active = card.active;
        canAttack = card.canAttack;
        attack = card.attack;
        defense = card.defense;
        ability = card.ability;
        goldCost = card.goldCost;
        resourceCost = card.resourceCost;
        image = card.image;
        name = card.name;
    }

    /**
        The alterDefense method decreases the card's defense by the specified value

        @param value The value to decrease the card's defense by
     */

    public void alterDefense(int value) {
        defense -= value;
    }

    /**
        The getActive method returns whether the card is active

        @return Whether the card is active
     */

    public boolean getActive() {
        return active;
    }

    /**
        The getCanAttack method returns whether the card can be used to attack

        @return Whether the card can attack
     */

    public boolean getCanAttack() {
        return canAttack;
    }

    /**
     The getAttack method returns the card's attack

     @return The card's attack
     */

    public int getAttack() {
        return attack;
    }

    /**
     The getAbility method returns the card's ability

     @return The card's ability
     */

    public Ability getAbility() {
        return ability;
    }

    /**
     The getDefense method returns the card's defense

     @return The card's defense
     */

    public int getDefense() {
        return defense;
    }

    /**
     The getGoldCost method returns the card's gold cost

     @return The card's gold cost
     */

    public int getGoldCost() {
        return goldCost;
    }

    /**
        The getImageResID method returns the Integer of the card's
        resource ID of its image

        @return Integer resource for card's image
     */

    public Integer getImageResID() { return image; }

    /**
     The getName method returns the name of the card

     @return The name of the card
     */

    public String getName() {
        return name;
    }

    /**
     The getResourceCost method returns the card's resource cost

     @return The card's resource cost
     */

    public int getResourceCost() {
        return resourceCost;
    }

    /**
        The setActive method sets the card's active status

        @param isActive Whether the card is active
     */

    public void setActive(boolean isActive) {
        active = isActive;
    }

    /**
        The setCanAttack method sets whether the card can attack

        @param attack Whether the can attack
     */

    public void setCanAttack(boolean attack) {
        canAttack = attack;
    }
}
/**
 This class represents a card in the game
 WEB 251 0001 - Battle Cards
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

public class Card {
    // Fields
    private Ability ability;
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
        attack = card.attack;
        defense = card.defense;
        ability = card.ability;
        goldCost = card.goldCost;
        resourceCost = card.resourceCost;
        image = card.image;
        name = card.name;
    }

    /**
        The alterDefense method increases or decreases the card's defense
        by an amount

        @param value The value to increase or decrease the card's defense by
     */

    public void alterDefense(int value) {
        defense += value;
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
}
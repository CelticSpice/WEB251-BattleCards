/**
 This class represents a card in the game
 9/23/2016
 WEB 251 0001 - Battle Cards (M5HW1)
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

public class Card {
    // Fields
    private boolean active;
    private CombatAbility ability;
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

    public Card(int atk, int def, CombatAbility abil, int gCost,
                int rCost, Integer img, String n) {
        attack = atk;
        defense = def;
        ability = abil;
        goldCost = gCost;
        resourceCost = rCost;
        image = img;
        name = n;
        active = false;
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
        active = card.active;
    }

    /**
     The activate method simulates the Card being activated. It sets the
     active property to true
     */

    public void activate() {
        active = true;
    }

    /**
     The getAttack method returns the Card's attack

     @return The Card's attack
     */

    public int getAttack() {
        return attack;
    }

    /**
     The getCombatAbility method returns the Card's combat ability

     @return The Card's combat ability
     */

    public CombatAbility getCombatAbility() {
        return ability;
    }

    /**
     The getDefense method returns the Card's defense

     @return The Card's defense
     */

    public int getDefense() {
        return defense;
    }

    /**
     The getGoldCost method returns the Card's gold cost

     @return The Card's gold cost
     */

    public int getGoldCost() {
        return goldCost;
    }

    /**
     The getIfActive method returns the active status of the Card

     @return Whether or not the Card is active
     */

    public boolean getIfActive() {
        return active;
    }

    /**
        The getImage method returns the Integer pointing to
        the Card's image

        @return Integer pointing to the Card's image
     */

    public Integer getImage() { return image; }

    /**
     The getName method returns the name of the Card

     @return The name of the card
     */

    public String getName() {
        return name;
    }

    /**
     The getResourceCost method returns the Card's resource cost

     @return The Card's resource cost
     */

    public int getResourceCost() {
        return resourceCost;
    }

    /**
     The setDefense method sets the Card's defense

     @param defenseNum The Card's defense
     */

    public void setDefense(int defenseNum) {
        defense = defenseNum;
    }
}
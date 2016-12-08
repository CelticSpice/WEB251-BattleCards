/**
 This class represents a card
 12/8/2016
 WEB 251 0001 - M5PROJ
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.Random;

public class Card {
    // Fields
    private Ability ability;
    private boolean canAttack;
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
     The attack method has the card attack another card

     @param cardToAttack The card to attack
     */

    public void attack(Card cardToAttack) {
        Random rng = new Random();
        cardToAttack.defense -= rng.nextInt(attack + 1);
    }

    /**
     The getAbility method returns the card's ability

     @return The card's ability
     */

    public Ability getAbility() {
        return ability;
    }

    /**
     The getAttack method returns the card's attack

     @return The card's attack
     */

    public int getAttack() {
        return attack;
    }

    /**
    The getCanAttack method returns whether the card can attack

    @return Whether the card can attack
     */

    public boolean getCanAttack() {
        return canAttack;
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
     The setCanAttack method sets whether the card can attack

     @param attack Whether the can attack
     */

    public void setCanAttack(boolean attack) {
        canAttack = attack;
    }
}
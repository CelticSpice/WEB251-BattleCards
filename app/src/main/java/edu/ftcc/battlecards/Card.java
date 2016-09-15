/**
 This class represents a card in the game

 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

public class Card {
    /*
        If there shall be no combat ability, "None" should be set in the
        combatAbility field
    */

    // Fields
    private boolean active, cast;
    private int attackNumber, defenseNumber, goldCost, resourceCost;
    private String cardName, combatAbility;

    /**
     Constructor
     Defines the card's name, attack number, defense number, gold cost,
     resource cost, and attack ability, if any. If the card shall have no attack
     ability, "None" should be passed

     @param name The card's name
     @param attackNum The attack number
     @param defenseNum The defense number
     @param gCost The gold cost
     @param rCost The resource cost
     @param ability The combat ability, if any
     */

    public Card(String name, int attackNum, int defenseNum,
                int gCost, int rCost, String ability) {

        cardName = name;
        attackNumber = attackNum;
        defenseNumber = defenseNum;
        goldCost = gCost;
        resourceCost = rCost;
        combatAbility = ability;
        active = false;
        cast = false;
    }

    /**
     The activate method simulates the card being activated. It sets the
     active field to true
     */

    public void activate() {
        active = true;
    }

    /**
     The beCast method simulates the card being cast. It sets the cast
     field to true
     */

    public void beCast() {
        cast = true;
    }

    /**
     The getActive method returns the active status of the card

     @return Whether or not the card is active
     */

    public boolean getActive() {
        return active;
    }

    /**
     The getCast method returns whether or not the card has been cast.

     @return Whether the card has been cast
     */

    public boolean getCast() {
        return cast;
    }

    /**
     The getCardName method returns the name of the card

     @return The name of the card
     */

    public String getCardName() {
        return cardName;
    }

    /**
     The getAttackNumber method returns the card's attack number

     @return The card's attack number
     */

    public int getAttackNumber() {
        return attackNumber;
    }

    /**
     The getDefenseNumber method returns the card's defense number

     @return The card's defense number
     */

    public int getDefenseNumber() {
        return defenseNumber;
    }

    /**
     The setDefenseNumber method sets the card's defense number

     @param defense The card's defense number
     */

    public void setDefenseNumber(int defense) {
        defenseNumber = defense;
    }

    /**
     The getGoldCost method returns the card's gold cost

     @return The card's gold cost
     */

    public int getGoldCost() {
        return goldCost;
    }

    /**
     The getResourceCost method returns the cards resource cost

     @return The card's resource cost
     */

    public int getResourceCost() {
        return resourceCost;
    }

    /**
     The getCombatAbility method returns the card's combat ability

     @return The card's combat ability
     */

    public String getCombatAbility() {
        return combatAbility;
    }
}
/**
 This class represents a CardView in the game

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
     Defines the CardView's name, attack number, defense number, gold cost,
     resource cost, and attack ability, if any. If the CardView shall have no attack
     ability, "None" should be passed

     @param name The CardView's name
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
     The activate method simulates the CardView being activated. It sets the
     active field to true
     */

    public void activate() {
        active = true;
    }

    /**
     The beCast method simulates the CardView being cast. It sets the cast
     field to true
     */

    public void beCast() {
        cast = true;
    }

    /**
     The getActive method returns the active status of the CardView

     @return Whether or not the CardView is active
     */

    public boolean getActive() {
        return active;
    }

    /**
     The getCast method returns whether or not the CardView has been cast.

     @return Whether the CardView has been cast
     */

    public boolean getCast() {
        return cast;
    }

    /**
     The getCardName method returns the name of the CardView

     @return The name of the CardView
     */

    public String getCardName() {
        return cardName;
    }

    /**
     The getAttackNumber method returns the CardView's attack number

     @return The CardView's attack number
     */

    public int getAttackNumber() {
        return attackNumber;
    }

    /**
     The getDefenseNumber method returns the CardView's defense number

     @return The CardView's defense number
     */

    public int getDefenseNumber() {
        return defenseNumber;
    }

    /**
     The setDefenseNumber method sets the CardView's defense number

     @param defense The CardView's defense number
     */

    public void setDefenseNumber(int defense) {
        defenseNumber = defense;
    }

    /**
     The getGoldCost method returns the CardView's gold cost

     @return The CardView's gold cost
     */

    public int getGoldCost() {
        return goldCost;
    }

    /**
     The getResourceCost method returns the cards resource cost

     @return The CardView's resource cost
     */

    public int getResourceCost() {
        return resourceCost;
    }

    /**
     The getCombatAbility method returns the CardView's combat ability

     @return The CardView's combat ability
     */

    public String getCombatAbility() {
        return combatAbility;
    }
}
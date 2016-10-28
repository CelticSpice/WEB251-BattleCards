/**
 This class represents a card in the game
 9/23/2016
 WEB 251 0001 - Battle Cards (M5HW1)
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

public class Card {
    // Fields
    private CardProperties properties;

    /**
     Constructor
     Accepts a CardProperties defining the Card's properties

     @param props The Card's properties
     */

    public Card(CardProperties props) {
        properties = new CardProperties(props);
    }

    /**
     Copy Constructor

     @param card The Card to make a copy of
     */

    public Card(Card card) {
        properties = new CardProperties(card.properties);
    }

    /**
     The activate method simulates the Card being activated. It sets the
     active property to true
     */

    public void activate() {
        properties.active = true;
    }

    /**
     The getAttack method returns the Card's attack

     @return The Card's attack
     */

    public int getAttack() {
        return properties.attack;
    }

    /**
     The getCombatAbility method returns the Card's combat ability

     @return The Card's combat ability
     */

    public CombatAbility getCombatAbility() {
        return properties.ability;
    }

    /**
     The getDefense method returns the Card's defense

     @return The Card's defense
     */

    public int getDefense() {
        return properties.defense;
    }

    /**
     The getGoldCost method returns the Card's gold cost

     @return The Card's gold cost
     */

    public int getGoldCost() {
        return properties.goldCost;
    }

    /**
     The getIfActive method returns the active status of the Card

     @return Whether or not the Card is active
     */

    public boolean getIfActive() {
        return properties.active;
    }

    /**
        The getImage method returns the Integer pointing to
        the Card's image

        @return Integer pointing to the Card's image
     */

    public Integer getImage() { return properties.image; }

    /**
     The getName method returns the name of the Card

     @return The name of the card
     */

    public String getName() {
        return properties.name;
    }

    /**
     The getResourceCost method returns the Card's resource cost

     @return The Card's resource cost
     */

    public int getResourceCost() {
        return properties.resourceCost;
    }

    /**
     The setDefense method sets the Card's defense

     @param defenseNum The Card's defense
     */

    public void setDefense(int defenseNum) {
        properties.defense = defenseNum;
    }
}
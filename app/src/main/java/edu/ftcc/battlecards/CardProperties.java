/**
    This class holds the various properties of a Card
    WEB 251 0001
    @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

public class CardProperties {
    public boolean active;
    public CombatAbility ability;
    public int attack, defense, goldCost, resourceCost;
    public Integer image;
    public String name;

    /**
        Constructor
     */

    public CardProperties()
    {
        active = false;
        ability = CombatAbility.NONE;
        attack = 0;
        defense = 0;
        goldCost = 0;
        image = 0;
        resourceCost = 0;
        name = null;
    }

    /**
        Copy Constructor

        @param props The CardProperties to replicate
     */

    public CardProperties(CardProperties props) {
        active = props.active;
        ability = props.ability;
        attack = props.attack;
        defense = props.defense;
        goldCost = props.goldCost;
        image = props.image;
        resourceCost = props.resourceCost;
        name = props.name;
    }
}
/**
 Contains information about an attack
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

public class Attack {
    // Fields
    private Card attacker, defender;
    private int attackerI, defenderI;

    /**
     Constructor - Accepts the attacking and defending cards and their respective indices on the
     battlefield

     @param attk The attacker
     @param def The defender
     @param attkI The attacker's index
     @param defI The defender's index
     */

    public Attack(Card attk, Card def, int attkI, int defI) {
        attacker = attk;
        defender = def;
        attackerI = attkI;
        defenderI = defI;
    }

    /**
     GetAttacker - Returns the attacker

     @return The attacker
     */

    public Card getAttacker() {
        return attacker;
    }

    /**
     GetDefender - Returns the defender

     @return The defender
     */

    public Card getDefender() {
        return defender;
    }

    /**
     GetAttackerIndex - Returns the attacker's index

     @return The attacker's index
     */

    public int getAttackerIndex() {
        return attackerI;
    }

    /**
     GetDefenderIndex - Returns the defender's index

     @return The defender's index
     */

    public int getDefenderIndex() {
        return defenderI;
    }
}
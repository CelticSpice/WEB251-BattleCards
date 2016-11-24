/**
 Contains information about a turn taken
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.ArrayList;

public class TurnResult {
    // Fields
    private boolean didDraw, didCast, didAttack;
    private Draw draw;
    private Cast cast;
    private ArrayList<Attack> attacks;

    /**
     Constructor
     */

    public TurnResult() {
        didDraw = false;
        didCast = false;
        didAttack = false;
        draw = null;
        cast = null;
        attacks = new ArrayList<>();
    }

    /**
     AddAttackInfo - Adds information about an attack made

     @param a The attack
     */

    public void addAttackInfo(Attack a) {
        if (!didAttack)
            didAttack = true;
        attacks.add(a);
    }

    /**
     GetAttacks - Returns an array of attacks made

     @return The attacks made
     */

    public Attack[] getAttacks() {
        return attacks.toArray(new Attack[attacks.size()]);
    }

    /**
     GetCast - Returns the cast

     @return The cast
     */

    public Cast getCast() {
        return cast;
    }

    /**
     GetDraw - Returns the draw

     @return The draw made
     */

    public Draw getDraw() {
        return draw;
    }

    /**
     GetDidAttack - Returns whether attacks are made

     @return Whether attacks are made
     */

    public boolean getDidAttack() {
        return didAttack;
    }

    /**
     GetDidCast - Returns whether a cast is made

     @return Whether a cast is made
     */

    public boolean getDidCast() {
        return didCast;
    }

    /**
     GetDidDraw - Returns whether a draw is made

     @return Whether a draw is made
     */

    public boolean getDidDraw() {
        return didDraw;
    }

    /**
     SetCast - Sets information about a cast made

     @param c The cast
     */

    public void setCast(Cast c) {
        cast = c;
        didCast = true;
    }

    /**
     SetDraw - Sets information about a draw made

     @param d The draw
     */

    public void setDraw(Draw d) {
        draw = d;
        didDraw = true;
    }
}
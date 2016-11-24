/**
 Contains information about a cast
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

public class Cast {
    // Fields
    private Card cardCast;
    private int handIndex, battlefieldIndex;

    /**
     Constructor - Accepts the caster, the card cast, and the indices of the hand and battlefield
     the card is cast from and to

     @param card Card cast
     @param handI Index of hand card cast from
     @param fieldI Index of battlefield card cast to
     */

    public Cast(Card card, int handI, int fieldI) {
        cardCast = card;
        handIndex = handI;
        battlefieldIndex = fieldI;
    }

    /**
     GetCardCast - Returns the card cast

     @return The card cast
     */

    public Card getCardCast() {
        return cardCast;
    }

    /**
     GetHandIndex - Returns the index from the hand the card was cast from

     @return The hand index
     */

    public int getHandIndex() {
        return handIndex;
    }

    /**
     GetBattlefieldIndex - Returns the index on the battlefield the card was cast into

     @return The battlefield index
     */

    public int getBattlefieldIndex() {
        return battlefieldIndex;
    }
}
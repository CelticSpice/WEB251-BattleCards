/**
 Contains information about a drawing
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

public class Draw {
    // Fields
    private Card cardDrawn;
    private int index;

    /**
     Constructor - Accepts the card that is drawn and the index into which it is placed

     @param card The card drawn
     @param i The index
     */

    public Draw(Card card, int i) {
        cardDrawn = card;
        index = i;
    }

    /**
     GetCardDrawn - Returns the card that is drawn

     @return The drawn card
     */

    public Card getCardDrawn() {
        return cardDrawn;
    }

    /**
     GetIndex - Returns the index into which the card is placed

     @return The index
     */

    public int getIndex() {
        return index;
    }
}
/**
 Contains information about a drawing
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

public class Draw {
    // Fields
    private boolean drawSuccessful;
    private Card cardDrawn;
    private int index;

    /**
     Constructor - Accepts the card that is drawn and the index into which it is placed,
     and whether draw is successful

     @param success Whether the draw is a success
     @param card The card drawn
     @param i The index
     */

    public Draw(boolean success, Card card, int i) {
        drawSuccessful = success;
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
     GetDrawSuccessful - Returns whether the draw is successful

     @return Whether the draw is successful
     */

    public boolean getDrawSuccessful() {
        return drawSuccessful;
    }

    /**
     GetIndex - Returns the index into which the card is placed

     @return The index
     */

    public int getIndex() {
        return index;
    }
}
/**
    This class represents the battlefield
 */

package edu.ftcc.battlecards;

public class Battlefield {
    // Fields
    private int FIELD_SIZE = 2;

    private Card[] humanCards;
    private Card[] computerCards;
    private int humanSelectedIndex, computerSelectedIndex;

    /**
        Constructor
     */

    public Battlefield() {
        humanCards = new Card[FIELD_SIZE];
        computerCards = new Card[FIELD_SIZE];
        humanSelectedIndex = -1;
        computerSelectedIndex = -1;
    }

    /**
        The canCardsAttack method returns whether any of the specified player's cards can attack

        @param player The player to check if cards can attack for
        @return canAttack Whether any of the specified player's cards can attack
     */

    public boolean canCardsAttack(PlayerType player) {
        boolean canAttack = false;
        for (Card c : humanCards)
            if (c.getCanAttack())
                canAttack = true;
        return canAttack;
    }

    /**
        The getComputerCardAt method returns the card at the specified index of the computer's field

        @param index The index of the computer's field
        @return The card at the specified index
     */

    public Card getComputerCardAt(int index) {
        return computerCards[index];
    }

    /**
        The getComputerSelectedIndexMethod gets the selected index of a computer's card

        @return The selected index of a computer's card
     */

    public int getComputerSelectedIndex() {
        return computerSelectedIndex;
    }

    /**
        The getFieldSize method returns the field size for both players

        @return The size of the field for each player
     */

    public int getFieldSize() {
        return FIELD_SIZE;
    }

    /**
        The getHumanCardAt method returns the card at the specified index of the human's field

        @param index The index of the human's field
        @return The card at the specified index
     */

    public Card getHumanCardAt(int index) {
        return humanCards[index];
    }

    /**
        The getHumanSelectedIndexMethod gets the selected index of a human's card

        @return The selected index of a human's card
     */

    public int getHumanSelectedIndex() {
        return humanSelectedIndex;
    }

    /**
        The isFieldEmpty method returns whether the specified player's field is empty

        @param player The player to check if field of is empty
        @return empty Whether the player's field is empty
     */

    public boolean isFieldEmpty(PlayerType player) {
        boolean empty = true;
        if (player == PlayerType.HUMAN) {
            for (Card card : humanCards)
                if (card != null)
                    empty = false;
        }
        else {
            for (Card card : computerCards)
                if (card != null)
                    empty = false;
        }
        return empty;
    }

    /**
        The placeCard method places the specified card at the specified index of the specified
        player's field

        @param card Card to place
        @param index Index of where to place the card
        @param player The player placing the card
     */

    public void placeCard(Card card, int index, PlayerType player) {
        if (player == PlayerType.HUMAN)
            humanCards[index] = card;
        else
            computerCards[index] = card;
    }

    /**
        The removeCard method removes the card at the specified index of the specified player's
        field

        @param index The index of the player's field to remove the card from
        @param player The player's field to remove a card from
     */

    public void removeCard(int index, PlayerType player) {
        if (player == PlayerType.HUMAN)
            humanCards[index] = null;
        else
            computerCards[index] = null;
    }

    /**
        The resetCanAttack method sets the cards on the field of the specified player to be able
        to attack

        @param player The player whose field cards will have their attack reset
     */

    public void resetCanAttack(PlayerType player) {
        if (player == PlayerType.HUMAN) {
            for (Card card : humanCards)
                if (card != null)
                    card.setCanAttack(true);
        }
        else {
            for (Card card : computerCards)
                if (card != null)
                    card.setCanAttack(true);
        }
    }

    /**
        The setComputerSelectedIndex method sets the selected index of a computer's card

        @param index The selected index to set
     */

    public void setComputerSelectedIndex(int index) {
        computerSelectedIndex = index;
    }

    /**
        The setHumanSelectedIndex method sets the selected index of a human's card

        @param index The selected index to set
     */

    public void setHumanSelectedIndex(int index) {
        humanSelectedIndex = index;
    }
}

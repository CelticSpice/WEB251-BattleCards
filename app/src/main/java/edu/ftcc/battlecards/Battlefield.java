/**
 This class represents the battlefield
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.ArrayList;
import java.util.Random;

public class Battlefield {
    // Fields
    private final int FIELD_SIZE = 2;

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
     AutoSelectCardToAttack - Automatically sets the selected index of the specified
     player's field to a card that can be attacked

     @param player The player type whose field to select a card to attack from
     */

    public void autoSelectCardToAttack(PlayerType player) {
        Random rng = new Random();
        if (player == PlayerType.HUMAN) {
            ArrayList<Integer> indices = new ArrayList<>();
            for (int i = 0; i < FIELD_SIZE; i++)
                if (computerCards[i] != null)
                    indices.add(i);
            computerSelectedIndex = indices.get(rng.nextInt(indices.size()));
        }
        else {
            ArrayList<Integer> indices = new ArrayList<>();
            for (int i = 0; i < FIELD_SIZE; i++)
                if (humanCards[i] != null)
                    indices.add(i);
            humanSelectedIndex = indices.get(rng.nextInt(indices.size()));
        }
    }

    /**
     AutoSelectCardToAttackWith - Automatically sets the selected index of the specified player's
     field to a card that can attack

     @param player The player type whose field to select a card to attack with from
     */

    public void autoSelectCardToAttackWith(PlayerType player) {
        Random rng = new Random();
        if (player == PlayerType.HUMAN) {
            ArrayList<Integer> indices = new ArrayList<>();
            for (int i = 0; i < FIELD_SIZE; i++)
                if (humanCards[i] != null && humanCards[i].getCanAttack())
                    indices.add(i);
            humanSelectedIndex = indices.get(rng.nextInt(indices.size()));
        }
        else {
            ArrayList<Integer> indices = new ArrayList<>();
            for (int i = 0; i < FIELD_SIZE; i++)
                if (computerCards[i] != null && computerCards[i].getCanAttack())
                    indices.add(i);
            computerSelectedIndex = indices.get(rng.nextInt(indices.size()));
        }
    }

    /**
     AutoSelectCastLocation - Automatically sets the selected index of the specified player's field
     to a location to cast a card into

     @param player Player type whose field to select a location to cast a card into
     */

    public void autoSelectCastLocation(PlayerType player) {
        if (player == PlayerType.HUMAN) {
            int index = -1;
            for (int i = 0; i < humanCards.length; i++)
                if (humanCards[i] == null && index != 0)
                    index = i;
            humanSelectedIndex = index;
        }
        else {
            int index = -1;
            for (int i = 0; i < computerCards.length; i++)
                if (computerCards[i] == null && index != 0)
                    index = i;
            computerSelectedIndex = index;
        }
    }

    /**
     CanCardAttack - Returns whether the card at the index of the specified player's field can
     attack

     @param index The index of the card
     @param player The player whose field the card is in
     @return canAttack Whether the card can attack
     */

    public boolean canCardAttack(int index, PlayerType player) {
        boolean canAttack;
        if (player == PlayerType.HUMAN)
            canAttack = humanCards[index].getCanAttack();
        else
            canAttack = computerCards[index].getCanAttack();
        return canAttack;
    }

    /**
     The canCardsAttack method returns whether any of the specified player's cards can attack

     @param player The player to check if cards can attack for
     @return canAttack Whether any of the specified player's cards can attack
     */

    public boolean canCardsAttack(PlayerType player) {
        boolean canAttack = false;
        if (player == PlayerType.HUMAN) {
            for (Card card : humanCards)
                if (card != null && card.getCanAttack())
                    canAttack = true;
        }
        else {
            for (Card card : computerCards)
                if (card != null && card.getCanAttack())
                    canAttack = true;
        }
        return canAttack;
    }

    /**
     The getFieldSize method returns the field size for both players

     @return The size of the field for each player
     */

    public int getFieldSize() {
        return FIELD_SIZE;
    }

    /**
     The getSelectedCard method returns the card pointed to by the specified player's field
     selected index

     @param player The player to get the selected card of its field's selected index of
     @return card The card selected
     */

    public Card getSelectedCard(PlayerType player) {
        Card card;
        if (player == PlayerType.HUMAN)
            card = humanCards[humanSelectedIndex];
        else
            card = computerCards[computerSelectedIndex];
        return card;
    }

    /**
     The getSelectedIndex method returns the selected index of the specified player's field

     @param player The player to get the selected index of field of
     @return The selected index of the specified player's field
     */

    public int getSelectedIndex(PlayerType player) {
        return (player == PlayerType.HUMAN) ? humanSelectedIndex : computerSelectedIndex;
    }

    /**
     IsCardAt - Returns whether a card exists at the specified index of the specified player's
     field

     @param index The index
     @param player Player whose field to examine
     @return exists Whether a card exists at the index of the specified player's field
     */

    public boolean isCardAt(int index, PlayerType player) {
        boolean exists = false;
        if (player == PlayerType.HUMAN)
            if (humanCards[index] != null)
                exists = true;
        else
            if (computerCards[index] != null)
                exists = true;
        return exists;
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
     The isFieldFull method returns whether the specified player's field is full

     @param player The player to check if field of is full
     @return full Whether the player's field is full
     */

    public boolean isFieldFull(PlayerType player) {
        boolean full = true;
        if (player == PlayerType.HUMAN) {
            for (Card card : humanCards)
                if (card == null)
                    full = false;
        }
        else {
            for (Card card : computerCards)
                if (card == null)
                    full = false;
        }
        return full;
    }

    /**
     PlaceCard - Places a card at the selected index of the specified player's field

     @param card Card to place
     @param player The player on whose field to place the card
     */

    public void placeCard(Card card, PlayerType player) {
        if (player == PlayerType.HUMAN) {
            humanCards[humanSelectedIndex] = card;
            humanSelectedIndex = -1;
        }
        else {
            computerCards[computerSelectedIndex] = card;
            computerSelectedIndex = -1;
        }
    }

    /**
     The removeSelectedCard method removes the card pointed to by the selected index of the field
     of the specified player

     @param player The player, the field of which the field's selected card will be removed
     */

    public void removeSelectedCard(PlayerType player) {
        if (player == PlayerType.HUMAN)
            humanCards[humanSelectedIndex] = null;
        else
            computerCards[computerSelectedIndex] = null;
    }

    /**
     The resetCanAttack method sets all of the cards can attack status to true
     */

    public void resetCanAttack() {
        for (Card card : humanCards)
            if (card != null)
                card.setCanAttack(true);
        for (Card card : computerCards)
            if (card != null)
                card.setCanAttack(true);
    }

    /**
     The setSelectedIndex method sets the selected index of the specified player's field

     @param index The index to set
     @param player The player's whose field's selected index will be set
     */

    public void setSelectedIndex(int index, PlayerType player) {
        if (player == PlayerType.HUMAN)
            humanSelectedIndex = index;
        else
            computerSelectedIndex = index;
    }
}

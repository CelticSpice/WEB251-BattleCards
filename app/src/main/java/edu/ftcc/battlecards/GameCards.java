/**
 This class contains information about all the cards in the game
 9/23/2016
 WEB 251 0001 - Battle Cards (M5HW1)
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.ArrayList;

public class GameCards {
    private Card[] cards;

    /**
     The getCard method returns the Card with the passed name

     @param name The name of the Card to get
     @return card The Card with the specified name; else, null
     */

    public Card getCard(String name) {
        Card card = null;
        for (int i = 0; i < cards.length && card == null; i++)
            if (cards[i].getName().equalsIgnoreCase(name))
                card = new Card(cards[i]);
        return card;
    }

    /**
     The getCard method returns the Card with the passed index

     @param index Index of Card to get
     @return The Card specified by the passed index
     */

    public Card getCard(int index) {
        return new Card(cards[index]);
    }

    /**
        The getCardIndex method returns the index of the Card with the
        specified name in the class' field

        @param name The name of the Card to get the index of
        @return index The index of the Card with the specified name
     */

    public int getCardIndex(String name) {
        int index = -1;
        for (int i = 0; i < cards.length && index == -1; i++)
            if (cards[i].getName().equalsIgnoreCase(name))
                index = i;
        return index;
    }

    /**
        The getCards method returns every Card in the game

        @return Every card in the game
     */

    public Card[] getCards() {
        Card[] toReturn = new Card[cards.length];
        for (int i = 0; i < toReturn.length; i++)
            toReturn[i] = new Card(cards[i]);
        return toReturn;
    }

    /**
     The InitGameCards method initializes the class field with information about
     the game's cards.

     @param gameCards The Cards available in the game
     */

    public void InitGameCards(Card[] gameCards) {
        cards = new Card[gameCards.length];
        for (int i = 0; i < gameCards.length; i++)
            cards[i] = new Card(gameCards[i]);
    }
}

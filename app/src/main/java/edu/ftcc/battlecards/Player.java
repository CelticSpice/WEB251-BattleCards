/**
 This class represents a player in the game
 12/8/2016
 WEB 251 0001 - M5PROJ
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.Random;

public class Player {
    // Fields
    private Battlefield battlefield;
    private Hand hand;
    private Deck deck;
    private int gold, resources;
    private PlayerType type;

    /**
     Constructor - Accepts the type of player

     @param playerType Whether the player is a human or a computer
     */

    public Player(PlayerType playerType) {
        battlefield = null;
        hand = new Hand();
        deck = new Deck();
        gold = 100;
        resources = 30;
        type = playerType;
    }

    /**
     AddToHand - Adds a card to the player's hand, and returns the index in hand placed

     @param card The card to add
     @return The index of where the card was placed in the player's hand
     */

    public int addToHand(Card card) {
        return hand.add(card);
    }

    /**
     AutoBuy - Automatically buys cards to add to the player's deck
     */

    public void autoBuy() {
        Random rng = new Random();
        Game game = Game.getInstance();

        // Buy cards until max deck size is reached
        while (deck.getNumCards() != deck.getMaxSize()) {
            Card[] buyable = game.getCardsAvailableToBuy(this);
            do {
                // Buy card
                buyCard(buyable[rng.nextInt(buyable.length)]);

                // Refresh set of buyable cards
                buyable = game.getCardsAvailableToBuy(this);
            } while (buyable.length != 0 && deck.getNumCards() != deck.getMaxSize());

            if (deck.getNumCards() != deck.getMaxSize())
            {
                deck.clear();
                gold = 100;
            }
        }
    }

    /**
     BuyCard - Purchases a card with the player's gold and adds it to the player's deck

     @param card The card to purchase
     */

    public void buyCard(Card card) {
        gold -= card.getGoldCost();
        deck.push(card);
    }

    /**
     Cast - Has the player cast the player's selected card from its hand onto the
     battlefield and updates the player's resources appropriately
     The method returns the card cast

     @return card The card cast
     */


    public Card cast() {
        Card card = hand.removeSelected();
        battlefield.placeCard(card, type);
        resources -= card.getResourceCost();
        return card;
    }

    /**
     Draw - Removes and returns the card from the top of the player's deck

     @return The card from the top of the player's deck
     */

    public Card draw() {
        return deck.pop();
    }

    /**
     GetCardsInDeck - Returns the cards contained in the player's deck

     @return The player's cards contained in the player's deck
     */

    public Card[] getCardsInDeck() {
        return deck.getCards();
    }

    /**
     GetDeckSize - Returns the number of cards in the player's deck

     @return The number of cards in the player's deck
     */

    public int getDeckSize() {
        return deck.getNumCards();
    }

    /**
     GetGold - Returns the current amount of gold that the player has

     @return The current amount of gold that the player has
     */

    public int getGold() {
        return gold;
    }

    /**
     GetHandSize - Returns the size of the player's hand

     @return The size of the player's hand
     */

    public int getHandSize() {
        return hand.getHandSize();
    }

    /**
     GetMaxDeckSize - Returns the max size of the player's deck

     @return The max number of cards that the player's deck can contain
     */

    public int getMaxDeckSize() {
        return deck.getMaxSize();
    }

    /**
     GetNumInHand - Returns the number of cards in the player's hand

     @return The number of cards in the player's hand
     */

    public int getNumInHand() {
        return hand.getNumInHand();
    }

    /**
     GetResources - Returns the current amount of resources that the player has

     @return The current amount of resources that the player has
     */

    public int getResources() {
        return resources;
    }

    /**
     GetSelectedCardIndex - Returns the index of the player's selected hand card

     @return Index of selected hand card
     */

    public int getSelectedCardIndex() {
        return hand.getSelectedIndex();
    }

    /**
     GetType - Returns the player's type

     @return The player's type
     */

    public PlayerType getType() {
        return type;
    }

    /**
     IsDefeated - Returns whether the player is defeated

     @return defeated Whether the player is defeated
     */

    public boolean isDefeated() {
        boolean defeated = false;
        if (hand.getNumInHand() == 0 && deck.getNumCards() == 0 && battlefield.isFieldEmpty(type))
            defeated = true;
        return defeated;
    }

    /**
     SelectCardInHand - Player automatically selects a card in its hand
     */

    public void selectCardInHand() {
        hand.autoSelect();
    }

    /**
     SelectCardInHand - Player selects the card in its hand at the specified index

     @param index The index to select
     */

    public void selectCardInHand(int index) {
        hand.setSelectedIndex(index);
    }

    /**
     SelectCardToAttack - Player automatically selects a card to attack on the battlefield
     */

    public void selectCardToAttack() {
        battlefield.autoSelectCardToAttack(type);
    }

    /**
     SelectCardToAttackWith - Player automatically selects a card to attack with on the battlefield
     */

    public void selectCardToAttackWith() {
        battlefield.autoSelectCardToAttackWith(type);
    }

    /**
     SelectWhereToCast - Player automatically selects a location on the battlefield to cast a card
     into
     */

    public void selectWhereToCast() {
        battlefield.autoSelectCastLocation(type);
    }

    /**
     SelectBattlefieldLocation - Player selects a location on the battlefield to perform operations
     on

     @param index The index of the location on the battlefield to select
     @param player Player's field to select
     */

    public void selectBattlefieldLocation(int index, PlayerType player) {
        battlefield.setSelectedIndex(index, player);
    }

    /**
     SellCard - Sells a card from the player's deck at the specified position

     @param index The index in the player's deck of the card to sell
     */

    public void sellCard(int index) {
        gold += deck.removeAt(index).getGoldCost();
    }

    /**
     SetBattlefield - Sets the player's battlefield

     @param field The battlefield to associate with the player
     */

    public void setBattlefield(Battlefield field) {
        battlefield = field;
    }

    /**
     ShuffleDeck - Shuffles the player's deck
     */

    public void shuffleDeck() {
        deck.shuffle();
    }
}

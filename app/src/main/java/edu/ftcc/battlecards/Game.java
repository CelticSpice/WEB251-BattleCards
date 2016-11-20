/**
    This class handles game logic
 */

package edu.ftcc.battlecards;

import java.util.ArrayList;

public class Game {
    // Fields
    private boolean isCardCast, isOver;
    private PlayerType activePlayer, winner;
    private static Game game;
    private Deck masterDeck;
    private Phase gamePhase;
    private Player humanPlayer, computerPlayer;

    /**
        Constructor
     */

    private Game() {
        isCardCast = false;
        activePlayer = PlayerType.HUMAN;
        prepareMasterDeck();
        gamePhase = Phase.Draw;
        humanPlayer = new Player();
        computerPlayer = new Player();
    }

    /**
        The getActivePlayer method returns the active player

        @return The currently active player
     */

    public PlayerType getActivePlayer() {
        return activePlayer;
    }

    /**
        The setActivePlayer method sets the active player

        @param player The active player
     */

    public void setActivePlayer(PlayerType player) {
        activePlayer = player;
    }

    /**
        The getCard method returns a copy of the card with the specified name from the master deck

        @param name The name of the card to get a copy of
        @return A copy of the card with the specified name
     */

    public Card getCard(String name) {
        return new Card(masterDeck.getCard(name));
    }

    /**
        The getCardAt method returns a copy of the card with the specified index from the master
        deck

        @param index Index of card to get a copy of
        @return A copy of the card at the specified index
     */

    public Card getCardAt(int index) {
        return new Card(masterDeck.getCardAt(index));
    }

    /**
        The getIsCardCast method returns whether a card has been cast

        @return Whether a card has been cast
     */

    public boolean getIsCardCast() {
        return isCardCast;
    }

    /**
        The getPhase method returns the current phase of a player's turn

        @return The current game phase of a player's turn
     */

    public Phase getPhase() {
        return gamePhase;
    }

    /**
        The indexOfCard method returns the index of the card from the master deck with the
        specified name

        @param name The name of the card to get the index of
        @return The index of the card
     */

    public int indexOfCard(String name) {
        return masterDeck.indexOf(name);
    }

    /**
        The getIsOver method returns whether the game is over

        @return Whether the game is over
     */

    public boolean getIsOver() {
        return isOver;
    }

    /**
        The getInstance method returns the instance of the game

        @return An instance of game
     */

    public static Game getInstance() {
        if (game == null)
            game = new Game();
        return game;
    }

    /**
     The getCardsAvailableToBuy method returns an array containing cards that are
     available to buy based off of the specified amount of gold

     @param gold The amount of gold to base population of cards available to buy off of
     @return An array of cards available to buy
     */

    public Card[] getCardsAvailableToBuy(int gold) {
        ArrayList<Card> available = new ArrayList<>();
        for (Card card : masterDeck.toArray())
            if (card.getGoldCost() <= gold)
                available.add(new Card(card));
        return available.toArray(new Card[available.size()]);
    }

    /**
        The getWinner method returns the winner of the game, who will be the player with no cards
        on the field, in its hand, or in its deck

        @return The winner
     */

    public PlayerType getWinner() {
        return winner;
    }

    /**
        The prepareMasterDeck method prepares the cards in the master deck
     */

    private void prepareMasterDeck() {
        masterDeck = new Deck();

        // Create the game's cards
        masterDeck.push(new Card(3, 5, Ability.NONE, 3, 5, R.drawable.goblin, "Goblin"));
        masterDeck.push(new Card(7, 3, Ability.FIRST_STRIKE, 5, 4, R.drawable.orc, "Orc"));
        masterDeck.push(new Card(2, 3, Ability.NONE, 3, 3, R.drawable.undead, "Undead"));
        masterDeck.push(new Card(3, 3, Ability.NONE, 2, 3, R.drawable.kobold, "Kobold"));
        masterDeck.push(new Card(25, 25, Ability.FIRST_STRIKE, 15, 15, R.drawable.dragon,
                                  "Dragon"));
    }

    /**
        The setIsCardCast method sets if a card has been cast

        @param cast Whether a card has been cast
     */

    public void setIsCardCast(boolean cast) {
        isCardCast = cast;
    }

    /**
        The setIsOver method sets whether the game is over

        @param over Whether the game is over
     */

    public void setIsOver(boolean over) {
        isOver = over;
    }

    /**
        The setPhase method sets the phase of the game the player is in

        @param phase The phase the player is in
     */

    public void setPhase(Phase phase) {
        gamePhase = phase;
    }

    /**
        The setWinner method sets the winner of the game

        @param player The winner of the game
     */

    public void setWinner(PlayerType player) {
        winner = player;
    }

    /**
        HumanPlayer Getter

        @return A reference to the human player
     */

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    /**
     ComputerPlayer Getter

     @return A reference to the computer player
     */

    public Player getComputerPlayer() {
        return computerPlayer;
    }
}

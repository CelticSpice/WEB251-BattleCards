/**
    This class handles game logic
 */

package edu.ftcc.battlecards;

import java.util.ArrayList;

public class Game {
    // Fields
    private static Game game;
    private Deck masterDeck;
    private Player humanPlayer, computerPlayer;

    /**
        Constructor
     */

    private Game() {
        prepareMasterDeck();
        humanPlayer = new Player();
        computerPlayer = new Player();
    }

    /**
        The getInstance method returns an instance of the Game

        @return An instance of the Game
     */

    public static Game getInstance() {
        if (game == null)
            game = new Game();
        return game;
    }

    /**
        The getMasterDeck method returns a reference to the game's master deck of cards

        @return A reference to the game's master deck of cards
     */

    public Deck getMasterDeck() { return masterDeck; }

    /**
     The getCardsAvailableToBuy method returns an array containing cards that are
     available to buy based off of the specified amount of gold

     @param gold The amount of gold to base population off of
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
        The prepareMasterDeck method prepares the cards in the master deck
     */

    private void prepareMasterDeck() {
        masterDeck = new Deck();

        // Create the game's cards
        masterDeck.push(new Card(3, 5, CombatAbility.NONE, 3, 5, R.drawable.goblin, "Goblin"));
        masterDeck.push(new Card(7, 3, CombatAbility.FIRST_STRIKE, 5, 4, R.drawable.orc, "Orc"));
        masterDeck.push(new Card(2, 3, CombatAbility.NONE, 3, 3, R.drawable.undead, "Undead"));
        masterDeck.push(new Card(3, 3, CombatAbility.NONE, 2, 3, R.drawable.kobold, "Kobold"));
        masterDeck.push(new Card(25, 25, CombatAbility.FIRST_STRIKE, 15, 15, R.drawable.dragon,
                                  "Dragon"));
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

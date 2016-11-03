/**
    This class handles game logic
 */

package edu.ftcc.battlecards;

import java.util.ArrayList;

public class Game {
    // Fields
    private static Game game;
    private GameCards cards;
    private Player humanPlayer, computerPlayer;

    /**
        Constructor
     */

    private Game() {
        prepareGameCards();
        humanPlayer = new Player();
    }

    /**
        The getInstance method returns an instance of the Game

        @return The instance of the Game
     */

    public static Game getInstance() {
        if (game == null)
            game = new Game();
        return game;
    }

    /**
        The canBuy method determines whether it is possible for a player to buy
        the specified card

        @param player The player to buy the card
        @param card The card to buy
        @return canBuy Whether the card can be bought
     */

    public boolean canBuy(PlayerType player, Card card) {
        boolean canBuy = false;
        if (player == PlayerType.HUMAN)
            if (humanPlayer.getGold() >= card.getGoldCost())
                canBuy = true;
        else
            if (computerPlayer.getGold() >= card.getGoldCost())
                canBuy = true;
        return canBuy;
    }

    /**
        The getCards method returns a reference to GameCards

        @return A reference to GameCards
     */

    public GameCards getCards() { return cards; }

    /**
     The getCardsAvailableToBuy method returns an array containing cards that are
     available to buy based off of the passed gold amount

     @param gold The amount of gold to base population off of
     @return An array of Cards available to buy
     */

    public Card[] getCardsAvailableToBuy(int gold) {
        ArrayList<Card> available = new ArrayList<>(0);
        for (Card card : cards.getCards())
            if (card.getGoldCost() <= gold)
                available.add(new Card(card));
        return available.toArray(new Card[available.size()]);
    }

    /**
        The getPlayer method returns a reference to the specifed player

        @param type The type of player to get
        @return player The player
     */

    public Player getPlayer(PlayerType type) {
        Player player;
        if (type == PlayerType.HUMAN)
            player = humanPlayer;
        else
            player = computerPlayer;
        return player;
    }

    /**
        The prepareComputerPlayer method prepares the computerPlayer
     */

    public void prepareComputerPlayer() {
        computerPlayer = new Player();
    }

    /**
        The prepareGameCards method prepares the cards available in the game
     */

    private void prepareGameCards() {
        final int NUMBER_OF_CARDS = 5;

        cards = new GameCards();

        // Create the game's cards
        Card[] cardsInGame = new Card[NUMBER_OF_CARDS];
        CardProperties properties = new CardProperties();

        properties.name = "Goblin";
        properties.attack = 3;
        properties.defense = 5;
        properties.goldCost = 3;
        properties.resourceCost = 5;
        properties.image = R.drawable.goblin;
        properties.ability = CombatAbility.NONE;
        cardsInGame[0] = new Card(properties);

        properties.name = "Orc";
        properties.attack = 7;
        properties.defense = 3;
        properties.goldCost = 5;
        properties.resourceCost = 4;
        properties.image = R.drawable.orc;
        properties.ability = CombatAbility.FIRST_STRIKE;
        cardsInGame[1] = new Card(properties);

        properties.name = "Undead";
        properties.attack = 2;
        properties.defense = 5;
        properties.goldCost = 3;
        properties.resourceCost = 3;
        properties.image = R.drawable.undead;
        properties.ability = CombatAbility.NONE;
        cardsInGame[2] = new Card(properties);

        properties.name = "Kobold";
        properties.attack = 3;
        properties.defense = 3;
        properties.goldCost = 2;
        properties.resourceCost = 3;
        properties.image = R.drawable.kobold;
        properties.ability = CombatAbility.NONE;
        cardsInGame[3] = new Card(properties);

        properties.name = "Dragon";
        properties.attack = 25;
        properties.defense = 25;
        properties.goldCost = 15;
        properties.resourceCost = 15;
        properties.image = R.drawable.dragon;
        properties.ability = CombatAbility.FIRST_STRIKE;
        cardsInGame[4] = new Card(properties);

        // Initialize GameCards' data
        cards.InitGameCards(cardsInGame);
    }
}

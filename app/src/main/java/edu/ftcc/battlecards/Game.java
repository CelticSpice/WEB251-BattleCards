/**
 This class handles game logic
 */

package edu.ftcc.battlecards;

import java.util.ArrayList;

public class Game {
    // Fields
    private static Game game;

    private Battlefield battlefield;
    private boolean isCardCast;
    private Deck masterDeck;
    private Phase phase;
    private Player humanPlayer, computerPlayer;

    /**
     Constructor
     */

    private Game() {
        battlefield = new Battlefield();
        isCardCast = false;
        prepareMasterDeck();
        phase = Phase.DRAW;
        humanPlayer = new Player(PlayerType.HUMAN);
        computerPlayer = new Player(PlayerType.COMPUTER);
        humanPlayer.setBattlefield(battlefield);
        computerPlayer.setBattlefield(battlefield);
    }

    /**
     DoAttack - Performs the logic of an attack
     Accepts the player to perform the attack
     Returns information about the attack

     @param player The player performing the attack
     @return The attack
     */

    public Attack doAttack(PlayerType player) {
        Card attacker = battlefield.getSelectedCard(player);
        int attackerI = battlefield.getSelectedIndex(player);

        if (player == PlayerType.HUMAN) {
            if (player == PlayerType.HUMAN) {

                defender = battlefield.getSelectedCard(PlayerType.COMPUTER);
                defenderI = battlefield.getSelectedIndex(PlayerType.COMPUTER);
            }
            else {
                defender = battlefield.getSelectedCard(PlayerType.HUMAN);
                defenderI = battlefield.getSelectedIndex(PlayerType.HUMAN);
            }

            // Attack begins
            if (defender.getAbility() == Ability.FIRST_STRIKE)
                // Defender strikes back
                defender.attack(attacker);

            if (attacker.getDefense() > 0)
                attacker.attack(defender);

            // Attacker can no longer attack
            attacker.setCanAttack(false);

            // Reset battlefield selections
            battlefield.setSelectedIndex(-1, PlayerType.HUMAN);
            battlefield.setSelectedIndex(-1, PlayerType.COMPUTER);
        }

        return new Attack(attacker, defender, attackerI, defenderI);
    }

    /**
     DoCast - Performs the logic of the cast phase
     Accepts the player to perform the cast
     Returns information about the cast

     @param player The player to perform the cast
     @return cast The cast
     */

    public Cast doCast(PlayerType player) {
        Card cast;
        int handI, fieldI;

        if (player == PlayerType.HUMAN) {
            handI = humanPlayer.getSelectedCardIndex();
            fieldI = battlefield.getSelectedIndex(player);
            cast = humanPlayer.cast();
        }
        else {
            computerPlayer.selectCardInHand();
            if (battlefield.isCardAt(0, PlayerType.COMPUTER))
                computerPlayer.selectBattlefieldLocation(0, PlayerType.COMPUTER);
            else
                computerPlayer.selectBattlefieldLocation(1, PlayerType.COMPUTER);

            handI = humanPlayer.getSelectedCardIndex();
            fieldI = battlefield.getSelectedIndex(player);
            cast = humanPlayer.cast();
        }

        return new Cast(cast, handI, fieldI);
    }

    /**
     DoComputerTurn - Performs the logic of the computer taking its turn
     Returns the results of the computer's turn

     @return result The result of the computer's turn
     */

    public TurnResult doComputerTurn() {
        TurnResult result = new TurnResult();

        // Player draws
        if (computerPlayer.getDeckSize() != 0)
            result.setDraw(doDraw(PlayerType.COMPUTER));

        // Player casts
        if (computerPlayer.getNumInHand() != 0)
            result.setCast(doCast(PlayerType.COMPUTER));

        // Player Attacks
        while (battlefield.canCardsAttack(PlayerType.COMPUTER) &&
              !battlefield.isFieldEmpty(PlayerType.HUMAN)) {

        }
    }

    /**
     DoDraw - Performs the logic of the draw phase
     Accepts the player to perform the draw
     Returns information about the drawing

     @param player The player to perform the draw
     @return draw The draw
     */

    public Draw doDraw(PlayerType player) {
        boolean success = false;
        Card drawn = null;
        int index = -1;

        if (player == PlayerType.HUMAN) {
            if (humanPlayer.getDeckSize() != 0) {
                success = true;
                drawn = humanPlayer.draw();
                index = humanPlayer.addToHand(drawn);
            }
        }
        else {
            if (computerPlayer.getDeckSize() != 0) {
                success = true;
                drawn = computerPlayer.draw();
                index = computerPlayer.addToHand(drawn);
            }
        }

        return new Draw(success, drawn, index);
    }

    /**
     The getBattlefield method returns a reference to the battlefield

     @return A reference to the battlefield
     */

    public Battlefield getBattlefield() {
        return battlefield;
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
     The getCardAt method returns a copy of the card at the specified index of the master
     deck

     @param index Index of card to get a copy of
     @return A copy of the card at the specified index
     */

    public Card getCardAt(int index) {
        return new Card(masterDeck.getCardAt(index));
    }

    /**
     The getCardsAvailableToBuy method returns an array containing cards that are
     available to buy based off of the specified amount of gold

     @param gold The amount of gold to base population of cards available to buy off of
     @return An array of cards available to buy
     */

    public Card[] getCardsAvailableToBuy(int gold) {
        ArrayList<Card> available = new ArrayList<>();
        for (Card card : masterDeck.getCards())
            if (card.getGoldCost() <= gold)
                available.add(new Card(card));
        return available.toArray(new Card[available.size()]);
    }

    /**
     The getComputerPlayer method returns a reference to the computer player

     @return A reference to the computer player
     */

    public Player getComputerPlayer() {
        return computerPlayer;
    }

    /**
     The getHumanPlayer method returns a reference to the human player

     @return A reference to the human player
     */

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    /**
     The getInstance method returns an instance of the game

     @return An instance of game
     */

    public static Game getInstance() {
        if (game == null)
            game = new Game();
        return game;
    }

    /**
     The getIsCardCast method returns whether a card has been cast

     @return Whether a card has been cast
     */

    public boolean getIsCardCast() {
        return isCardCast;
    }

    /**
     The getPhase method returns the phase of the game

     @return The phase of the game
     */

    public Phase getPhase() {
        return phase;
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
        The startBattlePhase method handles the logic of starting the battle phase
     */

    private void startBattlePhase() {
        phase = Phase.BATTLE;
        battlefield.setSelectedIndex(-1, PlayerType.HUMAN);
        battlefield.setSelectedIndex(-1, PlayerType.COMPUTER);
    }

    /**
     SetIsCardCast - Sets whether a card has been cast

     @param cast Whether a card has been cast
     */

    public void setIsCardCast(boolean cast) {
        isCardCast = cast;
    }

    /**
     The setPhase method sets the phase of the game

     @param p The phase of the game
     */

    public void setPhase(Phase p) {
        phase = p;
    }
}

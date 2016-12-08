/**
 This class handles game logic
 12/8/2016
 WEB 251 0001 - M5PROJ
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import java.util.ArrayList;

public class Game {
    // Fields
    private static Game game;

    private Battlefield battlefield;
    private boolean isGameOver;
    private Deck masterDeck;
    private Phase phase;
    private Player humanPlayer, computerPlayer;

    /**
     Constructor
     */

    private Game() {
        battlefield = new Battlefield();
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

    public Attack doAttack(Player player) {
        Card attacker, defender;
        int attackerI, defenderI;

        if (player == humanPlayer) {
            attacker = battlefield.getSelectedCard(player.getType());
            attackerI = battlefield.getSelectedIndex(player.getType());
            defender = battlefield.getSelectedCard(PlayerType.COMPUTER);
            defenderI = battlefield.getSelectedIndex(PlayerType.COMPUTER);
        }
        else {
            // Player selects card to attack with and to attack
            computerPlayer.selectCardToAttackWith();
            computerPlayer.selectCardToAttack();

            attacker = battlefield.getSelectedCard(player.getType());
            attackerI = battlefield.getSelectedIndex(player.getType());
            defender = battlefield.getSelectedCard(PlayerType.HUMAN);
            defenderI = battlefield.getSelectedIndex(PlayerType.HUMAN);
        }

        // Attack begins
        if ((defender.getAbility() == Ability.FIRST_STRIKE) && (attacker.getAbility() != Ability.FIRST_STRIKE))
            // Defender strikes back
            defender.attack(attacker);

        // Attacker strikes
        if (attacker.getDefense() > 0)
            attacker.attack(defender);
        else
            // Attacker is defeated
            battlefield.removeSelectedCard(player.getType());

        // Check if defender is defeated
        if (defender.getDefense() <= 0) {
            // Defender is defeated
            if (player == humanPlayer)
                battlefield.removeSelectedCard(PlayerType.COMPUTER);
            else
                battlefield.removeSelectedCard(PlayerType.HUMAN);
        }

        // Attacker can no longer attack
        attacker.setCanAttack(false);

        // Reset battlefield selections
        battlefield.setSelectedIndex(-1, PlayerType.HUMAN);
        battlefield.setSelectedIndex(-1, PlayerType.COMPUTER);

        return new Attack(attacker, defender, attackerI, defenderI);
    }

    /**
     DoCast - Performs the logic of the cast phase
     Accepts the player to perform the cast
     Returns information about the cast

     @param player The player to perform the cast
     @return cast The cast
     */

    public Cast doCast(Player player) {
        Card cast;
        int handI, fieldI;

        if (player == humanPlayer) {
            handI = humanPlayer.getSelectedCardIndex();
            fieldI = battlefield.getSelectedIndex(player.getType());
            cast = humanPlayer.cast();

            if ((cast.getAbility() == Ability.PSYCHIC_STRIKE) && (computerPlayer.getDeckSize() > 0)) {
                computerPlayer.draw();
            }
        }
        else {
            computerPlayer.selectCardInHand();
            computerPlayer.selectWhereToCast();

            handI = computerPlayer.getSelectedCardIndex();
            fieldI = battlefield.getSelectedIndex(player.getType());
            cast = computerPlayer.cast();

            if ((cast.getAbility() == Ability.PSYCHIC_STRIKE) && (humanPlayer.getDeckSize() > 0)) {
                humanPlayer.draw();
            }
        }

        phase = Phase.BATTLE;

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
            result.setDraw(doDraw(computerPlayer));

        // Player casts
        if (computerPlayer.getNumInHand() != 0 && !battlefield.isFieldFull(PlayerType.COMPUTER))
            result.setCast(doCast(computerPlayer));

        // Player Attacks
        while (battlefield.canCardsAttack(PlayerType.COMPUTER) &&
              !battlefield.isFieldEmpty(PlayerType.HUMAN)) {

            result.addAttack(doAttack(computerPlayer));
        }

        endTurn();

        return result;
    }

    /**
     DoDraw - Performs the logic of the draw phase
     Accepts the player to perform the draw
     Returns information about the drawing

     @param player The player to perform the draw
     @return draw The draw
     */

    public Draw doDraw(Player player) {
        Card drawn = null;
        int index = -1;

        if (player == humanPlayer) {
            drawn = humanPlayer.draw();
            index = humanPlayer.addToHand(drawn);
        }
        else {
            drawn = computerPlayer.draw();
            index = computerPlayer.addToHand(drawn);
        }

        phase = Phase.CAST;

        return new Draw(drawn, index);
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
     GetCardsAvailableToBuy - Returns an array containing cards that the specified player can
     buy

     @param player The player to get cards available to buy for
     @return An array of cards available to buy
     */

    public Card[] getCardsAvailableToBuy(Player player) {
        ArrayList<Card> available = new ArrayList<>();
        int gold = player.getGold();
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
     GetInstance - Returns an instance of the game

     @return An instance of game
     */

    public static Game getInstance() {
        if (game == null)
            game = new Game();
        return game;
    }

    /**
     GetIsGameOver - Returns whether the game is over

     @return Whether the game is over
     */

    public boolean getIsGameOver() {
        return isGameOver;
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
     EndTurn - Ends the current turn
     */

    public void endTurn() {
        // Check if game is over
        if (humanPlayer.isDefeated() || computerPlayer.isDefeated())
            isGameOver = true;
        else {
            // Reset can attack status of cards on battlefield
            battlefield.resetCanAttack();

            // To draw phase
            phase = Phase.DRAW;
        }
    }

    /**
     The prepareMasterDeck method prepares the cards in the master deck
     */

    private void prepareMasterDeck() {
        masterDeck = new Deck();

        // Create the game's cards
        masterDeck.push(new Card(3, 4, Ability.NONE, 5, 0, R.drawable.goblin, "Goblin"));
        masterDeck.push(new Card(5, 3, Ability.FIRST_STRIKE, 10, 0, R.drawable.orc, "Orc"));
        masterDeck.push(new Card(3, 9, Ability.PSYCHIC_STRIKE, 15, 0, R.drawable.undead, "Undead"));
        masterDeck.push(new Card(2, 2, Ability.NONE, 0, 0, R.drawable.kobold, "Kobold"));
        masterDeck.push(new Card(8, 8, Ability.FIRST_STRIKE, 20, 0, R.drawable.dragon,
                                  "Dragon"));
    }

    /**
     The setPhase method sets the phase of the game

     @param p The phase of the game
     */

    public void setPhase(Phase p) {
        phase = p;
    }
}

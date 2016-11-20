package edu.ftcc.battlecards;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BattlefieldActivity extends AppCompatActivity {
    // Fields
    private Battlefield battlefield;
    private Button btnAction;
    private Game game;
    private TextView txtHumanDeckSize, txtComputerDeckSize, txtInfo;
    private View[] computerFieldViews, handViews, humanFieldViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        // Create battlefield
        battlefield = new Battlefield();

        // Get game instance
        game = Game.getInstance();

        // Get views and button
        txtHumanDeckSize = (TextView) findViewById(R.id.txtHumanDeckSize);
        txtComputerDeckSize = (TextView) findViewById(R.id.txtComputerDeckSize);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        btnAction = (Button) findViewById(R.id.btnAction);

        computerFieldViews = new View[battlefield.getFieldSize()];
        computerFieldViews[0] = findViewById(R.id.fragComputerField1);
        computerFieldViews[1] = findViewById(R.id.fragComputerField2);

        handViews = new View[Player.HAND_SIZE];
        handViews[0] = findViewById(R.id.fragHand1);
        handViews[1] = findViewById(R.id.fragHand2);
        handViews[2] = findViewById(R.id.fragHand3);

        humanFieldViews = new View[battlefield.getFieldSize()];
        humanFieldViews[0] = findViewById(R.id.fragHumanField1);
        humanFieldViews[1] = findViewById(R.id.fragHumanField2);

        // Set initial text displayed
        txtHumanDeckSize.setText("15/15");
        txtComputerDeckSize.setText("15/15");
        txtInfo.setText("Draw a Card");
        btnAction.setText("Draw");

        // Register button listeners
        registerListeners();
    }

    /**
        The displayCard method displays a specified card in the specified view

        @param card Card to display
        @param v View to display card in
     */

    private void displayCard(Card card, View v) {
        ((TextView)v.findViewById(R.id.txtName)).setText(card.getName());
        ((ImageView)v.findViewById(R.id.imgImage)).setImageResource(card.getImageResID());
        ((TextView)v.findViewById(R.id.txtAttack)).setText(String.valueOf(card.getAttack()));
        ((TextView)v.findViewById(R.id.txtDefense)).setText(String.valueOf(card.getDefense()));

        String ability = card.getAbility().toString().toLowerCase();
        ability = ability.replace('_', ' ');
        if (!ability.equals("none"))
            ((TextView)v.findViewById(R.id.txtAbility)).setText(ability);

        ((TextView)v.findViewById(R.id.txtResource)).setText(String.valueOf(card.getResourceCost()));
        ((TextView)v.findViewById(R.id.txtGold)).setText(String.valueOf(card.getGoldCost()));
    }

    /**
        The doAttack method performs the logic of an attack
     */

    private void doAttack() {
        Card attacker = null;
        Card defender = null;

        // Get attacker and defender
        if (game.getActivePlayer() == PlayerType.HUMAN) {
            attacker = battlefield.getHumanCardAt(battlefield.getHumanSelectedIndex());
            defender = battlefield.getComputerCardAt(battlefield.getComputerSelectedIndex());
        }
        else {
            attacker = battlefield.getComputerCardAt(battlefield.getComputerSelectedIndex());
            defender = battlefield.getHumanCardAt(battlefield.getHumanSelectedIndex());
        }

        // Check for first strike
        if (defender.getAbility() == Ability.FIRST_STRIKE) {
            // Defender attacks first
            attacker.alterDefense(defender.getAttack());

            // Attacker attacks
            if (attacker.getDefense() > 0)
                defender.alterDefense(attacker.getAttack());
            else
                onCardDefeated(attacker);
        }
        else {
            // Attacker attacks
            defender.alterDefense(attacker.getAttack());

            // Defender attacks
            if (defender.getDefense() > 0)
                attacker.alterDefense(defender.getAttack());
            else
                onCardDefeated(defender);
        }

        // Reset active states
        attacker.setActive(false);
        defender.setActive(false);

        // Set attacker's attacker state
        attacker.setCanAttack(false);

        // Reset selected indices
        battlefield.setHumanSelectedIndex(-1);
        battlefield.setComputerSelectedIndex(-1);
    }

    /**
        The doCast method performs the logic of the cast phase
     */

    private void doCast() {
        if (game.getActivePlayer() == PlayerType.HUMAN) {
            // Get selected card from player's hand
            Card selected = game.getHumanPlayer().getActiveCard();

            // Get selected field position to cast card in
            View field = humanFieldViews[battlefield.getHumanSelectedIndex()];

            // Cast card
            int selectedIndex = game.getHumanPlayer().indexOfHandCard(selected);
            undisplayCard(handViews[selectedIndex]);
            game.getHumanPlayer().cast(selected);
            battlefield.placeCard(selected, battlefield.getHumanSelectedIndex(), PlayerType.HUMAN);
            displayCard(selected, field);

            // Reset player's battlefield selected index and the cast card's active state
            battlefield.setHumanSelectedIndex(-1);
            selected.setActive(false);
        }
        else {
            // Get selected card from player's hand
            Card selected = game.getComputerPlayer().getActiveCard();

            // Get selected field position to cast card in
            View field = computerFieldViews[battlefield.getComputerSelectedIndex()];

            // Cast card
            game.getComputerPlayer().cast(selected);
            battlefield.placeCard(selected, battlefield.getComputerSelectedIndex(),
                                  PlayerType.COMPUTER);
            displayCard(selected, field);

            // Reset player's battlefield selected index and the cast card's active state
            battlefield.setComputerSelectedIndex(-1);
            selected.setActive(false);
        }
        game.setIsCardCast(true);
    }

    /**
        The doComputerTurn method performs the logic of the computer's turn
     */

    private void doComputerTurn() {
        // Computer draws
        doDraw();

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        // If computer's field is empty, cast
        if (battlefield.isFieldEmpty(PlayerType.COMPUTER))
            doCast();

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        // while computer can attack, attack
        while (battlefield.canCardsAttack(PlayerType.COMPUTER) &&
               !battlefield.isFieldEmpty(PlayerType.HUMAN)) {

            doSelectCardToAttackWith();
            doSelectCardToAttack();
            doAttack();

            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }

        // End turn
        onEndTurn();
    }

    /**
        The doDraw method performs the logic of the draw phase
     */

    private void doDraw() {
        if (game.getActivePlayer() == PlayerType.HUMAN) {
            // Player draws a card
            Card drawn = game.getHumanPlayer().draw();

            // Update deck size
            txtHumanDeckSize.setText(String.valueOf(game.getHumanPlayer().getDeckSize()) + "/15");

            // If there is room in hand, place in player's hand
            int numInHand = game.getHumanPlayer().getNumInHand();
            if (numInHand < Player.HAND_SIZE) {
                int indexInHand = game.getHumanPlayer().addToHand(drawn);
                displayCard(drawn, handViews[indexInHand]);
            }
        }
        else {
            // Player draws a card
            Card drawn = game.getComputerPlayer().draw();

            // Update deck size
            txtComputerDeckSize.setText(String.valueOf(
                    game.getComputerPlayer().getDeckSize() + "/15"));

            // If there is room in hand, place in player's hand
            int numInHand = game.getComputerPlayer().getNumInHand();
            if (numInHand < Player.HAND_SIZE)
                game.getComputerPlayer().addToHand(drawn);
        }
    }

    /**
        The doSelectCardToAttack method performs the logic of selecting a card to attack
     */

    private void doSelectCardToAttack() {
        if (game.getActivePlayer() == PlayerType.HUMAN) {
            // Check that the player has selected a card to attack with
            if (battlefield.getHumanSelectedIndex() != -1) {
                // Get selected card to attack
                Card selected = battlefield.getComputerCardAt(
                        battlefield.getComputerSelectedIndex());

                // Set selected card to active
                selected.setActive(true);
            }
        }
        else {
            // Player selects card
            int selection = game.getComputerPlayer().selectCardFromField();
            Card selected = null;
            if (battlefield.getHumanCardAt(selection) != null)
                selected = battlefield.getHumanCardAt(selection);
            else {
                if (selection == 0)
                    selected = battlefield.getHumanCardAt(1);
                else
                    selected = battlefield.getHumanCardAt(0);
            }

            // Set the card to active
            selected.setActive(true);
        }
    }

    /**
        The doSelectCardToAttackWith method performs the logic of selecting a card to attack with
     */

    private void doSelectCardToAttackWith() {
        if (game.getActivePlayer() == PlayerType.HUMAN) {
            // Get the card the player selected
            Card selected = battlefield.getHumanCardAt(battlefield.getHumanSelectedIndex());

            // If the card can attack, set it to active
            if (selected.getCanAttack()) {
                selected.setActive(true);
                humanFieldViews[battlefield.getHumanSelectedIndex()].setBackground(
                        getResources().getDrawable(R.drawable.custom_background));

                // Set other card to inactive
                if (battlefield.getHumanSelectedIndex() == 0) {
                    battlefield.getHumanCardAt(1).setActive(false);
                    humanFieldViews[1].setBackground(getResources().getDrawable(R.drawable.custom_background));
                }
                else {
                    battlefield.getComputerCardAt(0).setActive(false);
                    humanFieldViews[0].setBackground(getResources().getDrawable(R.drawable.custom_background));
                }
            }
        }
        else {
            // Player selects card
            int selection = game.getComputerPlayer().selectCardFromField();
            Card selected = null;
            if (battlefield.getComputerCardAt(selection).getCanAttack())
                selected = battlefield.getComputerCardAt(selection);
            else {
                if (selection == 0)
                    selected = battlefield.getComputerCardAt(1);
                else
                    selected = battlefield.getComputerCardAt(0);
            }

            // Set the card to active
            selected.setActive(true);
        }
    }

    /**
        The onCardDefeated method performs the logic for when a card is defeated

        @param card The card that was defeated
     */

    private void onCardDefeated(Card card) {
        // Determine where in the field the card was
        if (card == battlefield.getHumanCardAt(battlefield.getHumanSelectedIndex())) {
            // Remove card from field
            battlefield.removeCard(battlefield.getHumanSelectedIndex(), PlayerType.HUMAN);

            // Remove card display
            undisplayCard(humanFieldViews[battlefield.getHumanSelectedIndex()]);

            // Check if game should be over
            if (game.getHumanPlayer().getDeckSize() == 0   &&
                game.getHumanPlayer().getNumInHand() == 0  &&
                battlefield.isFieldEmpty(PlayerType.HUMAN)) {

                // Set game over and winner
                game.setIsOver(true);
                game.setWinner(PlayerType.HUMAN);
                btnAction.setEnabled(false);
                txtInfo.setText("The computer won!");
            }
        }
        else {
            // Remove card from field
            battlefield.removeCard(battlefield.getComputerSelectedIndex(), PlayerType.HUMAN);

            // Remove card display
            undisplayCard(humanFieldViews[battlefield.getComputerSelectedIndex()]);

            // Check if game should be over
            if (game.getComputerPlayer().getDeckSize() == 0      &&
                    game.getComputerPlayer().getNumInHand() == 0 &&
                    battlefield.isFieldEmpty(PlayerType.COMPUTER)) {

                // Set game over and winner
                game.setIsOver(true);
                game.setWinner(PlayerType.COMPUTER);
                btnAction.setEnabled(false);
                txtInfo.setText("The computer won!");
            }
        }
    }

    /**
        The onEndTurn method performs the logic for when the end of a turn has been reached
     */

    private void onEndTurn() {
        if (game.getActivePlayer() == PlayerType.HUMAN) {
            // Reset attack status of cards on player's field
            battlefield.resetCanAttack(PlayerType.HUMAN);

            // Change active player
            game.setActivePlayer(PlayerType.COMPUTER);
        }
        else {
            // Reset attack status of cards on player's field
            battlefield.resetCanAttack(PlayerType.COMPUTER);

            // Change active player
            game.setActivePlayer(PlayerType.HUMAN);
        }
    }

    /**
        The registerListeners method registers listeners to the controls
     */

    private void registerListeners() {
        for (View view : computerFieldViews) {
            view.setOnClickListener(new CardFieldClickHandler());
            view.setOnLongClickListener(new LongClickHandler());
        }
        for (View view : handViews) {
            view.setOnClickListener(new CardHandClickHandler());
            view.setOnLongClickListener(new LongClickHandler());
        }
        for (View view : humanFieldViews) {
            view.setOnClickListener(new CardFieldClickHandler());
            view.setOnLongClickListener(new LongClickHandler());
        }
        btnAction.setOnClickListener(new ButtonHandler());
    }

    /**
     The startBattlePhase method starts the battle phase
     */

    private void startBattlePhase() {
        game.setPhase(Phase.Battle);
        Toast.makeText(this, "Battle Phase", Toast.LENGTH_SHORT).show();
        if (game.getActivePlayer() == PlayerType.HUMAN) {
            txtInfo.setText("Attack enemy cards with your cards");
            btnAction.setText("End Turn");
        }
    }

    /**
     The startCastPhase method starts the cast phase
     */

    private void startCastPhase() {
        game.setPhase(Phase.Cast);
        Toast.makeText(this, "Cast Phase", Toast.LENGTH_SHORT).show();
        if (game.getActivePlayer() == PlayerType.HUMAN) {
            txtInfo.setText("Select a card from your hand to cast, or move on to battle phase");
            btnAction.setText("Battle");
        }
    }

    /**
        The undisplayCard method removes the display of a card in the specified fragment

        @param v The View to remove the display of a card from
     */

    private void undisplayCard(View v) {
        ((TextView)v.findViewById(R.id.txtName)).setText(null);
        ((ImageView)v.findViewById(R.id.imgImage)).setImageResource(0);
        ((TextView)v.findViewById(R.id.txtAttack)).setText(null);
        ((TextView)v.findViewById(R.id.txtDefense)).setText(null);
        ((TextView)v.findViewById(R.id.txtAbility)).setText(null);
        ((TextView)v.findViewById(R.id.txtResource)).setText(null);
        ((TextView)v.findViewById(R.id.txtGold)).setText(null);
        v.setBackground(getResources().getDrawable(R.drawable.custom_background));
    }

    /**
        Handler for button
     */

    private class ButtonHandler implements Button.OnClickListener {
        /**
            onClick method
         */
        @Override
        public void onClick(View v) {
            // Determine action to perform
            String action = ((Button)v).getText().toString();
            switch (action) {
                case "Draw":
                    doDraw();
                    startCastPhase();
                    break;
                case "Battle":
                    startBattlePhase();
                    break;
                case "End Turn":
                    onEndTurn();
                    doComputerTurn();
                    break;
            }
        }
    }

    /**
        Click handler for card field views
     */

    private class CardFieldClickHandler implements View.OnClickListener {
        /**
            onClick method
         */
        @Override
        public void onClick(View v) {
            // Get phase
            if (game.getPhase() == Phase.Cast) {
                if (!game.getIsCardCast() && game.getHumanPlayer().isHandCardActive() &&
                    v != computerFieldViews[0] && v != computerFieldViews[1]) {

                    // Determine which view was clicked
                    if (v == humanFieldViews[0])
                        battlefield.setHumanSelectedIndex(0);
                    else
                        battlefield.setHumanSelectedIndex(1);
                    doCast();
                }
            }
            else if (game.getPhase() == Phase.Battle) {
                // Determine view clicked
                if (v == humanFieldViews[0]) {
                    if (battlefield.getHumanCardAt(0) != null) {
                        battlefield.setHumanSelectedIndex(0);
                        doSelectCardToAttackWith();
                    }
                }
                else if (v == humanFieldViews[1]) {
                    if (battlefield.getHumanCardAt(1) != null) {
                        battlefield.setHumanSelectedIndex(1);
                        doSelectCardToAttackWith();
                    }
                }
                else if (v == computerFieldViews[0]) {
                    if (battlefield.getComputerCardAt(1) != null) {
                        battlefield.setComputerSelectedIndex(1);
                        doSelectCardToAttackWith();
                        doAttack();
                    }
                }
                else {
                    if (battlefield.getComputerCardAt(1) != null) {
                        battlefield.setComputerSelectedIndex(1);
                        doSelectCardToAttackWith();
                        doAttack();
                    }
                }
            }
        }
    }

    /**
        Click handler for human's hand views
     */

    private class CardHandClickHandler implements View.OnClickListener {
        /**
            onClick method
         */
        @Override
        public void onClick(View v) {
            // Check phase
            if (game.getPhase() == Phase.Cast) {
                // Get index of view that was clicked
                int handIndex = 0;
                for (int i = 0; i < handViews.length; i++)
                    if (handViews[i] == v)
                        handIndex = i;

                // Get card
                Card card = game.getHumanPlayer().getCardInHandAt(handIndex);

                // Check if card has not already been clicked
                if (card != null && !card.getActive()) {
                    // Set the selected card to active
                    card.setActive(true);

                    // Set other cards in hand as not active
                    for (Card c : game.getHumanPlayer().getHand())
                        if (c != null && c != card)
                            c.setActive(false);

                    // Set view background
                    v.setBackground(getBaseContext().getResources().getDrawable(R.drawable.card_selected));
                }
                else if (card != null && card.getActive()) {
                    // Set the selected card to inactive
                    card.setActive(false);
                    v.setBackground(getBaseContext().getResources().getDrawable(R.drawable.custom_background));
                }
            }
        }
    }

    /**
        Long click handler for card views
     */
    private class LongClickHandler implements View.OnLongClickListener {
        /**
            onLongClick method
         */
        @Override
        public boolean onLongClick(View v) {
            // Get and display card that was clicked
            String name = ((TextView)v.findViewById(R.id.txtName)).getText().toString();
            if (!name.isEmpty()) {
                int cardIndex = game.indexOfCard(name);
                Intent intent = new Intent(BattlefieldActivity.this, CardDetailActivity.class);
                intent.putExtra(CardDetailActivity.CARD_INDEX, cardIndex);
                startActivity(intent);
            }
            return true;
        }
    }
}

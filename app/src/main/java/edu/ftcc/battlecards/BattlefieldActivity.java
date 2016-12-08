/**
 Where the user will play the game
 12/8/2016
 WEB 251 0001 - M5PROJ
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BattlefieldActivity extends AppCompatActivity {
    // Fields
    private Button btnAction;
    private Game game;
    private TextView txtHumanDeckSize, txtComputerDeckSize, txtInfo;
    private View[] computerFieldViews, handViews, humanFieldViews,
                   computerFieldViewFrames, handViewFrames, humanFieldViewFrames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        // Get game instance
        game = Game.getInstance();

        // Get views and button
        txtHumanDeckSize = (TextView) findViewById(R.id.txtHumanDeckSize);
        txtComputerDeckSize = (TextView) findViewById(R.id.txtComputerDeckSize);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        btnAction = (Button) findViewById(R.id.btnAction);

        // Get fragments
        getFragments();

        // Get fragment frames
        getFragmentFrames();

        // Set initial text displayed
        txtHumanDeckSize.setText("15/15");
        txtComputerDeckSize.setText("15/15");
        txtInfo.setText("Draw a Card");
        btnAction.setText("Draw");

        // Register listeners
        registerListeners();
    }

    /**
        DisplayCard - Displays a card in a view

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

        v.setVisibility(View.VISIBLE);
    }

    /**
     GetFragments - Gets the fragments and assigns them to the fields
     */

    private void getFragments() {
        computerFieldViews = new View[game.getBattlefield().getFieldSize()];
        computerFieldViews[0] = findViewById(R.id.fragComputerField1);
        computerFieldViews[0].setVisibility(View.INVISIBLE);
        computerFieldViews[1] = findViewById(R.id.fragComputerField2);
        computerFieldViews[1].setVisibility(View.INVISIBLE);

        handViews = new View[game.getHumanPlayer().getHandSize()];
        handViews[0] = findViewById(R.id.fragHand1);
        handViews[0].setVisibility(View.INVISIBLE);
        handViews[1] = findViewById(R.id.fragHand2);
        handViews[1].setVisibility(View.INVISIBLE);
        handViews[2] = findViewById(R.id.fragHand3);
        handViews[2].setVisibility(View.INVISIBLE);

        humanFieldViews = new View[game.getBattlefield().getFieldSize()];
        humanFieldViews[0] = findViewById(R.id.fragHumanField1);
        humanFieldViews[0].setVisibility(View.INVISIBLE);
        humanFieldViews[1] = findViewById(R.id.fragHumanField2);
        humanFieldViews[1].setVisibility(View.INVISIBLE);
    }

    /**
     GetFragmentFrames - Gets the frames of the fragments and assigns them to the fields
     */

    private void getFragmentFrames() {
        computerFieldViewFrames = new View[game.getBattlefield().getFieldSize()];
        computerFieldViewFrames[0] = findViewById(R.id.fragComputerField1Frame);
        computerFieldViewFrames[1] = findViewById(R.id.fragComputerField2Frame);

        handViewFrames = new View[game.getHumanPlayer().getHandSize()];
        handViewFrames[0] = findViewById(R.id.fragHand1Frame);
        handViewFrames[1] = findViewById(R.id.fragHand2Frame);
        handViewFrames[2] = findViewById(R.id.fragHand3Frame);

        humanFieldViewFrames = new View[game.getBattlefield().getFieldSize()];
        humanFieldViewFrames[0] = findViewById(R.id.fragHumanField1Frame);
        humanFieldViewFrames[1] = findViewById(R.id.fragHumanField2Frame);
    }

    /**
     GetFragmentIndex - Returns the index of the fragment that lives in the specified frame

     @param frame The frame View
     @return The index of the fragment that lives in the frame View
     */

    private int getFragmentIndex(View frame) {
        int index = 0;
        boolean found = false;

        for (int i = 0; i < computerFieldViewFrames.length && !found; i++)
            if (computerFieldViewFrames[i] == frame) {
                index = i;
                found = true;
            }

        for (int i = 0; i < handViewFrames.length && !found; i++)
            if (handViewFrames[i] == frame) {
                index = i;
                found = true;
            }

        for (int i = 0; i < humanFieldViewFrames.length && !found; i++)
            if (humanFieldViewFrames[i] == frame) {
                index = i;
                found = true;
            }

        return index;
    }

    /**
        RegisterListeners - Registers listeners to the controls
     */

    private void registerListeners() {
        for (View view : computerFieldViewFrames) {
            view.setOnClickListener(new ComputerFieldClickHandler());
            view.setOnLongClickListener(new LongClickHandler());
        }
        for (View view : handViewFrames) {
            view.setOnClickListener(new CardHandClickHandler());
            view.setOnLongClickListener(new LongClickHandler());
        }
        for (View view : humanFieldViewFrames) {
            view.setOnClickListener(new HumanFieldClickHandler());
            view.setOnLongClickListener(new LongClickHandler());
        }
        btnAction.setOnClickListener(new ButtonHandler());
    }

    /**
     RemoveListeners - Removes listeners from the controls
     */

    private void removeListeners() {
        for (View view : computerFieldViewFrames) {
            view.setOnClickListener(null);
            view.setOnLongClickListener(null);
        }
        for (View view : handViewFrames) {
            view.setOnClickListener(null);
            view.setOnLongClickListener(null);
        }
        for (View view : humanFieldViewFrames) {
            view.setOnClickListener(null);
            view.setOnLongClickListener(null);
        }
        btnAction.setOnClickListener(null);
    }

    /**
     OnBattleComputerViewClick - Handles the logic of the user clicking a location on the field
     to select a card to attack

     @param v The View clicked
     */

    private void onBattleComputerViewClick(View v) {
        if (game.getBattlefield().getSelectedIndex(PlayerType.HUMAN) != -1) {
            // Get index of fragment
            int index = getFragmentIndex(v);

            // Check that valid selection made
            if (computerFieldViews[index].getVisibility() == View.VISIBLE) {
                // Player makes selection
                game.getHumanPlayer().selectBattlefieldLocation(index, PlayerType.COMPUTER);

                // Player attacks
                Attack attack = game.doAttack(game.getHumanPlayer());

                // Update display
                Card attacker = attack.getAttacker();
                Card defender = attack.getDefender();

                if (attacker.getDefense() > 0)
                    ((TextView) humanFieldViews[attack.getAttackerIndex()].findViewById(
                            R.id.txtDefense)).setText(String.valueOf(attacker.getDefense()));
                else
                    undisplayCard(humanFieldViews[attack.getAttackerIndex()]);

                if (defender.getDefense() > 0)
                    ((TextView) computerFieldViews[index].findViewById(R.id.txtDefense)).setText(
                            String.valueOf(defender.getDefense()));
                else
                    undisplayCard(computerFieldViews[index]);

                humanFieldViews[attack.getAttackerIndex()].setBackground(getResources().
                    getDrawable(R.drawable.custom_background));
            }
        }
    }

    /**
     OnBattleHumanViewClick - Handles the logic of the user clicking a location on the field
     to select a card to attack with

     @param v The View clicked
     */

    private void onBattleHumanViewClick(View v) {
        // Get index of fragment
        int index = getFragmentIndex(v);

        // Check that a valid card has been selected
        if (humanFieldViews[index].getVisibility() == View.VISIBLE) {
            // Check that selected card can attack
            if (game.getBattlefield().canCardAttack(index, PlayerType.HUMAN)) {
                // Player selects card to attack with
                game.getHumanPlayer().selectBattlefieldLocation(index, PlayerType.HUMAN);

                // Update display
                for (View view : humanFieldViews) {
                    if (view != humanFieldViews[index] && view.getVisibility() == View.VISIBLE)
                        view.setBackground(getResources().getDrawable(
                                R.drawable.custom_background));
                    else
                        view.setBackground(getResources().getDrawable(R.drawable.card_selected));
                }
            }
        }
    }

    /**
     OnCastClick - Handles the logic of the user clicking a location to cast a card in

     @param v The View clicked
     */

    private void onCastClick(View v) {
        // Get index of fragment
        int index = getFragmentIndex(v);

        // Check that valid conditions exist
        Player player = game.getHumanPlayer();
        if (player.getSelectedCardIndex() != -1) {
            // Player selects battlefield index
            player.selectBattlefieldLocation(index, PlayerType.HUMAN);

            // Cast
            Cast cast = game.doCast(player);

            // Update display
            undisplayCard(handViews[cast.getHandIndex()]);
            displayCard(cast.getCardCast(), humanFieldViews[index]);

            toBattlePhase();
        }
    }

    /**
     ToBattlePhase - Proceeds to the battle phase
     */

    private void toBattlePhase() {
        txtInfo.setText("Attack with your cards, or end turn");
        btnAction.setText("End Turn");
    }

    /**
     ToCastPhase - Proceeds to the cast phase
     */

    private void toCastPhase() {
        txtInfo.setText("Cast a card, or proceed to battle phase");
        btnAction.setText("Battle");
    }

    /**
     ToComputerTurn - Proceeds to the computer's turn
     */

    private void toComputerTurn() {
        TurnResult res = game.doComputerTurn();

        // Update views
        if (res.getDidDraw())
            txtComputerDeckSize.setText(game.getComputerPlayer().getDeckSize() + "/15");

        if (res.getDidCast()) {
            Cast cast = res.getCast();
            displayCard(cast.getCardCast(), computerFieldViews[cast.getBattlefieldIndex()]);
        }

        if (res.getDidAttack()) {
            for (Attack attack : res.getAttacks()) {
                if (attack.getAttacker().getDefense() > 0)
                    ((TextView)computerFieldViews[attack.getAttackerIndex()].findViewById(
                            R.id.txtDefense)).setText(String.valueOf(
                            attack.getAttacker().getDefense()));
                else
                    undisplayCard(computerFieldViews[attack.getAttackerIndex()]);

                if (attack.getDefender().getDefense() > 0)
                    ((TextView)humanFieldViews[attack.getDefenderIndex()].findViewById(
                            R.id.txtDefense)).setText(String.valueOf(
                                attack.getDefender().getDefense()));
                else
                    undisplayCard(humanFieldViews[attack.getDefenderIndex()]);
            }
        }
    }

    /**
     ToDrawPhase - Proceeds to the draw phase
     */

    private void toDrawPhase() {
        txtInfo.setText("Draw a card");
        btnAction.setText("Draw");
    }

    /**
     UndisplayCard - Undisplays a card in a view

     @param v The View to undisplay card from
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
        v.setVisibility(View.INVISIBLE);
    }

    /**
     Handler for button
     */

    private class ButtonHandler implements Button.OnClickListener {
        /**
         OnClick method

         @param v The View
         */

        @Override
        public void onClick(View v) {
            // Determine action to perform
            String action = ((Button)v).getText().toString();
            switch (action) {
                case "Draw":
                    Player player = game.getHumanPlayer();
                    Draw draw = game.doDraw(player);
                    displayCard(draw.getCardDrawn(), handViews[draw.getIndex()]);
                    txtHumanDeckSize.setText(player.getDeckSize() + "/15");
                    toCastPhase();
                    break;
                case "Battle":
                    toBattlePhase();
                    game.getHumanPlayer().selectCardInHand(-1);
                    for (View view : handViews)
                        view.setBackground(getBaseContext().getResources().getDrawable(
                                R.drawable.custom_background));
                    game.setPhase(Phase.BATTLE);
                    break;
                case "End Turn":
                    // Update view
                    for (View view : humanFieldViews)
                        view.setBackground(getBaseContext().getResources().getDrawable(
                                R.drawable.custom_background));

                    // Check if game is over
                    if (game.getIsGameOver()) {
                        txtInfo.setText("Game is over!");
                        btnAction.setText(null);
                        removeListeners();
                    }
                    else
                        toComputerTurn();

                    // Check if game is over
                    if (game.getIsGameOver()) {
                        txtInfo.setText("Game is over!");
                        btnAction.setText(null);
                        removeListeners();
                    }
                    else {
                        // Check if human can draw
                        if (game.getHumanPlayer().getDeckSize() != 0)
                            toDrawPhase();
                        // Check if human can cast
                        else if (game.getHumanPlayer().getNumInHand() != 0) {
                            game.setPhase(Phase.CAST);
                            toCastPhase();
                        }
                        // To battle
                        else {
                            game.setPhase(Phase.BATTLE);
                            toBattlePhase();
                        }
                    }
                    break;
            }
        }
    }

    /**
     Click handler for computer field views
     */

    private class ComputerFieldClickHandler implements View.OnClickListener {
        /**
         OnClick method

         @param v The view
         */

        @Override
        public void onClick(View v) {
            if (game.getPhase() == Phase.BATTLE) {
                onBattleComputerViewClick(v);
            }
        }
    }

    /**
     Click handler for human field views
     */

    private class HumanFieldClickHandler implements View.OnClickListener {
        /**
         OnClick method

         @param v The View
         */

        @Override
        public void onClick(View v) {
            if (game.getPhase() != Phase.DRAW) {
                //
                if (game.getPhase() == Phase.CAST)
                    onCastClick(v);

                if (game.getPhase() == Phase.BATTLE)
                    onBattleHumanViewClick(v);
            }
        }
    }

    /**
     Click handler for human's hand views
     */

    private class CardHandClickHandler implements View.OnClickListener {
        /**
         OnClick method
         */

        @Override
        public void onClick(View v) {
            if (game.getPhase() == Phase.CAST) {
                // Get index of fragment
                int index = getFragmentIndex(v);

                // Check that fragment is selectable
                if (handViews[index].getVisibility() == View.VISIBLE) {
                    // Player selects card
                    game.getHumanPlayer().selectCardInHand(index);

                    // Set backgrounds
                    for (View view : handViews) {
                        if (view != handViews[index] && view.getVisibility() == View.VISIBLE)
                            view.setBackground(getBaseContext().getResources().
                                    getDrawable(R.drawable.custom_background));
                        else
                            view.setBackground(getBaseContext().getResources().
                                    getDrawable(R.drawable.card_selected));
                    }
                }
            }
        }
    }

    /**
     Long click handler for card views
     */
    private class LongClickHandler implements View.OnLongClickListener {
        /**
         OnLongClick method
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

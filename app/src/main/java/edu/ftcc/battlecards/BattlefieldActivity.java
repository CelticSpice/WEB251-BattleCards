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
    private View[] computerFieldViews, handViews, humanFieldViews;

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

        // Set initial text displayed
        txtHumanDeckSize.setText("15/15");
        txtComputerDeckSize.setText("15/15");
        txtInfo.setText("Draw a Card");
        btnAction.setText("Draw");

        // Register button listeners
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
        The registerListeners method registers listeners to the controls
     */

    private void registerListeners() {
        for (View view : computerFieldViews) {
            view.setOnClickListener(new ComputerFieldClickHandler());
            view.setOnLongClickListener(new LongClickHandler());
        }
        for (View view : handViews) {
            view.setOnClickListener(new CardHandClickHandler());
            view.setOnLongClickListener(new LongClickHandler());
        }
        for (View view : humanFieldViews) {
            view.setOnClickListener(new HumanFieldClickHandler());
            view.setOnLongClickListener(new LongClickHandler());
        }
        btnAction.setOnClickListener(new ButtonHandler());
    }

    /**
     OnBattleComputerViewClick - Handles the logic of the user clicking a location on the field
     to select a card to attack

     @param v The View clicked
     */

    private void onBattleComputerViewClick(View v) {
        if (game.getBattlefield().getSelectedIndex(PlayerType.HUMAN) != -1) {
            // Get index of view
            int index = 0;
            for (int i = 0; i < computerFieldViews.length; i++)
                if (computerFieldViews[i] == v)
                    index = i;

            // Player makes selection
            game.getHumanPlayer().selectBattlefieldLocation(index, PlayerType.COMPUTER);

            // Player attacks
            Attack attack = game.doAttack(PlayerType.HUMAN);

            // Update display
            Card attacker = attack.getAttacker();
            Card defender = attack.getDefender();

            if (attacker.getDefense() > 0)
                ((TextView) humanFieldViews[index].findViewById(R.id.txtDefense)).setText(
                        String.valueOf(attacker.getDefense()));
            else
                undisplayCard(humanFieldViews[index]);

            if (defender.getDefense() > 0)
                ((TextView) computerFieldViews[index].findViewById(R.id.txtDefense)).setText(
                        String.valueOf(defender.getDefense()));
            else
                undisplayCard(computerFieldViews[attack.getDefenderIndex()]);
        }
    }

    /**
     OnBattleHumanViewClick - Handles the logic of the user clicking a location on the field
     to select a card to attack with

     @param v The View clicked
     */

    private void onBattleHumanViewClick(View v) {
        // Get index of view
        int index = 0;
        for (int i = 0; i < humanFieldViews.length; i++)
            if (humanFieldViews[i] == v)
                index = i;

        // Check that selected card can attack
        if (game.getBattlefield().canCardAttack(index, PlayerType.HUMAN)) {
            // Player selects card to attack with
            game.getHumanPlayer().selectBattlefieldLocation(index, PlayerType.HUMAN);

            // Update display
            for (View view : humanFieldViews) {
                if (view != v && view.getVisibility() == View.VISIBLE)
                    view.setBackground(getResources().getDrawable(R.drawable.custom_background));
                else
                    view.setBackground(getResources().getDrawable(R.drawable.card_selected));
            }
        }
    }

    /**
     OnCastClick - Handles the logic of the user clicking a location to cast a card in

     @param v The View clicked
     */

    private void onCastClick(View v) {
        if (!game.getIsCardCast()) {
            // Get index of view
            int index = 0;
            for (int i = 0; i < humanFieldViews.length; i++)
                if (humanFieldViews[i] == v)
                    index = i;

            // Check that valid conditions exist
            Player player = game.getHumanPlayer();
            if (player.getSelectedCardIndex() != -1) {
                // Player selects battlefield index
                player.selectBattlefieldLocation(index, PlayerType.HUMAN);

                // Cast
                Cast cast = game.doCast(PlayerType.HUMAN);

                // Update display
                undisplayCard(handViews[cast.getHandIndex()]);
                displayCard(cast.getCardCast(), humanFieldViews[cast.getBattlefieldIndex()]);

                // Set card as cast
                game.setIsCardCast(true);
            }
        }
    }

    /**
     ToBattlePhase - Proceeds to the battle phase
     */

    private void toBattlePhase() {
        game.setPhase(Phase.BATTLE);
        txtInfo.setText("Attack with your cards");
        btnAction.setText("End Turn");
    }

    /**
     ToCastPhase - Proceeds to the cast phase
     */

    private void toCastPhase() {
        game.setPhase(Phase.CAST);
        txtInfo.setText("Cast a card, or proceed to battle phase");
        btnAction.setText("Battle");
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
         onClick method
         */

        @Override
        public void onClick(View v) {
            // Determine action to perform
            String action = ((Button)v).getText().toString();
            switch (action) {
                case "Draw":
                    Draw draw = game.doDraw(PlayerType.HUMAN);
                    if (draw.getDrawSuccessful())
                        displayCard(draw.getCardDrawn(), handViews[draw.getIndex()]);
                    txtHumanDeckSize.setText(game.getHumanPlayer().getDeckSize() + "/15");
                    toCastPhase();
                    break;
                case "Battle":
                    toBattlePhase();
                    break;
                case "End Turn":
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
            if (game.getPhase() == Phase.BATTLE && v.getVisibility() == View.VISIBLE) {
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

         @param v The view
         */

        @Override
        public void onClick(View v) {
            if (game.getPhase() != Phase.DRAW && v.getVisibility() == View.VISIBLE) {
                if (game.getPhase() == Phase.CAST)
                    onCastClick(v);
                else
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
            if (game.getPhase() == Phase.CAST && v.getVisibility() == View.VISIBLE) {
                // Get index of clicked view
                int index = 0;
                for (int i = 0; i < handViews.length; i++)
                    if (handViews[i] == v)
                        index = i;

                // Player selects card
                game.getHumanPlayer().selectCardInHand(index);

                // Set backgrounds
                for (View view : handViews) {
                    if (view != v && view.getVisibility() == View.VISIBLE)
                        v.setBackground(getBaseContext().getResources().getDrawable(
                                R.drawable.custom_background));
                    else
                        v.setBackground(getBaseContext().getResources().getDrawable(
                                R.drawable.card_selected));
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

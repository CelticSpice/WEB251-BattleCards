package edu.ftcc.battlecards;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BattlefieldActivity extends AppCompatActivity {
    // Fields
    private Button btnAction;
    private Card humanField1, humanField2, computerField1, computerField2;
    private Player humanPlayer, computerPlayer;
    private TextView txtPlayerDeckSize, txtComputerDeckSize, txtInfo;
    private View humanField1View, humanField2View, computerField1View, computerField2View;
    private View[] handViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        // Get game instance
        Game game = Game.getInstance();

        // Get players
        humanPlayer = game.getHumanPlayer();
        computerPlayer = game.getComputerPlayer();

        // Get views and button
        txtPlayerDeckSize = (TextView) findViewById(R.id.txtPlayerDeckSize);
        txtComputerDeckSize = (TextView) findViewById(R.id.txtOpponentDeckSize);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        btnAction = (Button) findViewById(R.id.btnAction);

        handViews = new View[Player.HAND_SIZE];
        handViews[0] = findViewById(R.id.fragmentHand1);
        handViews[1] = findViewById(R.id.fragmentHand2);
        handViews[2] = findViewById(R.id.fragmentHand3);

        humanField1View = findViewById(R.id.fragmentPlayerField1);
        humanField2View = findViewById(R.id.fragmentPlayerField2);
        computerField1View = findViewById(R.id.fragmentOpponentField1);
        computerField2View = findViewById(R.id.fragmentOpponentField2);

        // Set initial text displayed
        txtPlayerDeckSize.setText("15/15");
        txtComputerDeckSize.setText("15/15");
        txtInfo.setText("Draw a Card");
        btnAction.setText("Draw");

        // Register listener to button
        btnAction.setOnClickListener(new ButtonHandler());
    }

    /**
        The displayCard method displays a specified card in the specified fragment

        @param card Card to display
        @param frag Fragment to display card in
     */

    private void displayCard(Card card, View frag) {
        LinearLayout layout = (LinearLayout) frag;

        ((TextView) layout.findViewById(R.id.txtName)).setText(card.getName());

        ((ImageView) layout.findViewById(R.id.imgImage)).setImageResource(card.getImageResID());

        ((TextView) layout.findViewById(R.id.txtAttack)).setText(String.valueOf(card.getAttack()));

        ((TextView) layout.findViewById(R.id.txtDefense)).
                setText(String.valueOf(card.getDefense()));

        String ability = card.getAbility().toString().toLowerCase();
        ability = ability.replace('_', ' ');
        if (!ability.equals("none"))
            ((TextView)layout.findViewById(R.id.txtAbility)).setText(ability);

        ((TextView)layout.findViewById(R.id.txtResource)).
                setText(String.valueOf(card.getResourceCost()));

        ((TextView)layout.findViewById(R.id.txtGold)).setText(String.valueOf(card.getGoldCost()));
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
            // Determine action command of button
            String action = ((Button)v).getText().toString();
            switch (action) {
                case "Draw":
                    // Player draws a card
                    Card drawn = humanPlayer.draw();

                    // Update deck size
                    txtPlayerDeckSize.setText(String.valueOf(humanPlayer.getDeckSize()) + "/15");

                    // If there is room in hand, place in player's hand
                    int numInHand = humanPlayer.getNumInHand();
                    if (numInHand < Player.HAND_SIZE) {
                        int index = humanPlayer.addToHand(drawn);
                        displayCard(drawn, handViews[index]);
                    }
                    break;
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
            Game game = Game.getInstance();

        }
    }
}

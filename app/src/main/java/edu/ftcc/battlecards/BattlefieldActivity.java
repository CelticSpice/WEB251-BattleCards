package edu.ftcc.battlecards;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BattlefieldActivity extends AppCompatActivity {
    // Fields
    Game game;
    Player humanPlayer, computerPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        // Get game instance
        game = Game.getInstance();

        // Get players
        humanPlayer = game.getHumanPlayer();
        computerPlayer = game.getComputerPlayer();

        // Human player draws card
        Card drawnCard = humanPlayer.draw();

        // Display card
        LinearLayout layout = (LinearLayout) findViewById(R.id.fragmentHand1);
        ((TextView) layout.findViewById(R.id.txtName)).setText(drawnCard.getName());
        ((ImageView) layout.findViewById(R.id.imgImage)).setImageResource(drawnCard.getImageResID());
        ((TextView) layout.findViewById(R.id.txtAttack)).setText(String.valueOf(drawnCard.getAttack()));
        ((TextView) layout.findViewById(R.id.txtDefense)).setText(String.valueOf(drawnCard.getDefense()));
        ((TextView)layout.findViewById(R.id.txtAbility)).setText(drawnCard.getAbility().toString());
        ((TextView)layout.findViewById(R.id.txtResource)).setText(String.valueOf(drawnCard.getResourceCost()));
        ((TextView)layout.findViewById(R.id.txtGold)).setText(String.valueOf(drawnCard.getGoldCost()));
    }
}

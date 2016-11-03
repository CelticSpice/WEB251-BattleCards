/**
 This class enables a detailed view of a Card
 9/23/2016
 WEB 251 0001 - Battle Cards (M5HW1)
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class CardDetailActivity extends AppCompatActivity {
    // Fields
    public static final String CARD_INDEX = "cardIndex";

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        // Get game
        game = Game.getInstance();

        // Get extra
        int cardIndex = (Integer) getIntent().getExtras().get(CARD_INDEX);

        // Get the selected Card
        Card card = game.getCards().getCard(cardIndex);

        // Get controls to populate
        ImageView imgCardImage = (ImageView) findViewById(R.id.imgCardImage);
        TextView txtName = (TextView) findViewById(R.id.txtName);
        TextView txtDefense = (TextView) findViewById(R.id.txtDefense);
        TextView txtAbility = (TextView) findViewById(R.id.txtAbility);
        TextView txtResource = (TextView) findViewById(R.id.txtResource);
        TextView txtGold = (TextView) findViewById(R.id.txtGold);
        TextView txtAttack = (TextView) findViewById(R.id.txtAttack);

        // Populate controls with appropriate data
        imgCardImage.setImageResource(card.getImage());
        txtName.setText(card.getName());
        txtDefense.setText(String.valueOf(card.getDefense()));
        txtAbility.setText(card.getCombatAbility().toString());
        txtResource.setText(String.valueOf(card.getResourceCost()));
        txtGold.setText(String.valueOf(card.getGoldCost()));
        txtAttack.setText(String.valueOf(card.getAttack()));
    }
}

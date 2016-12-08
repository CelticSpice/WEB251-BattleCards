/**
 This class displays a detailed view of a card
 12/8/2016
 WEB 251 0001 - M5PROJ
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

public class CardDetailActivity extends AppCompatActivity {
    // Fields
    public static final String CARD_INDEX = "cardIndex";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        // Get game instance
        Game game = Game.getInstance();

        // Get extra
        int cardIndex = (Integer) getIntent().getExtras().get(CARD_INDEX);

        // Get the selected Card
        Card card = game.getCardAt(cardIndex);

        // Get controls to populate
        ImageView imgCardImage = (ImageView) findViewById(R.id.imgImage);
        TextView txtName = (TextView) findViewById(R.id.txtName);
        TextView txtDefense = (TextView) findViewById(R.id.txtDefense);
        TextView txtAbility = (TextView) findViewById(R.id.txtAbility);
        TextView txtResource = (TextView) findViewById(R.id.txtResource);
        TextView txtGold = (TextView) findViewById(R.id.txtGold);
        TextView txtAttack = (TextView) findViewById(R.id.txtAttack);

        // Populate controls with appropriate data
        imgCardImage.setImageResource(card.getImageResID());
        txtName.setText(card.getName());
        txtDefense.setText(String.valueOf(card.getDefense()));

        String ability = card.getAbility().toString().toLowerCase();
        ability = ability.replace('_', ' ');
        if (!ability.equals("none"))
            txtAbility.setText(ability);

        txtResource.setText(String.valueOf(card.getResourceCost()));
        txtGold.setText(String.valueOf(card.getGoldCost()));
        txtAttack.setText(String.valueOf(card.getAttack()));
    }
}

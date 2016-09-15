package edu.ftcc.battlecards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DeckCreatorActivity extends AppCompatActivity {
    // Fields
    private Card[] gameCards;
    private Player humanPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_creator);

        humanPlayer = new Player();

        prepareGameCards();
        setCardsToBuy();

        TextView goldTxt = (TextView) findViewById(R.id.goldAmountTxt);
        goldTxt.setText(String.valueOf(humanPlayer.getGold()));
    }

    /**
        The prepareGameCards method prepares the cards available in the game
     */

    private void prepareGameCards() {
        final int NUMBER_OF_CARDS = 5;

        gameCards = new Card[NUMBER_OF_CARDS];

        gameCards[0] = new Card("Goblin", 5, 4, 2, 2, "None");
        gameCards[1] = new Card("Orc", 7, 3, 5, 4, "First Strike");
        gameCards[2] = new Card("Undead", 2, 5, 3, 3, "None");
        gameCards[3] = new Card("Kobold", 3, 3, 2, 3, "None");
        gameCards[4] = new Card("Dragon", 25, 75, 15, 15, "First Strike");
    }

    /**
        The setCardsToBuy method sets the cards which are available to buy, based off of the
        player's current amount of gold
     */

    private void setCardsToBuy() {
        ArrayList<String> cardsAvailableToBuy = new ArrayList<>();

        for (Card card : gameCards) {
            if (card.getGoldCost() <= humanPlayer.getGold()) {
                cardsAvailableToBuy.add(card.getCardName());
            }
        }

        ListView toBuyList = (ListView) findViewById(R.id.cardsToBuyList);
        String[] names = cardsAvailableToBuy.toArray(new String[cardsAvailableToBuy.size()]);
        toBuyList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                                                names));
    }
}

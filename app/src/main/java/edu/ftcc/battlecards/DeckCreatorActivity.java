/**
 This class enables the user to build his Deck of Cards for the game
 9/23/2016
 WEB 251 0001 - Battle Cards (M5HW1)
 @author James Alves, Timothy Burns
 */

package edu.ftcc.battlecards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DeckCreatorActivity extends AppCompatActivity {
    // Fields
    private Game game;
    private Button btnStart;
    private ListView toBuyList, boughtList;
    private TextView txtGold, txtNumCardsBought;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_creator);

        // Initialization
        game = Game.getInstance();
        btnStart = (Button) findViewById(R.id.btnStart);
        toBuyList = (ListView) findViewById(R.id.listToBuy);
        boughtList = (ListView) findViewById(R.id.listBought);
        registerListListeners();
        setCardsToBuy();

        // Display initial amount of cards bought
        txtNumCardsBought = (TextView) findViewById(R.id.txtNumCardsBought);
        txtNumCardsBought.setText("0/15");

        // Display initial amount of gold that the player has
        txtGold = (TextView) findViewById(R.id.txtGoldAmount);
        txtGold.setText(String.valueOf(game.getHumanPlayer().getGold()));
    }

    /**
        The setCardsBought method updates the boughtList with the names of cards that the Player
        has bought
     */

    private void setCardsBought() {
        final Card[] BOUGHT = game.getHumanPlayer().getDeck().toArray();

        boughtList.setAdapter(new ArrayAdapter<Card>(this, android.R.layout.simple_list_item_2,
                android.R.id.text1, BOUGHT) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(BOUGHT[position].getName());
                text2.setText((String.valueOf(BOUGHT[position].getGoldCost())));
                return view;
            }
        });
    }

    /**
     The setCardsToBuy method updates listToBuy with the names of cards that the Player
     can buy
     */

    private void setCardsToBuy() {
        // Get cards available to buy
        int gold = game.getHumanPlayer().getGold();
        final Card[] AVAILABLE = game.getCardsAvailableToBuy(gold);

        // Set ArrayAdapter
        toBuyList.setAdapter(new ArrayAdapter<Card>(this, android.R.layout.simple_list_item_2,
                android.R.id.text1, AVAILABLE) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(AVAILABLE[position].getName());
                text2.setText((String.valueOf(AVAILABLE[position].getGoldCost())));
                return view;
            }
        });
    }

    /**
     The registerListListeners method registers listeners to the lists
     */

    private void registerListListeners() {
        toBuyList.setOnItemLongClickListener(new ListItemLongClickListener());
        boughtList.setOnItemLongClickListener(new ListItemLongClickListener());

        toBuyList.setOnItemClickListener(new ListItemClickListener());
        boughtList.setOnItemClickListener(new ListItemClickListener());
    }

    /**
        Handler for ListView click
     */

    private class ListItemClickListener implements ListView.OnItemClickListener {
        /**
            onItemClick method
         */

        @Override
        public void onItemClick(AdapterView adapterView, View view, int pos, long id) {
            // Get the selected card's name
            String name = (String) ((TextView)view.findViewById(android.R.id.text1)).getText();

            // Determine whether we are buying or selling
            if (adapterView == toBuyList) {
                game.getHumanPlayer().buyCard(game.getMasterDeck().getCard(name));
                setCardsToBuy();
                setCardsBought();
            }
            else {
                game.getHumanPlayer().sellCard(game.getHumanPlayer().getDeck().getCard(name));
                setCardsToBuy();
                setCardsBought();
            }

            // Update cards bought amount
            int numBought = game.getHumanPlayer().getDeck().getNumCards();
            txtNumCardsBought.setText(String.valueOf(numBought) + "/15");

            // Update gold amount
            txtGold.setText(String.valueOf(game.getHumanPlayer().getGold()));

            // Check if minimum number of cards bought
            if (numBought >= 15)
            {
                btnStart.setEnabled(true);
                toBuyList.setEnabled(false);
            }
            else
            {
                btnStart.setEnabled(false);
                toBuyList.setEnabled(true);
            }
        }
    }

    /**
        Handler for ListView long click
     */

    private class ListItemLongClickListener implements ListView.OnItemLongClickListener {
        /**
            onItemLongClick method
         */

        @Override
        public boolean onItemLongClick(AdapterView adapterView, View view, int pos, long id) {
            // Get the selected card's name
            String name = (String) ((TextView)view.findViewById(android.R.id.text1)).getText();

            // Get the selected card's master index
            int cardIndex = game.getMasterDeck().getCardIndex(name);

            // Open activity to display card details
            Intent intent = new Intent(DeckCreatorActivity.this, CardDetailActivity.class);
            intent.putExtra(CardDetailActivity.CARD_INDEX, cardIndex);
            startActivity(intent);

            return true;
        }
    }
}

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DeckCreatorActivity extends AppCompatActivity {
    // Fields
    private Game game;
    private ListView toBuyList, boughtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_creator);

        // Initialization
        game = new Game();
        toBuyList = (ListView) findViewById(R.id.listToBuy);
        boughtList = (ListView) findViewById(R.id.listBought);
        registerListListeners();
        setCardsToBuy();

        // Display initial amount of gold that the player has
        TextView txtGoldAmount = (TextView) findViewById(R.id.txtGoldAmount);
        txtGoldAmount.setText(String.valueOf(game.getPlayer(PlayerType.HUMAN).getGold()));
    }

    /**
        The setCardsBought method updates the boughtList with the names of Cards that the Player
        has bought
     */

    private void setCardsBought() {
        boughtList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                game.getPlayer(PlayerType.HUMAN).getDeck().getCardNames()));
    }

    /**
     The setCardsToBuy method updates listToBuy with the names of Cards that the Player
     can buy
     */

    private void setCardsToBuy() {
        // Get cards available to buy
        Card[] available = game.getCardsAvailableToBuy(game.getPlayer(PlayerType.HUMAN).getGold());

        // Get the card names
        String[] names = new String[available.length];

        for (int i = 0; i < available.length; i++)
            names[i] = available[i].getName();

        // Set ArrayAdapter
        toBuyList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                names));
    }

    /**
     The registerListListeners method registers listeners to the lists
     */

    private void registerListListeners() {
        toBuyList.setOnItemLongClickListener(new ListLongClickListener());
        boughtList.setOnItemLongClickListener(new ListLongClickListener());
    }

    /**
        Handler for ListView click
     */

    private class ListClickListener implements ListView.OnItemClickListener {
        /**
            onItemClick method
         */

        @Override
        public void onItemClick(AdapterView adapterView, View view, int pos, long id) {
            // Get the selected Card's name
            String name = (String) adapterView.getSelectedItem();

            // Determine whether we are buying or selling
            if (adapterView == toBuyList) {
                game.getPlayer(PlayerType.HUMAN).buyCard(game.getCards().getCard(name));
                setCardsToBuy();
            }
            else {
                game.getPlayer(PlayerType.HUMAN).sellCard(game.getCards().getCard(name));
                setCardsBought();
            }
        }
    }

    /**
        Handler for ListView long click
     */

    private class ListLongClickListener implements ListView.OnItemLongClickListener {
        /**
            onItemLongClick method
         */

        @Override
        public boolean onItemLongClick(AdapterView adapterView, View view, int pos, long id) {
            // Get the selected Card's name
            String name = (String) adapterView.getItemAtPosition(pos);

            // Get the selected Card's game index
            int cardIndex = game.getCards().getCardIndex(name);

            // Open activity to display Card details
            Intent intent = new Intent(DeckCreatorActivity.this, CardDetailActivity.class);
            intent.putExtra(CardDetailActivity.CARD_INDEX, game);
            startActivity(intent);

            // I'm not sure what this is, but the abstract method onItemLongClick()
            // requires that we return a boolean
            return true;
        }
    }
}

package com.timbau3285.myinventory.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.timbau3285.myinventory.R;
import com.timbau3285.myinventory.data.InventoryDbHelper;
import com.timbau3285.myinventory.data.StockItem;
import com.timbau3285.myinventory.database.SharedPreference;

public class MainActivity extends AppCompatActivity {

    SharedPreference userInfo;
    Intent i;


    private final static String LOG_TAG = MainActivity.class.getCanonicalName();
    InventoryDbHelper dbHelper;
    StockCursorAdapter adapter;
    int lastVisibleItem = 0;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new InventoryDbHelper(this);

        userInfo = new SharedPreference(this);

        if (!userInfo.getState()){
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        dbHelper = new InventoryDbHelper(this);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = dbHelper.readStock();

        adapter = new StockCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == 0) return;
                final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                if (currentFirstVisibleItem > lastVisibleItem) {
                    fab.show();
                } else if (currentFirstVisibleItem < lastVisibleItem) {
                    fab.hide();
                }
                lastVisibleItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override public void onBackPressed(){
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            userInfo.clearUserPreference();
            i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.swapCursor(dbHelper.readStock());
    }

    public void clickOnViewItem(long id) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void clickOnSale(long id, int quantity) {
        dbHelper.sellOneItem(id, quantity);
        adapter.swapCursor(dbHelper.readStock());
    }

//    /**
//     * Add data for demo purposes
//     */
//    private void addDummyData() {
//        StockItem gummibears = new StockItem(
//                "Gummibears",
//                "10 $",
//                45,
//                "Haribo GmbH",
//                "+49 000 000 0000",
//                "haribo@sweet.com",
//                "android.resource://com.timbau3285.myinventory/drawable-xxxhdpi/gummibear");
//        dbHelper.insertItem(gummibears);
//
//        StockItem peaches = new StockItem(
//                "Peaches",
//                "10 €",
//                24,
//                "Haribo GmbH",
//                "+49 000 000 0000",
//                "haribo@sweet.com",
//                "android.resource://com.timbau3285.myinventory/drawable-xxxhdpi/peach");
//        dbHelper.insertItem(peaches);
//
//        StockItem cherries = new StockItem(
//                "Cherries",
//                "11 €",
//                74,
//                "Haribo GmbH",
//                "+49 000 000 0000",
//                "haribo@sweet.com",
//                "android.resource://com.timbau3285.myinventory/drawable-xxxhdpi/cherry");
//        dbHelper.insertItem(cherries);
//
//        StockItem cola = new StockItem(
//                "Cola",
//                "13 €",
//                44,
//                "Haribo GmbH",
//                "+49 000 000 0000",
//                "haribo@sweet.com",
//                "android.resource://com.timbau3285.myinventory/drawable-xxxhdpi/cola");
//        dbHelper.insertItem(cola);
//
//        StockItem fruitSalad = new StockItem(
//                "Fruit salad",
//                "20 €",
//                34,
//                "Haribo GmbH",
//                "+49 000 000 0000",
//                "haribo@sweet.com",
//                "android.resource://com.timbau3285.myinventory/drawable-xxxhdpi/fruit_salad");
//        dbHelper.insertItem(fruitSalad);
//
//        StockItem smurfs = new StockItem(
//                "Smurfs",
//                "12 €",
//                26,
//                "Haribo GmbH",
//                "+49 000 000 0000",
//                "haribo@sweet.com",
//                "android.resource://com.timbau3285.myinventory/drawable-xxxhdpi/smurfs");
//        dbHelper.insertItem(smurfs);
//
//        StockItem fresquito = new StockItem(
//                "Fresquito",
//                "9 €",
//                54,
//                "Fiesta S.A.",
//                "+34 000 000 0000",
//                "fiesta@dulce.com",
//                "android.resource://com.timbau3285.myinventory/drawable-xxxhdpi/fresquito");
//        dbHelper.insertItem(fresquito);
//
//        StockItem hotChillies = new StockItem(
//                "Hot chillies",
//                "13 €",
//                12,
//                "Fiesta S.A.",
//                "+34 000 000 0000",
//                "fiesta@dulce.com",
//                "android.resource://com.timbau3285.myinventory/drawable-xxxhdpi/hot_chillies");
//        dbHelper.insertItem(hotChillies);
//
//        StockItem lolipopStrawberry = new StockItem(
//                "Lolipop strawberry",
//                "12 €",
//                62,
//                "Fiesta S.A.",
//                "+34 000 000 0000",
//                "fiesta@dulce.com",
//                "android.resource://com.timbau3285.myinventory/drawable-xxxhdpi/lolipop");
//        dbHelper.insertItem(lolipopStrawberry);
//
//        StockItem heartGummy = new StockItem(
//                "Heart gummy jellies",
//                "13 €",
//                22,
//                "Fiesta S.A.",
//                "+34 000 000 0000",
//                "fiesta@dulce.com",
//                "android.resource://com.timbau3285.myinventory/drawable-xxxhdpi/heart_gummy");
//        dbHelper.insertItem(heartGummy);
//    }

}

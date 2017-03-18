package edu.asu.msse.hjshah2.geoplacedescription;

import android.support.v7.app.ActionBar;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import org.json.JSONException;
import android.content.Intent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Main2Activity extends AppCompatActivity implements ListView.OnItemClickListener {
    PlaceDescriptionLibrary placeLib;
    ListView listView;
    String[] arr ;
    ArrayAdapter<String> simpleAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.listView);
        String url = "http://10.0.2.2:9090";
        placeLib = new PlaceDescriptionLibrary(this);
        arr = new String[]{"unknown"};
        simpleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(arr)));
        listView.setAdapter(simpleAdapter);
        try{
            MethodInformation mi = new MethodInformation(this, url,"getNames",
                    new String[]{});
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                    ex.getMessage());
        }


        listView.setOnItemClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String itemname = data.getStringExtra("places");
                android.util.Log.d(this.getClass().getSimpleName(), "Returned list item name: " + itemname);

                placeLib = data.getSerializableExtra("places")!=null ? (PlaceDescriptionLibrary) data.getSerializableExtra("places") : new PlaceDescriptionLibrary(this);
                ArrayList<String> arr = (ArrayList<String>) placeLib.getTitles(this);
                simpleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
                listView.setAdapter(simpleAdapter);
                listView.setOnItemClickListener(this);
            }
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( item.getItemId() == R.id.add){
            Intent intent = new Intent(Main2Activity.this,add_activity.class);
            intent.putExtra("places", placeLib);
            this.startActivityForResult(intent,1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = (String) listView.getItemAtPosition(i);
        Intent intq = new Intent(Main2Activity.this, MainActivity.class);
        intq.putExtra("places",placeLib);
        intq.putExtra("name", selected);
        startActivityForResult(intq,1);
    }

}

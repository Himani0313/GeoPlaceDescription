package edu.asu.msse.hjshah2.geoplacedescription;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.acos;
import static java.lang.Math.atan;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/*
 * Copyright 2017 Himani Shah,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * instuctor and the University with the right to build and evaluate the software package for the purpose of determining your grade and program assessment
 *
 * Purpose: Example of single view application
 * Allows to display the objects of a class to access the GUI
 *
 * Ser423 Mobile Applications
 * @author Himani Shah Himani.shah@asu.edu
 *         Software Engineering, CIDSE, ASU Poly
 * @version January 2017
 */
public class MainActivity extends AppCompatActivity {

    EditText name, description, category, adddresstitle, address, elevation, latitude, longitude,distance_edit, bearing;
    Button updatebtn;
    PlaceDescription PlaceDescriptionObject, placeDescriptionObject2;
    public String selectedPlace, seletedPlace2;
    public PlaceDescriptionLibrary pdl;
    public  String itemname;
    Spinner spinner;
    String url = "http://10.0.2.2:9090";
    public void getCompleted (PlaceDescription pdo){
        name.setText(pdo.name);
        description.setText(pdo.description);
        category.setText(pdo.category);
        adddresstitle.setText(pdo.addresstitle);
        address.setText(pdo.address);
        elevation.setText(String.valueOf(pdo.elevation));
        latitude.setText(String.valueOf(pdo.latitude));
        longitude.setText(String.valueOf(pdo.longitude));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pdl = new PlaceDescriptionLibrary(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        updatebtn = (Button)findViewById(R.id.updateBtn);
        name = (EditText)findViewById(R.id.displayName);
        description = (EditText)findViewById(R.id.displayDescription);
        category = (EditText)findViewById(R.id.displayCategory);
        adddresstitle = (EditText)findViewById(R.id.displayAddresstitle);
        address = (EditText)findViewById(R.id.displayAddress);
        elevation = (EditText)findViewById(R.id.displayElevation);
        latitude = (EditText)findViewById(R.id.displayLatitude);
        longitude = (EditText)findViewById(R.id.displayLongitude);
        spinner = (Spinner)findViewById(R.id.spinner);
        distance_edit = (EditText)findViewById(R.id.distance_editText);
        bearing = (EditText)findViewById(R.id.bearing_editText);
        Intent intent = getIntent();
        pdl = intent.getSerializableExtra("places")!=null ? (PlaceDescriptionLibrary) intent.getSerializableExtra("places") :
                new PlaceDescriptionLibrary(this);
        selectedPlace = intent.getStringExtra("name")!=null ? intent.getStringExtra("name") : "unknown";
        try{
            MethodInformation mi = new MethodInformation(this, url,"get",
                    new String[]{selectedPlace});
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                    ex.getMessage());
        }

        //PlaceDescriptionObject = new PlaceDescription ();
        //PlaceDescriptionObject = pdl.getPlaceDescription(selectedPlace);
//        name.setText(PlaceDescriptionObject.name);
//        description.setText(PlaceDescriptionObject.description);
//        category.setText(PlaceDescriptionObject.category);
//        adddresstitle.setText(PlaceDescriptionObject.addresstitle);
//        address.setText(PlaceDescriptionObject.address);
//        elevation.setText(String.valueOf(PlaceDescriptionObject.elevation));
//        latitude.setText(String.valueOf(PlaceDescriptionObject.latitude));
//        longitude.setText(String.valueOf(PlaceDescriptionObject.longitude));

//        ArrayList<String> placeTitleList = (ArrayList<String>) pdl.getTitles(this);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, placeTitleList);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceDescriptionObject.setCategory(category.getText().toString().trim());
                PlaceDescriptionObject.setDescription(description.getText().toString().trim());
                PlaceDescriptionObject.setAddresstitle(adddresstitle.getText().toString().trim());
                PlaceDescriptionObject.setAddress(address.getText().toString().trim());
                PlaceDescriptionObject.setElevation(Double.parseDouble(elevation.getText().toString().trim()));
                PlaceDescriptionObject.setLatitude(Double.parseDouble(latitude.getText().toString().trim()));
                PlaceDescriptionObject.setLongitude(Double.parseDouble(longitude.getText().toString().trim()));

                pdl.update(selectedPlace,PlaceDescriptionObject);
                Intent i = new Intent();
                i.putExtra("places", pdl);
                setResult(RESULT_OK,i);
                finish();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seletedPlace2 = spinner.getSelectedItem().toString();
                placeDescriptionObject2 = pdl.getPlaceDescription(seletedPlace2);
                //calculate_distance();
                //calculate_bearing();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_2, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        android.util.Log.d(this.getClass().getSimpleName(), "called onOptionsItemSelected()");
        switch (item.getItemId()) {
            case R.id.back:
                Intent i = new Intent();
                i.putExtra("places", itemname);
                this.setResult(RESULT_OK, i);
                finish();
                return true;
            case R.id.delete:
                pdl.remove(this,selectedPlace);

                Intent intent = new Intent();
                intent.putExtra("places", pdl);
                this.setResult(RESULT_OK, intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void calculate_distance(){
        double lat1, lat2, long1, long2;
        double radius = 6371;
        double long_difference, central_angle, distance;
        lat1 = PlaceDescriptionObject.latitude;
        lat2 = placeDescriptionObject2.latitude;
        long1 = PlaceDescriptionObject.longitude;
        long2 = placeDescriptionObject2.longitude;
        long_difference = abs(long1-long2);
        double temp = sin(lat1)*sin(lat2);
        double temp2 = cos(lat1)*cos(lat2)*cos(long_difference);
        central_angle= acos(temp+temp2);
        distance = radius * central_angle;

        distance_edit.setText(""+distance);

    }
    public void calculate_bearing(){
        double lat1, lat2, long1, long2;
        double bearing_cal;
        lat1 = PlaceDescriptionObject.latitude;
        lat2 = placeDescriptionObject2.latitude;
        long1 = PlaceDescriptionObject.longitude;
        long2 = placeDescriptionObject2.longitude;

        bearing_cal = atan2(cos(lat1)*sin(lat2)-sin(lat1)*cos(lat2)*cos(long2-long1),sin(long2-long1)*cos(lat2) );
        bearing.setText(""+bearing_cal);
    }
}

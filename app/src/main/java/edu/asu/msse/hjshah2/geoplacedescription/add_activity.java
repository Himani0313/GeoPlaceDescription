package edu.asu.msse.hjshah2.geoplacedescription;
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
 * Purpose: Android app to browse and modify a collection of Place Descriptions using JsonRPC server
 *
 * Ser423 Mobile Applications
 * @author Himani Shah Himani.shah@asu.edu
 *         Software Engineering, CIDSE, ASU Poly
 * @version March 2017
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_activity extends AppCompatActivity {

    EditText name, description, category, adddresstitle, address, elevation, latitude, longitude;
    Button add;
    PlaceDescriptionLibrary pdl;
    PlaceDescription placeDescription;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        add = (Button)findViewById(R.id.addButton);
        name = (EditText)findViewById(R.id.displayName_add);
        description = (EditText)findViewById(R.id.displayDescription_add);
        category = (EditText)findViewById(R.id.displayCategory_add);
        adddresstitle = (EditText)findViewById(R.id.displayAddresstitle_add);
        address = (EditText)findViewById(R.id.displayAddress_add);
        elevation = (EditText)findViewById(R.id.displayElevation_add);
        latitude = (EditText)findViewById(R.id.displayLatitude_add);
        longitude = (EditText)findViewById(R.id.displayLongitude_add);
        Intent intent = getIntent();
        pdl = intent.getSerializableExtra("places")!=null ? (PlaceDescriptionLibrary) intent.getSerializableExtra("places") :
                new PlaceDescriptionLibrary(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = name.getText().toString().trim();
                placeDescription = new PlaceDescription();

                placeDescription.setName(name.getText().toString().trim());
                placeDescription.setCategory(category.getText().toString().trim());
                placeDescription.setDescription(description.getText().toString().trim());
                placeDescription.setAddresstitle(adddresstitle.getText().toString().trim());
                placeDescription.setAddress(address.getText().toString().trim());
                placeDescription.setElevation(Double.parseDouble(elevation.getText().toString().trim()));
                placeDescription.setLatitude(Double.parseDouble(latitude.getText().toString().trim()));
                placeDescription.setLongitude(Double.parseDouble(longitude.getText().toString().trim()));

                pdl.add(title,placeDescription,add_activity.this);

                Intent i = new Intent();
                i.putExtra("places", pdl);
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }
}

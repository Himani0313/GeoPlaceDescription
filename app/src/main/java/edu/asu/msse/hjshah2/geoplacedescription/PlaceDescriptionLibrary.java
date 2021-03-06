package edu.asu.msse.hjshah2.geoplacedescription;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
public class PlaceDescriptionLibrary implements Serializable {
    protected Hashtable<String, PlaceDescription> places;
    public ArrayList<String> str = new ArrayList<String>();
    Resources resources;
    String url = "http://10.0.2.2:9090";
    public PlaceDescriptionLibrary(Context appContext) {
//        places = new Hashtable<String, PlaceDescription>();
//        InputStream is = appContext.getResources().openRawResource(R.raw.places);
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        try{
//            JSONObject placesJSON = new JSONObject(new JSONTokener(br.readLine()));
//            Iterator<String> it = placesJSON.keys();
//            while(it.hasNext()) {
//                String pTitle = it.next();
//                JSONObject aPlace = placesJSON.optJSONObject(pTitle);
//
//                if(aPlace != null) {
//                    PlaceDescription md = new PlaceDescription(aPlace, pTitle);
//                    places.put(pTitle, md);
//                }
//            }
//        }
//        catch(Exception e){
//            e.getStackTrace();
//        }

    }


//    public List<String> loadFromJSON(Context appContext){
//        places = new Hashtable<String, PlaceDescription>();
//        InputStream is = appContext.getResources().openRawResource(R.raw.places);
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        try{
//            JSONObject placesJSON = new JSONObject(new JSONTokener(br.readLine()));
//            Iterator<String> it = placesJSON.keys();
//            while(it.hasNext()) {
//                String pTitle = it.next();
//                JSONObject aPlace = placesJSON.optJSONObject(pTitle);
//
//                if(aPlace != null) {
//                    PlaceDescription md = new PlaceDescription(aPlace, pTitle);
//                    places.put(pTitle, md);
//                    str.add(pTitle);
//                }
//            }
//        }
//        catch(Exception e){
//            e.getStackTrace();
//        }
//        return str;
//    }

    public void getTitles (Context appContext){
        try{
            MethodInformation mi = new MethodInformation((Main2Activity) appContext, url,"getNames", new String[]{});
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                    ex.getMessage());
        }
    }

    public void remove(Context appContext, String aName) {
        //debug("removing student named: " + aName);

        try{
            MethodInformation mi = new MethodInformation((MainActivity) appContext, url,"remove", new String[]{aName});
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                    ex.getMessage());
        }
    }

    public void add(String placeTitle, PlaceDescription placeDescriptionObject, Context appContext){
        try{
            MethodInformation mi = new MethodInformation((add_activity) appContext, url,"add", placeDescriptionObject.toJsonString());
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                    ex.getMessage());
        }
    }
    public void update(String placeTitle, PlaceDescription placeDescriptionObject, Context appContext){
        //places.put(placeTitle, placeDescriptionObject);

        try{
            MethodInformation mi = new MethodInformation((MainActivity) appContext, url,"add", placeDescriptionObject.toJsonString());
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                    ex.getMessage());
        }

    }
//    public PlaceDescription getPlaceDescription(String pTitle) {
//        return places.get(pTitle);
//    }

}

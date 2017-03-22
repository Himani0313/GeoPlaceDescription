package edu.asu.msse.hjshah2.geoplacedescription;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

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

public class AsyncCollectionConnect extends AsyncTask<MethodInformation, Integer, MethodInformation> {
    ArrayList<String> al = new ArrayList<String>();
    @Override
    protected void onPreExecute(){
        android.util.Log.d(this.getClass().getSimpleName(),"in onPreExecute on "+
                (Looper.myLooper() == Looper.getMainLooper()?"Main thread":"Async Thread"));
    }
    @Override
    protected MethodInformation doInBackground(MethodInformation... aRequest){
        // array of methods to be called. Assume exactly one in a single MethodInformation object
        android.util.Log.d(this.getClass().getSimpleName(),"in doInBackground on "+
                (Looper.myLooper() == Looper.getMainLooper()?"Main thread":"Async Thread"));
        try {
            JSONArray ja;
            if(aRequest[0].method.equals("add")){
                ja = new JSONArray();
                ja.put(aRequest[0].param);
            }
            else {
                ja = new JSONArray(aRequest[0].params);
            }
            android.util.Log.d(this.getClass().getSimpleName(),"params: "+ja.toString());
            String requestData = "{ \"jsonrpc\":\"2.0\", \"method\":\""+aRequest[0].method+"\", \"params\":"+ja.toString()+
                    ",\"id\":3}";
            android.util.Log.d(this.getClass().getSimpleName(),"requestData: "+requestData+" url: "+aRequest[0].urlString);
            JsonRPCRequestViaHttp conn = new JsonRPCRequestViaHttp((new URL(aRequest[0].urlString)), aRequest[0].parent);
            String resultStr = conn.call(requestData);
            android.util.Log.d(this.getClass().getSimpleName(),"in doInBackground on vdffffffffffffff "+ resultStr );
            aRequest[0].resultAsJson = resultStr;
        }catch (Exception ex){
            android.util.Log.d(this.getClass().getSimpleName(),"exception in remote call "+
                    ex.getMessage());
        }
        return aRequest[0];
    }
    @Override
    protected void onPostExecute(MethodInformation res){
        android.util.Log.d(this.getClass().getSimpleName(), "in onPostExecute on " +
                (Looper.myLooper() == Looper.getMainLooper() ? "Main thread" : "Async Thread"));
        android.util.Log.d(this.getClass().getSimpleName(), " resulting is: " + res.resultAsJson);

        try {
            if (res.method.equals("getNames") && res.parent instanceof Main2Activity) {
                JSONObject jo = new JSONObject(res.resultAsJson);
                JSONArray ja = jo.getJSONArray("result");
                android.util.Log.d(this.getClass().getSimpleName(), String.valueOf(ja) +"pppppppp");

                for (int i = 0; i < ja.length(); i++) {
                    al.add(ja.getString(i));
                }
                String[] names = al.toArray(new String[0]);
                ((Main2Activity)(res.parent)).simpleAdapter.clear();
                for (int i = 0; i < names.length; i++) {
                    ((Main2Activity)(res.parent)).simpleAdapter.add(names[i]);
                }
                ((Main2Activity)(res.parent)).simpleAdapter.notifyDataSetChanged();

            }
            else if (res.method.equals("getNames") && res.parent instanceof MainActivity) {
                JSONObject jo = new JSONObject(res.resultAsJson);
                JSONArray ja = jo.getJSONArray("result");
                android.util.Log.d(this.getClass().getSimpleName(), String.valueOf(ja) +"pppppppp");

                for (int i = 0; i < ja.length(); i++) {
                    al.add(ja.getString(i));
                }
                String[] names = al.toArray(new String[0]);
                ((MainActivity)(res.parent)).simpleAdapter.clear();
                for (int i = 0; i < names.length; i++) {
                    ((MainActivity)(res.parent)).simpleAdapter.add(names[i]);
                }
                ((MainActivity)(res.parent)).simpleAdapter.notifyDataSetChanged();

            }
             else if (res.method.equals("resetFromJsonFile")) {
                JSONObject jo = new JSONObject(res.resultAsJson);
                JSONArray ja = jo.getJSONArray("result");
                android.util.Log.d(this.getClass().getSimpleName(), String.valueOf(ja) +"pppppppp");

                for (int i = 0; i < ja.length(); i++) {
                    al.add(ja.getString(i));
                }
                String[] names = al.toArray(new String[0]);
                ((Main2Activity)(res.parent)).simpleAdapter.clear();
                for (int i = 0; i < names.length; i++) {
                    ((Main2Activity)(res.parent)).simpleAdapter.add(names[i]);
                }
                ((Main2Activity)(res.parent)).simpleAdapter.notifyDataSetChanged();

            }
            else if (res.method.equals("get")) {
                JSONObject jo = new JSONObject(res.resultAsJson);
                PlaceDescription aPlace = new PlaceDescription(jo.getJSONObject("result"));
                //android.util.Log.d(this.getClass().getSimpleName(), aPlace.name + "xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                if(res.status == false){
                    Log.d("Async connect","list viewwwww");
                    ((MainActivity)(res.parent)).getCompleted(aPlace);
                }

                else
                    ((MainActivity)(res.parent)).getDistance(aPlace);
            }
            else if (res.method.equals("remove")) {
                JSONObject jo = new JSONObject(res.resultAsJson);
                //android.util.Log.d(this.getClass().getSimpleName(), aPlace.name + "xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                JSONArray ja = jo.getJSONArray("result");
            }
            else if (res.method.equals("add")) {

                JSONObject jo = new JSONObject(res.resultAsJson);
                Log.d("ADDD",jo.toString());
                JSONArray ja = jo.getJSONArray("result");
            }
        }catch (Exception ex){
                android.util.Log.d(this.getClass().getSimpleName(),"Exception: "+ex.getMessage());
            }
    }
}

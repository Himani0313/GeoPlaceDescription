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

import org.json.JSONObject;

import java.io.Serializable;

public class PlaceDescription implements Serializable {
    String name;
    String description;
    String category;
    String addresstitle;
    String address;
    double elevation;
    double latitude;
    double longitude;
    PlaceDescription(){

    }
    PlaceDescription(JSONObject jo, String name){
        try{
            //JSONObject jo = new JSONObject(jsonStr);
            this.name = name;
            description = jo.optString("description");
            category = jo.optString("category");
            addresstitle = jo.optString("address-title");
            address = jo.optString("address-street");
            elevation = jo.getDouble("elevation");
            latitude = jo.getDouble("latitude");
            longitude = jo.getDouble("longitude");
        }catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from json");
        }

    }
    PlaceDescription(JSONObject jo){
        try{
            //JSONObject jo = new JSONObject(jsonStr);
            this.name =jo.optString("name");
            description = jo.optString("description");
            category = jo.optString("category");
            addresstitle = jo.optString("address-title");
            address = jo.optString("address-street");
            elevation = jo.getDouble("elevation");
            latitude = jo.getDouble("latitude");
            longitude = jo.getDouble("longitude");
        }catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from json");
        }

    }
    public JSONObject toJsonString(){
        JSONObject ret = null;
        try{
            JSONObject jo = new JSONObject();
            jo.put("name",name);
            jo.put("description",description);
            jo.put("category",category);
            jo.put("addresstitle",addresstitle);
            jo.put("address",address);
            jo.put("elevation",elevation);
            jo.put("latitude", latitude);
            jo.put("longitude", longitude);
            ret = jo;
        }catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from json");
        }
        return ret;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name =name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description =description;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category =category;
    }
    public String getAddresstitle(){
        return addresstitle;
    }
    public void setAddresstitle(String addresstitle){
        this.addresstitle =addresstitle;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address =address;
    }
    public double getElevation(){
        return elevation;
    }
    public void setElevation(double elevation){
        this.elevation =elevation;
    }
    public double getLatitude(){
        return latitude;
    }
    public void setLatitude(double latitude){
        this.latitude =latitude;
    }
    public double getLongitude(){
        return longitude;
    }
    public void setLongitude(double longitude){
        this.longitude =longitude;
    }
}

package edu.asu.msse.hjshah2.geoplacedescription;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;
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

public class MethodInformation {
    public String method;
    public String[] params;
    public AppCompatActivity parent;
    public String urlString;
    public String resultAsJson;
    public JSONObject param;
    public boolean status = false;
    MethodInformation(AppCompatActivity parent, String urlString, String method, String[] params){
        this.method = method;
        this.parent = parent;
        this.urlString = urlString;
        this.params = params;
        this.resultAsJson = "{}";
    }
    MethodInformation(AppCompatActivity parent, String urlString, String method, String[] params, boolean status){
        this.method = method;
        this.parent = parent;
        this.urlString = urlString;
        this.params = params;
        this.resultAsJson = "{}";
        this.status = status;
    }
    MethodInformation(AppCompatActivity parent, String urlString, String method, JSONObject param){
        this.method = method;
        this.parent = parent;
        this.urlString = urlString;
        this.param = param;
        this.resultAsJson = "{}";
    }
}

package edu.asu.msse.hjshah2.geoplacedescription;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

/**
 * Created by hjshah2 on 3/17/2017.
 */

public class MethodInformation {
    public String method;
    public String[] params;
    public AppCompatActivity parent;
    public String urlString;
    public String resultAsJson;
    public JSONObject param;
    MethodInformation(AppCompatActivity parent, String urlString, String method, String[] params){
        this.method = method;
        this.parent = parent;
        this.urlString = urlString;
        this.params = params;
        this.resultAsJson = "{}";
    }
    MethodInformation(AppCompatActivity parent, String urlString, String method, JSONObject param){
        this.method = method;
        this.parent = parent;
        this.urlString = urlString;
        this.param = param;
        this.resultAsJson = "{}";
    }
}

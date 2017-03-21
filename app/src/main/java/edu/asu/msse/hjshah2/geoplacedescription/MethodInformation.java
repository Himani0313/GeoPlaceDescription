package edu.asu.msse.hjshah2.geoplacedescription;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by hjshah2 on 3/17/2017.
 */

public class MethodInformation {
    public String method;
    public String[] params;
    public AppCompatActivity parent;
    public String urlString;
    public String resultAsJson;

    MethodInformation(AppCompatActivity parent, String urlString, String method, String[] params){
        this.method = method;
        this.parent = parent;
        this.urlString = urlString;
        this.params = params;
        this.resultAsJson = "{}";
    }
}

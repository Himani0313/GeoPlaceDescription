package edu.asu.msse.hjshah2.geoplacedescription;

import android.os.AsyncTask;
import android.os.Looper;

import org.json.JSONArray;

import java.net.URL;

/**
 * Created by hjshah2 on 3/17/2017.
 */

public class AsyncCollectionConnect extends AsyncTask<MethodInformation, Integer, MethodInformation> {
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
        android.util.Log.d(this.getClass().getSimpleName(), " resulting is: " + res.resultAsJson);}
}

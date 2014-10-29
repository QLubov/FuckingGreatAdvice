package com.example.fga;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import org.xmlpull.v1.*;

class DownloadTask extends AsyncTask<String, Integer, String> {

	private Activity mActivity;
	public DownloadTask(Activity activity){
		mActivity = activity;
	}
	@Override
	protected String doInBackground(String... sUrl) {
		Log.e("mi", "doInBackground");
		InputStream input = null;
		HttpURLConnection connection = null;
				try{
			
			URL url = new URL(sUrl[0]);
			connection = (HttpURLConnection) url.openConnection();


			if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
				Log.e("mi", "FAIL: "+ connection.getResponseCode() + " " + connection.getResponseMessage());
				return "Server return" + connection.getResponseCode() + " " + connection.getResponseMessage();
			}
			
			input = connection.getInputStream();
			Log.e("mi", "SUCCESS");
		}
		catch(Exception e){
			Log.e("mi", "Exception! " + e.getMessage());
			}
		return "Nya";
	}

}

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void sendMessage(View view){
    	Log.e("mi", "sendMessage");
    	DownloadTask dTask = new DownloadTask(this);
    	dTask.execute("http://192.168.10.2/New");
    	}
}



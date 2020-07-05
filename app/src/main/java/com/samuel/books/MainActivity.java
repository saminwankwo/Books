package com.samuel.books;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookList_activity);
		
		
		try {
			URL bookUrl = apiUtlis.buildUrl("cooking");
			new BookQueryTasks().execute(bookUrl);
			
		} catch (Exception e) {
			Log.d("Error", e.getMessage());
		}
		
	}
	
	public class BookQueryTasks extends AsyncTask<URL, Void, String> {
		
		@Override
		protected String doInBackground(URL... urls) {
			URL searchURL = urls[0];
			String result = null;
			
			try {
				result = apiUtlis.getJson(searchURL);
				
			} catch (Exception e) {
				Log.d("Error", e.getMessage());
			}
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			TextView tvResult = (TextView) findViewById(R.id.tvResponse);
			tvResult.setText(result);
		}
		
	}
}

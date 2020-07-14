package com.samuel.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private ProgressBar mLoadingProgress;
	private RecyclerView rvBooks;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookList_activity);
		
		mLoadingProgress = (ProgressBar) findViewById(R.id.pb_loading);
		rvBooks = (RecyclerView) findViewById(R.id.rv_books);
		
		try {
			URL bookUrl = apiUtlis.buildUrl("cooking");
			new BookQueryTasks().execute(bookUrl);
			
		} catch (Exception e) {
			Log.d("Error", e.getMessage());
		}
		//create the layoutManager for the books (linear in this case, scrolling vertically
		@SuppressLint("WrongConstant") LinearLayoutManager booksLayoutManager =
				new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		rvBooks.setLayoutManager(booksLayoutManager);
		
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
			
			TextView tvError = (TextView) findViewById(R.id.tv_error);
			mLoadingProgress.setVisibility(View.INVISIBLE);
			
			if (result == null) {
				rvBooks.setVisibility(View.INVISIBLE);
				tvError.setVisibility(View.VISIBLE);
			} else {
				rvBooks.setVisibility(View.VISIBLE);
				tvError.setVisibility(View.INVISIBLE);
			}
			ArrayList<Book> books = apiUtlis.getBooksFromJson(result);
			String resultString = "";
			
			BooksAdapter adapter = new BooksAdapter(books);
			rvBooks.setAdapter(adapter);
		}
		
		@Override
		protected void onPreExecute() {
			
			mLoadingProgress.setVisibility(View.VISIBLE);
		}
	}
}

package com.samuel.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.samuel.books.databinding.ActivityBookDetailsBinding;

public class bookDetails extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityBookDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_book_details);
		Book book = getIntent().getParcelableExtra("Book");
		binding.setBook(book);
		
	}
}

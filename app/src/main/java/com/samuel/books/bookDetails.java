package com.samuel.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

public class bookDetails extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityBookDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_book_details);
		Book book = getIntent().getParcelableExtra("Book");
		binding.setBook(book);
		
	}
}

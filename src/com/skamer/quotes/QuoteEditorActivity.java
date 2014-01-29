package com.skamer.quotes;

import com.skamer.quotes.data.QuoteItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

public class QuoteEditorActivity extends Activity {

	private QuoteItem quote;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quote_editor);
		getActionBar().setDisplayHomeAsUpEnabled(true);		
		
		Intent intent = this.getIntent();
		quote = new QuoteItem();
		quote.setKey(intent.getStringExtra("key"));
		quote.setText(intent.getStringExtra("text"));
		quote.setAuthor(intent.getStringExtra("author"));
		quote.setCategory(intent.getStringExtra("category"));
		
		EditText et = (EditText) findViewById(R.id.quoteText);
		et.setText(quote.getText());
		et.setSelection(quote.getText().length());
		
		EditText at = (EditText) findViewById(R.id.authorText);
		at.setText(quote.getAuthor());
		at.setSelection(quote.getAuthor().length());
		
		EditText ct = (EditText) findViewById(R.id.categoryText);
		ct.setText(quote.getCategory());
		ct.setSelection(quote.getCategory().length());
	}
	
	private void saveAndFinish() {
		EditText et = (EditText) findViewById(R.id.quoteText);
		String quoteText = et.getText().toString();
		
		EditText at = (EditText) findViewById(R.id.authorText);
		String authorText = at.getText().toString();
		
		EditText ct = (EditText) findViewById(R.id.categoryText);
		String categoryText = ct.getText().toString();
		
		Intent intent =  new Intent();
		intent.putExtra("key", quote.getKey());
		intent.putExtra("text", quoteText);
		intent.putExtra("author", authorText);
		intent.putExtra("category", categoryText);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home){
			saveAndFinish();
		}
		return false;
	}
	
	@Override
	public void onBackPressed() {
		saveAndFinish();
	}
	
}

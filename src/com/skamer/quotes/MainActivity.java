package com.skamer.quotes;

import java.util.List;


import com.skamer.quotes.data.QuoteItem;
import com.skamer.quotes.data.QuotesDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MainActivity extends ListActivity {

	private static final int EDITOR_ACTIVITY_REQUEST = 1001;
	private static final int MENU_DELETE_ID = 1002;
	private int currentQuoteId;
	private QuotesDataSource datasource;
	List<QuoteItem> quotesList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		registerForContextMenu(getListView());
		
		datasource = new QuotesDataSource(this);
		
		refreshDisplay();
		
	}

	private void refreshDisplay() {
		quotesList = datasource.findAll();
		ArrayAdapter<QuoteItem> adapter =
				new ArrayAdapter<QuoteItem>(this, R.layout.list_item_layout, quotesList);
		setListAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.action_create){
			createQuote();
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void createQuote(){
		QuoteItem quote = QuoteItem.getNew();
		Intent intent = new Intent(this, QuoteEditorActivity.class);
		intent.putExtra("key", quote.getKey());
		intent.putExtra("text", quote.getText());
		intent.putExtra("author", quote.getAuthor());
		intent.putExtra("category", quote.getCategory());
		startActivityForResult(intent, EDITOR_ACTIVITY_REQUEST);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		QuoteItem quote = quotesList.get(position);
		Intent intent = new Intent(this, QuoteEditorActivity.class);
		intent.putExtra("key", quote.getKey());
		intent.putExtra("text", quote.getText());
		intent.putExtra("author", quote.getAuthor());
		intent.putExtra("category", quote.getCategory());
		startActivityForResult(intent, EDITOR_ACTIVITY_REQUEST);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == EDITOR_ACTIVITY_REQUEST && resultCode == RESULT_OK) {
			QuoteItem quote = new QuoteItem();
			quote.setKey(data.getStringExtra("key"));
			quote.setText(data.getStringExtra("text"));
			quote.setAuthor(data.getStringExtra("author"));
			quote.setCategory(data.getStringExtra("category"));
			datasource.update(quote);
			refreshDisplay();
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		currentQuoteId = (int)info.id;
		menu.add(0, MENU_DELETE_ID, 0, "Delete");
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		if (item.getItemId() == MENU_DELETE_ID){
			QuoteItem quote = quotesList.get(currentQuoteId);
			datasource.remove(quote);
			refreshDisplay();
		}
		
		return super.onContextItemSelected(item);
	}
	
}

package com.skamer.quotes.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Stefan on 11/24/13.
 */
public class QuotesDataSource {

    private static final String PREFKEY = "quotes";
    private SharedPreferences quotePrefs;

    public QuotesDataSource(Context context) {
        quotePrefs = context.getSharedPreferences(PREFKEY, Context.MODE_PRIVATE);
    }

    public List<QuoteItem> findAll() {

        Map<String, ?> quotesMap = quotePrefs.getAll();

        SortedSet<String> keys = new TreeSet<String>(quotesMap.keySet());

        List<QuoteItem> quoteList = new ArrayList<QuoteItem>();
        for (String key : keys) {
            QuoteItem quote = new QuoteItem();
            quote.setKey(key);
            quote.setText((String) quotesMap.get(key));
            quote.setAuthor((String) quotesMap.get(key));
            quote.setCategory((String) quotesMap.get(key));
            quoteList.add(quote);
        }

        return quoteList;

    }

    public boolean update(QuoteItem quote) {

        SharedPreferences.Editor editor = quotePrefs.edit();
        editor.putString(quote.getKey(), quote.getText());
        editor.commit();
        return true;
    }

    public boolean remove(QuoteItem quote) {

        if (quotePrefs.contains(quote.getKey())) {
            SharedPreferences.Editor editor = quotePrefs.edit();
            editor.remove(quote.getKey());
            editor.commit();
        }

        return true;
    }

}

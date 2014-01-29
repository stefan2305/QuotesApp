package com.skamer.quotes.data;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class QuoteItem {

    private String key;
    private String text;
    private String author;
    private String category;
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    
    @SuppressLint("SimpleDateFormat")
    public static QuoteItem getNew() {

        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);

        String pattern = "yyyy-MM-dd HH:mm:ss Z";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String key = formatter.format(new Date());

        QuoteItem quote = new QuoteItem();
        quote.setKey(key);
        quote.setText("");
        quote.setAuthor("");
        quote.setCategory("");
        return quote;
    }

    @Override
    public String toString() {
    	return this.getText();
    }

}

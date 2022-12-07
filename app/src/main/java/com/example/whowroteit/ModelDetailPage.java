// Lu Lu
package com.example.whowroteit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ModelDetailPage extends AppCompatActivity {

    private String title, subtitle, authors, description, publisher, publishedDate, thumbnail;
    private ImageView coverImage;
    TextView titleTV, subtitleTV, authorsTV, publisherTV, publishDateTV, descriptionTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_detail_page);

        titleTV = findViewById(R.id.titleBook);
        subtitleTV = findViewById(R.id.subtitleBook);
        publisherTV = findViewById(R.id.publisher);
        publishDateTV = findViewById(R.id.publishDate);
        descriptionTV = findViewById(R.id.description);
        coverImage = findViewById(R.id.coverImageBook);
        authorsTV = findViewById(R.id.authors);

        title = getIntent().getStringExtra("title");
        subtitle = getIntent().getStringExtra("subtitle");
        publisher = getIntent().getStringExtra("publisher");
        publishedDate = getIntent().getStringExtra("publishedDate");
        description = getIntent().getStringExtra("description");
        thumbnail = getIntent().getStringExtra("thumbnail");
        authors = getIntent().getStringExtra("authors");

        titleTV.setText(title);
        subtitleTV.setText(subtitle);
        authorsTV.setText(authors);
        publisherTV.setText(publisher);
        publishDateTV.setText(publishedDate);
        descriptionTV.setText(description);
        Picasso.get().load(thumbnail).into(coverImage);
    }
}
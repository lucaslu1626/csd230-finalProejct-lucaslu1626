//Lu Lu
package com.example.whowroteit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mBookInput;
    private ItemAdapter itemAdapter;
    private RecyclerView recyclerView;
    private RequestQueue mRequestQueue;
    private ArrayList<GoogleBookModel> googleBookModelArrayList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        mBookInput = findViewById(R.id.bookInput);
    }

    public void searchBook(View view) {
        progressBar.setVisibility(View.VISIBLE);
        if (mBookInput.getText().toString().isEmpty()) {
            mBookInput.setError("Please enter a search term");
        }
        //Hides the keyboard when the user taps the button
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        googleBookModelArrayList = new ArrayList<>();
        // Initialize the variable for request queue
        mRequestQueue = Volley.newRequestQueue(MainActivity.this);

        // Clear cache when data is being updated
        mRequestQueue.getCache().clear();

        // url for getting data from API in json format
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + mBookInput.getText().toString();

        // Creates a new request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // Json object request
        JsonObjectRequest booksObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            progressBar.setVisibility(View.GONE);
            // Extract all json data.
            try {
                JSONArray itemsArray = response.getJSONArray("items");
                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject itemsObj = itemsArray.getJSONObject(i);
                    JSONObject volumeObj = itemsObj.getJSONObject("volumeInfo");
                    String title = volumeObj.optString("title");
                    String subtitle = volumeObj.optString("subtitle");
                    String authors = volumeObj.optString("authors");
                    String publisher = volumeObj.optString("publisher");
                    String publishedDate = volumeObj.optString("publishedDate");
                    String description = volumeObj.optString("description");
                    JSONObject imageLinks = volumeObj.optJSONObject("imageLinks");
                    String thumbnail = imageLinks.optString("thumbnail");
                    //add 's' to HTTP
                    thumbnail = thumbnail.substring(0, 4) + 's' + thumbnail.substring(4);

                    // Save data to model class
                    GoogleBookModel googleBookModel = new GoogleBookModel(title, subtitle, authors, description, publisher, publishedDate, thumbnail);

                    // Pass model class to ArrayList
                    googleBookModelArrayList.add(googleBookModel);

                    // Pass ArrayList to adapter class
                    itemAdapter = new ItemAdapter(MainActivity.this, googleBookModelArrayList);

                    // Add linear layout manager for recycler view.
                    recyclerView = findViewById(R.id.customRecyclerView);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

                    // Set layout manager and adapter to recycler view.
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(itemAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                // Display a toast message when users get any error from API
                Toast.makeText(MainActivity.this, R.string.no_results, Toast.LENGTH_SHORT).show();
            }
        }, error -> {
        // Display error message in toast.
        Toast.makeText(MainActivity.this, getString(R.string.error) + error, Toast.LENGTH_SHORT).show();
    });
        //Add json object request to request queue
        queue.add(booksObjRequest);
    }
}
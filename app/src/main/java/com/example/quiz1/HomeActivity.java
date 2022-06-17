package com.example.quiz1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.quiz1.OtherActivity.ProfileActivity;
import com.example.quiz1.adapter.BookAdapter;
import com.example.quiz1.data.BookData;
import com.example.quiz1.data.UserData;
import com.example.quiz1.models.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class HomeActivity extends AppCompatActivity {

    BookData bookData = new BookData();
    UserData userData;
    RecyclerView rvBook;
    BookAdapter bookAdapter;
    Intent intent;

    private RequestQueue requestQueue;
    private ProgressDialog progressDialog;
    final String url = "https://mocki.io/v1/9df8c065-61ad-4109-bde5-622c597a07c7";

//    https://mocki.io/v1/5f379081-2473-4494-9cc3-9e808772dc54

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);

        startAsyncTask(R.layout.activity_home);

    }

    public void startAsyncTask(int view){
        InsormaAsyncTask task = new InsormaAsyncTask();
        task.execute(url);

    }

    private class InsormaAsyncTask extends AsyncTask<String, String, Vector<Book>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected Vector<Book> doInBackground(String... strings) {
            if(bookData.getLoadStatus() == false){
                int dataCountInDatabase = bookData.checkDatabase(HomeActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray furnitures = response.getJSONArray("books");
                                    for (int i = 0; i < furnitures.length(); i++) {
                                        JSONObject c = furnitures.getJSONObject(i);
                                        String name = c.getString("judul");
                                        String desc = c.getString("description");
                                        String writter = c.getString("penulis");
                                        String hal = c.getString("jmlHal");
                                        String category = c.getString("categorie");
                                        String publisher = c.getString("penerbit");
                                        String isbn = c.getString("isbn");
                                        String date = c.getString("publishDate");
                                        String img = c.getString("img");

                                        //tambah data ke vector
                                        bookData.getVectBook().add(new Book(i+1, name, desc, writter, hal, category, publisher, isbn, date, img));
                                    }
                                    Log.wtf(" .homeactivity", "do bg");

                                    if(dataCountInDatabase != bookData.getVectBook().size()){
                                        bookData.loadDataToDatabase(HomeActivity.this, bookData.getVectBook());
                                    }
                                    bookData.setLoadStatus(true);
                                    Log.wtf(" .homeactivity", "do bg - masuk if");

                                    Log.wtf(" .homeactivity", "add, array size now = " + bookData.getVectBook().size());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                bookAdapter.notifyDataSetChanged();
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Info", error.toString());
                    }
                }
                );
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                requestQueue.add(jsonObjectRequest);


            }
            Log.wtf(" .homeactivity", "do bg done");

            return bookData.getVectBook();
        }

        @Override
        protected void onPostExecute(Vector<Book> temp) {
            super.onPostExecute(temp);

            rvBook = findViewById(R.id.rvBook);
            bookAdapter = new BookAdapter(HomeActivity.this, bookData.getVectBook());
            rvBook.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            rvBook.setAdapter(bookAdapter);
            progressDialog.dismiss();
            Log.wtf(" .homeactivity", "do post excecute, arr ="+ bookData.getVectBook().size());

            setTitle("Hi " + userData.getLoggedIn().getUsername() + "!");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home :
                Log.wtf("test", "Masuk Home");
                break;
            case R.id.profile :
                intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("username", userData.getLoggedIn().getUsername());
                intent.putExtra("email", userData.getLoggedIn().getEmailAddress());
                intent.putExtra("phone", userData.getLoggedIn().getPhoneNum());
                startActivity(intent);
                Log.wtf("test", "Masuk Profile");
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private Object getActivity() {
        return 3;
    }
}
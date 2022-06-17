package com.example.quiz1.OtherActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.quiz1.R;

import java.util.Vector;

public class BookDetailActivity extends AppCompatActivity {

    ImageView ivDetailImage;
    TextView tvDetailName, tvDetailCategory,tvDetailWriter, tvDetailPublisher;
    TextView tvDetailPublishDate, tvDetailIsbn,tvDetailHal,tvDetailDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_detail);
        setTitle("Detail Book");

        String name,writer, img;
        String category, publisher, desc, publishDate, isbn, hal;

        Intent intent = getIntent();
        img = intent.getStringExtra("img");
        ivDetailImage = findViewById(R.id.ivDetailImage);

        RequestOptions options = new RequestOptions();
        Glide.with(this).load(img).apply(options).into(ivDetailImage);

        name = intent.getStringExtra("name");
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailName.setText(name);

        category = intent.getStringExtra("category");
        tvDetailCategory = findViewById(R.id.tvDetailCategory);
        tvDetailCategory.setText(category);

        writer = intent.getStringExtra("writer");
        tvDetailWriter = findViewById(R.id.tvDetailWriter);
        tvDetailWriter.setText("oleh " + writer);

        publisher = intent.getStringExtra("publisher");
        tvDetailPublisher = findViewById(R.id.tvDetailPublisher);
        tvDetailPublisher.setText("   "+publisher);

        publishDate = intent.getStringExtra("publishDate");
        tvDetailPublishDate = findViewById(R.id.tvDetailPublishDate);
        tvDetailPublishDate.setText("   "+publishDate);

        isbn = intent.getStringExtra("isbn");
        tvDetailIsbn = findViewById(R.id.tvDetailIsbn);
        tvDetailIsbn.setText("ISBN: "+isbn);

        hal = intent.getStringExtra("hal");
        tvDetailHal = findViewById(R.id.tvDetailHal);
        tvDetailHal.setText("Total Halaman: "+hal);

        desc = intent.getStringExtra("desc");
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        tvDetailDescription.setText(desc);

    }

}
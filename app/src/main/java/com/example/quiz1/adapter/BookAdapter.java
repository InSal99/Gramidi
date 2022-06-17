package com.example.quiz1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quiz1.OtherActivity.BookDetailActivity;
import com.example.quiz1.R;
import com.example.quiz1.data.BookData;
import com.example.quiz1.models.Book;

import java.util.Vector;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    Context context;
    private final Vector<Book> vectBook;

    public BookAdapter(Context context, Vector<Book> vectBook) {
        this.vectBook = vectBook;
        this.context = context;
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String name,writer, img;

        name = vectBook.get(position).getName();
        writer = vectBook.get(position).getWritter();
        img = vectBook.get(position).getImg();

        Glide.with(context)
                .load(img)
                .into(holder.picture);

        holder.name.setText(name);
        holder.writer.setText(writer);


        String category, publisher, desc, publishDate, isbn, hal;
        category = vectBook.get(position).getCategory();
        publisher = vectBook.get(position).getPublisher();
        desc = vectBook.get(position).getDesc();
        publishDate = vectBook.get(position).getPublishDate();
        isbn = vectBook.get(position).getIsbn();
        hal = vectBook.get(position).getHal();

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, BookDetailActivity.class);

            intent.putExtra("name", name);
            intent.putExtra("category", category);
            intent.putExtra("writer", writer);
            intent.putExtra("publisher", publisher);
            intent.putExtra("publishDate", publishDate);
            intent.putExtra("isbn", isbn);
            intent.putExtra("img", img);
            intent.putExtra("hal", hal);
            intent.putExtra("desc", desc);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return vectBook.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView picture;
        TextView name, writer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.ivListBook);
            name = itemView.findViewById(R.id.tvNameListBook);
            writer = itemView.findViewById(R.id.tvWriter);

        }
    }
}

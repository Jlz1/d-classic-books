package com.example.dclassicbooks.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dclassicbooks.R;
import com.example.dclassicbooks.models.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    // 1. Variabel untuk menyimpan list buku
    private ArrayList<Book> bookList;

    // Constructor untuk menerima data dari Activity/Fragment
    public BookAdapter(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    // 2. Memanggil "cetakan" layout XML (item_book.xml)
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    // 3. Memasukkan (binding) data ke masing-masing elemen UI
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        // Ambil satu buku berdasarkan posisinya di list
        Book currentBook = bookList.get(position);

        // Set informasi ke TextView dan ImageView
        holder.tvTitle.setText(currentBook.getTitle());
        holder.tvAuthor.setText(currentBook.getAuthor());
        holder.ivCover.setImageResource(currentBook.getImageResource());

        // (Opsional) Kalau kamu mau bikin action saat bukunya diklik
        // holder.itemView.setOnClickListener(v -> {
        //      Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
        //      intent.putExtra("BOOK_TITLE", currentBook.getTitle());
        //      v.getContext().startActivity(intent);
        // });
    }

    // 4. Menentukan seberapa banyak item yang mau ditampilkan
    @Override
    public int getItemCount() {
        return bookList.size();
    }

    // --- Inner Class: ViewHolder ---
    // Tugasnya menyambungkan variabel Java dengan ID yang ada di item_book.xml
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitle, tvAuthor;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            // Sesuaikan ID ini dengan yang ada di item_book.xml kamu
            ivCover = itemView.findViewById(R.id.ivBookCover);
            tvTitle = itemView.findViewById(R.id.tvBookTitle);
            tvAuthor = itemView.findViewById(R.id.tvBookAuthor);
        }
    }
}
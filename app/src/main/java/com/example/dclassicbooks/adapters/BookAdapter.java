package com.example.dclassicbooks.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dclassicbooks.R;
import com.example.dclassicbooks.activities.BookDetailActivity;
import com.example.dclassicbooks.models.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    // 1. Variabel untuk menyimpan list buku
    private ArrayList<Book> bookList;
    private int layoutId;

    // ==========================================
    // CONSTRUCTOR 1: Untuk halaman lama (Hanya 1 argumen)
    // Jika dipanggil tanpa menyebutkan layout, otomatis pakai layout lama
    // ==========================================
    public BookAdapter(ArrayList<Book> bookList) {
        this.bookList = bookList;
        this.layoutId = R.layout.item_book; // Masukkan nama file XML layout lamamu di sini
    }

    // ==========================================
    // CONSTRUCTOR 2: Untuk halaman Homepage baru (2 argumen)
    // Menerima pesanan layout khusus dari MainActivity
    // ==========================================
    public BookAdapter(ArrayList<Book> bookList, int layoutId) {
        this.bookList = bookList;
        this.layoutId = layoutId;
    }

    // 2. Memanggil "cetakan" layout XML (item_book.xml)
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new BookViewHolder(view);
    }

    // 3. Memasukkan (binding) data ke masing-masing elemen UI
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book currentBook = bookList.get(position);

        // 4. PENGECEKAN AMAN (Null Safety)
        // Kita cek dulu apakah komponennya ada (!= null) sebelum diisi data.

        // --- Komponen yang ada di KEDUA layout ---
        if (holder.tvTitle != null) holder.tvTitle.setText(currentBook.getTitle());
        if (holder.tvAuthor != null) holder.tvAuthor.setText(currentBook.getAuthor());
        if (holder.ivCover != null) holder.ivCover.setImageResource(currentBook.getImageResource());

        // --- Komponen yang HANYA ada di layout lama ---
        if (holder.tvDescription != null) holder.tvDescription.setText(currentBook.getDescription());

        // (Klik item tetap berfungsi untuk kedua layout)
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
            intent.putExtra(BookDetailActivity.EXTRA_TITLE, currentBook.getTitle());
            intent.putExtra(BookDetailActivity.EXTRA_AUTHOR, currentBook.getAuthor());
            intent.putExtra(BookDetailActivity.EXTRA_DESCRIPTION, currentBook.getDescription());
            intent.putExtra(BookDetailActivity.EXTRA_IMAGE_RES, currentBook.getImageResource());
            v.getContext().startActivity(intent);
        });
    }

    // 4. Menentukan seberapa banyak item yang mau ditampilkan
    @Override
    public int getItemCount() {
        return bookList.size();
    }

    // --- Inner Class: ViewHolder ---
    // Tugasnya menyambungkan variabel Java dengan ID yang ada di item_book.xml
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover, ivBookmark;
        TextView tvTitle, tvAuthor, tvDescription, tvViewEdition;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            // Sesuaikan ID ini dengan yang ada di item_book.xml kamu
            ivCover = itemView.findViewById(R.id.ivBookCover);
            ivBookmark = itemView.findViewById(R.id.ivBookmark);
            tvTitle = itemView.findViewById(R.id.tvBookTitle);
            tvAuthor = itemView.findViewById(R.id.tvBookAuthor);
            tvDescription = itemView.findViewById(R.id.tvBookDescription);
            tvViewEdition = itemView.findViewById(R.id.tvViewEdition);
        }
    }
}
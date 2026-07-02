package com.example.dclassicbooks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dclassicbooks.R;
import com.example.dclassicbooks.adapters.BookAdapter;
import com.example.dclassicbooks.data.BookData;
import com.example.dclassicbooks.models.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * BooksActivity - Menampilkan halaman daftar buku
 *
 * Fitur:
 * - Tab switching antara Fiction dan Non-Fiction
 * - RecyclerView dengan scrollable book cards
 * - Navigation drawer integration
 * - Menggunakan colors dan fonts dari file resources
 *
 * Layout: activity_books.xml
 * Component: item_book.xml (untuk setiap buku card)
 * Data: BookData.java
 * Adapter: BookAdapter.java
 *
 * Colors:
 * - Primary: #0E0911 (background)
 * - Secondary: #5D101D (drawer)
 * - Tertiary: #BC8F4B (accent/text highlight)
 * - Text: #EBE4D9 (text color)
 *
 * Fonts:
 * - Judul: Cinzel Bold (32sp)
 * - Sub-heading: Cormorant Garamond (24sp)
 * - Caption: Source Code Pro (12sp)
 */
public class BooksActivity extends AppCompatActivity {

    // UI Elements
    private DrawerLayout drawerLayout;
    private RecyclerView rvBooks;
    private BookAdapter bookAdapter;
    private ArrayList<Book> currentBooks;

    // Tab buttons
    private TextView tabFiction;
    private TextView tabNonFiction;

    // Current selected category
    private String selectedCategory = "Fiction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_books);

        // Handle system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        initializeUI();

        // Setup RecyclerView
        setupRecyclerView();

        // Load initial books (Fiction)
        loadBooksByCategory("Fiction");

        // Setup navigation drawer
        setupDrawerNavigation();
    }

    /**
     * Inisialisasi semua UI elements
     */
    private void initializeUI() {
        drawerLayout = findViewById(R.id.drawer_layout);
        rvBooks = findViewById(R.id.rvBooks);
        tabFiction = findViewById(R.id.tabFiction);
        tabNonFiction = findViewById(R.id.tabNonFiction);

        // Tab click listeners
        tabFiction.setOnClickListener(v -> switchCategory("Fiction"));
        tabNonFiction.setOnClickListener(v -> switchCategory("Non-Fiction"));

        // Open drawer button
        ImageView btnOpenDrawer = findViewById(R.id.btn_open_drawer);
        btnOpenDrawer.setOnClickListener(v -> drawerLayout.openDrawer(findViewById(R.id.drawer_start)));
    }

    /**
     * Setup RecyclerView untuk menampilkan list buku
     */
    private void setupRecyclerView() {
        rvBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    /**
     * Load buku berdasarkan kategori
     *
     * @param category Kategori buku ("Fiction" atau "Non-Fiction")
     */
    private void loadBooksByCategory(String category) {
        List<Book> allBooks = BookData.getBooks();
        currentBooks = new ArrayList<>();

        // Filter buku sesuai kategori
        for (Book book : allBooks) {
            if (book.getCategory().equals(category)) {
                currentBooks.add(book);
            }
        }

        // Setup adapter dengan buku yang sudah di-filter
        bookAdapter = new BookAdapter(currentBooks);
        rvBooks.setAdapter(bookAdapter);
    }

    /**
     * Switch kategori buku dan update UI
     *
     * @param category Kategori yang dipilih
     */
    private void switchCategory(String category) {
        selectedCategory = category;

        // Update tab styling
        View fictionUnderline = findViewById(R.id.fictionUnderline);
        View nonFictionUnderline = findViewById(R.id.nonFictionUnderline);

        if (category.equals("Fiction")) {
            tabFiction.setTextColor(getColor(R.color.secondary));
            tabNonFiction.setTextColor(getColor(R.color.text));
            if (fictionUnderline != null)
                fictionUnderline.setVisibility(View.VISIBLE);
            if (nonFictionUnderline != null)
                nonFictionUnderline.setVisibility(View.INVISIBLE);
        } else {
            tabFiction.setTextColor(getColor(R.color.text));
            tabNonFiction.setTextColor(getColor(R.color.secondary));
            if (fictionUnderline != null)
                fictionUnderline.setVisibility(View.INVISIBLE);
            if (nonFictionUnderline != null)
                nonFictionUnderline.setVisibility(View.VISIBLE);
        }

        // Load dan tampilkan buku dari kategori baru
        loadBooksByCategory(category);
    }

    /**
     * Setup navigation drawer dengan menu items
     */
    private void setupDrawerNavigation() {
        // Close button
        ImageView btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> drawerLayout.closeDrawer(findViewById(R.id.drawer_start)));

        // Menu items
        LinearLayout menuHome = findViewById(R.id.menu_home);
        LinearLayout menuBooks = findViewById(R.id.menu_books);
        LinearLayout menuStores = findViewById(R.id.menu_stores);
        LinearLayout menuLogout = findViewById(R.id.menu_logout);

        // Menu click listeners
        menuHome.setOnClickListener(v -> {
            startActivity(new Intent(BooksActivity.this, MainActivity.class));
            drawerLayout.closeDrawer(findViewById(R.id.drawer_start));
        });

        menuBooks.setOnClickListener(v -> {
            // Already on books page
            drawerLayout.closeDrawer(findViewById(R.id.drawer_start));
        });

        menuStores.setOnClickListener(v -> {
            startActivity(new Intent(BooksActivity.this, StoresActivity.class));
            drawerLayout.closeDrawer(findViewById(R.id.drawer_start));
        });

        menuLogout.setOnClickListener(v -> {
            // TODO: Implement logout
            drawerLayout.closeDrawer(findViewById(R.id.drawer_start));
        });
    }
}

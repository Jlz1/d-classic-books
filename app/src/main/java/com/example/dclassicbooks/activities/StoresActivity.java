package com.example.dclassicbooks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dclassicbooks.R;
import com.example.dclassicbooks.adapters.StoreAdapter;
import com.example.dclassicbooks.data.StoreData;
import com.example.dclassicbooks.models.Store;

import java.util.List;

/**
 * StoresActivity - Menampilkan halaman daftar toko buku
 *
 * Fitur:
 * - RecyclerView vertical scrollable dengan store cards
 * - Setiap card: full-width image, gradient overlay, nama, deskripsi, tombol
 * PLAN A VISIT
 * - Navigation drawer integration
 *
 * Layout: activity_stores.xml
 * Component: item_store.xml (untuk setiap store card)
 * Data: StoreData.java
 * Adapter: StoreAdapter.java
 *
 * Colors:
 * - Primary: #0E0911 (background)
 * - Secondary: #5D101D (drawer)
 * - Tertiary: #BC8F4B (description text)
 * - Text: #EBE4D9 (title, button)
 *
 * Fonts:
 * - Title: Cinzel Bold (32sp) — H1 style
 * - Description: Source Code Pro (12sp) — Caption style
 */
public class StoresActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView rvStores;
    private StoreAdapter storeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stores);

        // Handle system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.stores_drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        initializeUI();

        // Setup RecyclerView
        setupRecyclerView();

        // Setup navigation drawer
        setupDrawerNavigation();
    }

    /**
     * Inisialisasi semua UI elements
     */
    private void initializeUI() {
        drawerLayout = findViewById(R.id.stores_drawer_layout);
        rvStores = findViewById(R.id.rvStores);

        // Open drawer button
        ImageView btnOpenDrawer = findViewById(R.id.btn_open_drawer_stores);
        btnOpenDrawer.setOnClickListener(v -> drawerLayout.openDrawer(findViewById(R.id.drawer_start_stores)));
    }

    /**
     * Setup RecyclerView untuk menampilkan list toko secara vertical
     */
    private void setupRecyclerView() {
        List<Store> stores = StoreData.getStores();

        // PERBAIKAN: Tambahkan parameter R.layout.item_store di sini
        storeAdapter = new StoreAdapter(stores, R.layout.item_store);

        rvStores.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvStores.setAdapter(storeAdapter);
    }

    /**
     * Setup navigation drawer dengan menu items
     */
    private void setupDrawerNavigation() {
        // Close button
        ImageView btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> drawerLayout.closeDrawer(findViewById(R.id.drawer_start_stores)));

        // Menu items
        LinearLayout menuHome = findViewById(R.id.menu_home);
        LinearLayout menuBooks = findViewById(R.id.menu_books);
        LinearLayout menuStores = findViewById(R.id.menu_stores);
        LinearLayout menuLogout = findViewById(R.id.menu_logout);

        // Menu click listeners
        menuHome.setOnClickListener(v -> {
            startActivity(new Intent(StoresActivity.this, MainActivity.class));
            drawerLayout.closeDrawer(findViewById(R.id.drawer_start_stores));
        });

        menuBooks.setOnClickListener(v -> {
            startActivity(new Intent(StoresActivity.this, BooksActivity.class));
            drawerLayout.closeDrawer(findViewById(R.id.drawer_start_stores));
        });

        menuStores.setOnClickListener(v -> {
            // Already on stores page
            drawerLayout.closeDrawer(findViewById(R.id.drawer_start_stores));
        });

        menuLogout.setOnClickListener(v -> {
            // TODO: Implement logout
            drawerLayout.closeDrawer(findViewById(R.id.drawer_start_stores));
        });
    }
}

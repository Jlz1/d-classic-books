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

import com.example.dclassicbooks.R;

/**
 * MainActivity - Halaman utama aplikasi
 *
 * Fitur:
 * - Navigation drawer dengan menu items
 * - Navigation ke berbagai halaman (Books, Stores, dll)
 * - Hamburger menu untuk membuka drawer
 *
 * Navigation:
 * - HOME: Stay on MainActivity
 * - BOOKS: Navigate to BooksActivity
 * - STORES: Navigate to StoresActivity (TODO)
 * - LOG OUT: Logout functionality (TODO)
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize drawer and setup navigation
        initializeDrawerNavigation();
    }

    /**
     * Inisialisasi drawer layout dan setup semua menu click listeners
     */
    private void initializeDrawerNavigation() {
        drawerLayout = findViewById(R.id.drawer_layout);

        // Hamburger menu button
        ImageView btnOpenDrawer = findViewById(R.id.btn_open_drawer);
        btnOpenDrawer.setOnClickListener(v -> {
            if (drawerLayout != null) {
                drawerLayout.openDrawer(findViewById(R.id.drawer_start));
            }
        });

        // Close button in drawer
        ImageView btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> {
            if (drawerLayout != null) {
                drawerLayout.closeDrawer(findViewById(R.id.drawer_start));
            }
        });

        // Menu items
        LinearLayout menuHome = findViewById(R.id.menu_home);
        LinearLayout menuBooks = findViewById(R.id.menu_books);
        LinearLayout menuStores = findViewById(R.id.menu_stores);
        LinearLayout menuLogout = findViewById(R.id.menu_logout);

        // HOME - Stay on main page
        menuHome.setOnClickListener(v -> {
            if (drawerLayout != null) {
                drawerLayout.closeDrawer(findViewById(R.id.drawer_start));
            }
        });

        // BOOKS - Navigate to BooksActivity
        menuBooks.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, BooksActivity.class));
            if (drawerLayout != null) {
                drawerLayout.closeDrawer(findViewById(R.id.drawer_start));
            }
        });

        // STORES - Navigate to StoresActivity
        menuStores.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, StoresActivity.class));
            if (drawerLayout != null) {
                drawerLayout.closeDrawer(findViewById(R.id.drawer_start));
            }
        });

        // LOG OUT - TODO: Implement logout
        menuLogout.setOnClickListener(v -> {
            // TODO: Implement logout functionality
            if (drawerLayout != null) {
                drawerLayout.closeDrawer(findViewById(R.id.drawer_start));
            }
        });
    }
}
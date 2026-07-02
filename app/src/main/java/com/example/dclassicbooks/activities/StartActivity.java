package com.example.dclassicbooks.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dclassicbooks.R;
import com.google.android.material.button.MaterialButton;

/**
 * StartActivity - Start page aplikasi
 *
 * Menampilkan landing page dengan CTA:
 * - Log In: masuk ke LoginActivity
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);

        // Handle system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.start_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialButton btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v ->
            startActivity(new Intent(StartActivity.this, LoginActivity.class))
        );
    }
}

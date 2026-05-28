package com.example.dclassicbooks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dclassicbooks.R;
import com.google.android.material.button.MaterialButton;

/**
 * RegisterActivity - Halaman register aplikasi
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Handle system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText inputFullName = findViewById(R.id.input_full_name);
        EditText inputEmail = findViewById(R.id.input_email);
        EditText inputPassword = findViewById(R.id.input_password);
        TextView errorFullName = findViewById(R.id.error_full_name);
        TextView errorEmail = findViewById(R.id.error_email);
        TextView errorPassword = findViewById(R.id.error_password);
        MaterialButton btnRegister = findViewById(R.id.btn_register_submit);

        btnRegister.setOnClickListener(v -> {
            errorFullName.setVisibility(View.GONE);
            errorEmail.setVisibility(View.GONE);
            errorPassword.setVisibility(View.GONE);

            String fullName = inputFullName.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();
            String password = inputPassword.getText().toString();
            boolean hasError = false;

            if (fullName.isEmpty()) {
                errorFullName.setText(getString(R.string.register_fullname_required));
                errorFullName.setVisibility(View.VISIBLE);
                hasError = true;
            }
            if (email.isEmpty()) {
                errorEmail.setText(getString(R.string.register_email_required));
                errorEmail.setVisibility(View.VISIBLE);
                hasError = true;
            } else if (!email.contains("@")) {
                errorEmail.setText(getString(R.string.register_email_invalid));
                errorEmail.setVisibility(View.VISIBLE);
                hasError = true;
            }
            if (password.isEmpty()) {
                errorPassword.setText(getString(R.string.register_password_required));
                errorPassword.setVisibility(View.VISIBLE);
                hasError = true;
            }
            if (hasError) {
                return;
            }

            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        });
    }
}

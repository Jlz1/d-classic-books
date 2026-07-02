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
import com.example.dclassicbooks.data.UserData;
import com.google.android.material.button.MaterialButton;

/**
 * LoginActivity - Halaman login aplikasi
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Handle system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText inputUsername = findViewById(R.id.input_username);
        EditText inputPassword = findViewById(R.id.input_password);
        TextView errorUsername = findViewById(R.id.error_username);
        TextView errorPassword = findViewById(R.id.error_password);
        MaterialButton btnLogin = findViewById(R.id.btn_login_submit);

        btnLogin.setOnClickListener(v -> {
            errorUsername.setVisibility(View.GONE);
            errorPassword.setVisibility(View.GONE);

            String username = inputUsername.getText().toString().trim();
            String password = inputPassword.getText().toString();
            boolean hasError = false;

            if (username.isEmpty()) {
                errorUsername.setText(getString(R.string.login_username_required));
                errorUsername.setVisibility(View.VISIBLE);
                hasError = true;
            }
            if (password.isEmpty()) {
                errorPassword.setText(getString(R.string.login_password_required));
                errorPassword.setVisibility(View.VISIBLE);
                hasError = true;
            } else if (!password.matches("[a-zA-Z0-9]+")) {
                errorPassword.setText(getString(R.string.login_password_alphanumeric));
                errorPassword.setVisibility(View.VISIBLE);
                hasError = true;
            }
            if (hasError) {
                return;
            }

            UserData.setCurrentUsername(username);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });
    }
}

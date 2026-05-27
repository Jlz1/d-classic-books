package com.example.dclassicbooks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

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
        MaterialButton btnLogin = findViewById(R.id.btn_login_submit);

        btnLogin.setOnClickListener(v -> {
            inputUsername.setError(null);
            inputPassword.setError(null);

            String username = inputUsername.getText().toString().trim();
            String password = inputPassword.getText().toString();
            boolean hasError = false;

            if (username.isEmpty()) {
                inputUsername.setError(getString(R.string.login_username_required));
                hasError = true;
            }
            if (password.isEmpty()) {
                inputPassword.setError(getString(R.string.login_password_required));
                hasError = true;
            }
            if (hasError) {
                return;
            }

            if (UserData.validateUser(username, password)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                return;
            }

            inputPassword.setError(getString(R.string.login_invalid));
        });
    }
}

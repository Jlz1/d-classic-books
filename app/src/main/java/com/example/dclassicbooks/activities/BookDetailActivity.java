package com.example.dclassicbooks.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dclassicbooks.R;
import com.example.dclassicbooks.data.BookData;
import com.example.dclassicbooks.models.Book;
import com.google.android.material.button.MaterialButton;

import java.util.List;

/**
 * BookDetailActivity - Halaman detail buku
 */
public class BookDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_AUTHOR = "EXTRA_AUTHOR";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String EXTRA_IMAGE_RES = "EXTRA_IMAGE_RES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.book_detail_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnBack = findViewById(R.id.btn_back);
        ImageView btnBookmark = findViewById(R.id.btn_bookmark);
        ImageView cover = findViewById(R.id.iv_detail_cover);
        TextView title = findViewById(R.id.tv_detail_title);
        TextView author = findViewById(R.id.tv_detail_author);
        TextView description = findViewById(R.id.tv_detail_description);
        EditText inputShippingAddress = findViewById(R.id.input_shipping_address);
        EditText inputPhoneNumber = findViewById(R.id.input_phone_number);
        TextView errorShippingAddress = findViewById(R.id.error_shipping_address);
        TextView errorPhoneNumber = findViewById(R.id.error_phone_number);
        MaterialButton btnBuyNow = findViewById(R.id.btn_buy_now);

        btnBack.setOnClickListener(v -> finish());

        final boolean[] isBookmarked = {false};
        btnBookmark.setOnClickListener(v -> {
            isBookmarked[0] = !isBookmarked[0];
            int tintColor = isBookmarked[0] ? getColor(R.color.text) : getColor(R.color.tertiary);
            btnBookmark.setColorFilter(tintColor);
        });

        btnBuyNow.setOnClickListener(v -> {
            errorShippingAddress.setVisibility(View.GONE);
            errorPhoneNumber.setVisibility(View.GONE);

            String shippingAddress = inputShippingAddress.getText().toString().trim();
            String phoneNumber = inputPhoneNumber.getText().toString().trim();
            boolean hasError = false;

            if (shippingAddress.isEmpty()) {
                errorShippingAddress.setText(getString(R.string.shipping_address_required));
                errorShippingAddress.setVisibility(View.VISIBLE);
                hasError = true;
            }
            if (phoneNumber.isEmpty()) {
                errorPhoneNumber.setText(getString(R.string.phone_number_required));
                errorPhoneNumber.setVisibility(View.VISIBLE);
                hasError = true;
            }
            if (hasError) {
                return;
            }
        });

        Book fallbackBook = getDefaultBook();
        String bookTitle = getIntent().getStringExtra(EXTRA_TITLE);
        String bookAuthor = getIntent().getStringExtra(EXTRA_AUTHOR);
        String bookDescription = getIntent().getStringExtra(EXTRA_DESCRIPTION);
        int bookImage = getIntent().getIntExtra(EXTRA_IMAGE_RES, fallbackBook.getImageResource());

        if (bookTitle == null) {
            bookTitle = fallbackBook.getTitle();
        }
        if (bookAuthor == null) {
            bookAuthor = fallbackBook.getAuthor();
        }
        if (bookDescription == null) {
            bookDescription = fallbackBook.getDescription();
        }

        cover.setImageResource(bookImage);
        title.setText(bookTitle);
        author.setText(bookAuthor);
        description.setText(bookDescription);
    }

    private Book getDefaultBook() {
        List<Book> books = BookData.getBooks();
        if (!books.isEmpty()) {
            return books.get(0);
        }
        return new Book(
            "Harry Potter and the Deathly Hallows",
            "J. K. Rowling",
            "A breathtaking conclusion to a beloved epic, where the boundaries of magic and mortality are tested.",
            R.drawable.book_f_1,
            "Fiction"
        );
    }
}

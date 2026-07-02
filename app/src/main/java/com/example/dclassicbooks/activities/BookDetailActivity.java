package com.example.dclassicbooks.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

/**
 * BookDetailActivity - Halaman detail buku
 */
public class BookDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_AUTHOR = "EXTRA_AUTHOR";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String EXTRA_IMAGE_RES = "EXTRA_IMAGE_RES";
    private static final String PHONE_PREFIX = "+62 ";
    private static final int PHONE_GROUP_SIZE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_detail);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ImageView btnBack = findViewById(R.id.btn_back);
        ImageView btnBookmark = findViewById(R.id.btn_bookmark);
        ImageView cover = findViewById(R.id.iv_detail_cover);
        TextView title = findViewById(R.id.tv_detail_title);
        TextView author = findViewById(R.id.tv_detail_author);
        TextView description = findViewById(R.id.tv_detail_description);
        ScrollView detailScroll = findViewById(R.id.book_detail_root);
        EditText inputShippingAddress = findViewById(R.id.input_shipping_address);
        EditText inputPhoneNumber = findViewById(R.id.input_phone_number);
        MaterialButton btnBuyNow = findViewById(R.id.btn_buy_now);

        ViewCompat.setOnApplyWindowInsetsListener(detailScroll, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime());
            int imeBottom = insets.isVisible(WindowInsetsCompat.Type.ime()) ? imeInsets.bottom : 0;
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom + imeBottom);
            if (insets.isVisible(WindowInsetsCompat.Type.ime())) {
                scrollToFocusedField(detailScroll, inputShippingAddress, inputPhoneNumber);
            }
            return insets;
        });

        setupPhoneNumberFormatting(inputPhoneNumber);

        View.OnFocusChangeListener focusListener = (v, hasFocus) -> {
            if (hasFocus) {
                detailScroll.post(() -> scrollToFocusedField(detailScroll, inputShippingAddress, inputPhoneNumber));
                detailScroll.postDelayed(() -> scrollToFocusedField(detailScroll, inputShippingAddress, inputPhoneNumber), 150);
            } else if (!inputShippingAddress.hasFocus() && !inputPhoneNumber.hasFocus()) {
                detailScroll.post(() -> detailScroll.smoothScrollTo(0, 0));
            }
        };
        inputShippingAddress.setOnFocusChangeListener(focusListener);
        inputPhoneNumber.setOnFocusChangeListener(focusListener);

        btnBack.setOnClickListener(v -> finish());

        final boolean[] isBookmarked = {false};
        btnBookmark.setOnClickListener(v -> {
            isBookmarked[0] = !isBookmarked[0];
            int tintColor = getColor(R.color.tertiary);
            btnBookmark.setImageResource(isBookmarked[0] ? R.drawable.ic_bookmark_filled : R.drawable.ic_bookmark_custom);
            btnBookmark.setColorFilter(tintColor);
        });

        btnBuyNow.setOnClickListener(v -> {
            String shippingAddress = inputShippingAddress.getText().toString().trim();
            String phoneNumber = inputPhoneNumber.getText().toString().trim();
            String phoneDigits = extractDigitsAfterPrefix(phoneNumber);

            if (shippingAddress.isEmpty() || phoneDigits.isEmpty()) {
                showValidationError(getString(R.string.buy_now_fields_required));
                return;
            }
            if (!phoneDigits.matches("\\d+")) {
                showValidationError(getString(R.string.buy_now_phone_invalid));
                return;
            }

            new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.buy_now_success_title)
                .setMessage(R.string.buy_now_success_message)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> finish())
                .setOnCancelListener(dialog -> finish())
                .show();
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

    private void showValidationError(String message) {
        new MaterialAlertDialogBuilder(this)
            .setTitle(R.string.validation_error_title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show();
    }

    private void scrollToFocusedField(ScrollView scrollView, View... fields) {
        View focused = getCurrentFocus();
        if (focused == null) {
            return;
        }
        for (View field : fields) {
            if (field == focused) {
                int offset = scrollView.getHeight() / 3;
                int target = Math.max(0, field.getTop() - offset);
                scrollView.post(() -> scrollView.smoothScrollTo(0, target));
                break;
            }
        }
    }

    private void setupPhoneNumberFormatting(EditText inputPhoneNumber) {
        final boolean[] isFormatting = {false};

        inputPhoneNumber.setText(PHONE_PREFIX);
        inputPhoneNumber.setSelection(PHONE_PREFIX.length());
        inputPhoneNumber.setOnClickListener(v -> {
            int cursor = inputPhoneNumber.getSelectionStart();
            if (cursor < PHONE_PREFIX.length()) {
                inputPhoneNumber.setSelection(PHONE_PREFIX.length());
            }
        });
        inputPhoneNumber.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                boolean hasNumbers = !extractDigitsAfterPrefix(inputPhoneNumber.getText().toString()).isEmpty();
                if (!hasNumbers && inputPhoneNumber.getSelectionStart() <= PHONE_PREFIX.length()) {
                    return true;
                }
            }
            return false;
        });

        inputPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isFormatting[0]) {
                    return;
                }

                String original = s.toString();
                String afterPrefix = original.length() > PHONE_PREFIX.length()
                    ? original.substring(PHONE_PREFIX.length())
                    : "";
                if (!afterPrefix.matches("[0-9 ]*")) {
                    showValidationError(getString(R.string.buy_now_phone_invalid));
                }

                int cursorPosition = inputPhoneNumber.getSelectionStart();
                int safeCursor = Math.max(PHONE_PREFIX.length(), Math.min(cursorPosition, original.length()));
                int digitsBeforeCursor = countDigits(original.substring(PHONE_PREFIX.length(), safeCursor));
                String formatted = formatPhoneNumber(original);

                if (!formatted.equals(original)) {
                    isFormatting[0] = true;
                    inputPhoneNumber.setText(formatted);
                    String formattedNumbers = formatted.substring(PHONE_PREFIX.length());
                    int newCursor = PHONE_PREFIX.length() + getCursorForDigits(formattedNumbers, digitsBeforeCursor);
                    inputPhoneNumber.setSelection(Math.min(newCursor, formatted.length()));
                    isFormatting[0] = false;
                } else if (cursorPosition < PHONE_PREFIX.length()) {
                    inputPhoneNumber.setSelection(PHONE_PREFIX.length());
                }
            }
        });
    }

    private String formatPhoneNumber(String input) {
        String digits = extractDigitsAfterPrefix(input);
        StringBuilder formatted = new StringBuilder(PHONE_PREFIX);
        if (!digits.isEmpty()) {
            for (int i = 0; i < digits.length(); i++) {
                if (i > 0 && i % PHONE_GROUP_SIZE == 0) {
                    formatted.append(" ");
                }
                formatted.append(digits.charAt(i));
            }
        }
        return formatted.toString();
    }

    private int countDigits(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    private String extractDigitsAfterPrefix(String input) {
        if (input == null) {
            return "";
        }
        String working = input;
        if (working.startsWith(PHONE_PREFIX)) {
            working = working.substring(PHONE_PREFIX.length());
        } else {
            working = working.replace("+", "");
            if (working.startsWith("62")) {
                working = working.substring(2);
            }
        }
        return working.replaceAll("\\D", "");
    }

    private int getCursorForDigits(String input, int digitsBeforeCursor) {
        if (digitsBeforeCursor <= 0) {
            return 0;
        }
        int digitCount = 0;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                digitCount++;
                if (digitCount == digitsBeforeCursor) {
                    return i + 1;
                }
            }
        }
        return input.length();
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

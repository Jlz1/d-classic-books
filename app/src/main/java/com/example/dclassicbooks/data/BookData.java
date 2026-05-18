package com.example.dclassicbooks.data;

import com.example.dclassicbooks.R;
import com.example.dclassicbooks.models.Book;
import java.util.ArrayList;
import java.util.List;

public class BookData {
    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();

        // ===== FICTION BOOKS =====

        // book_f_1
        books.add(new Book(
            "Harry Potter and the Deathly Hallows",
            "J. K. Rowling",
            "A breathtaking conclusion to a beloved epic, where the boundaries of magic and mortality are tested. As shadows consume the realm, this final chapter weaves a profound narrative of courage, enduring friendship, and the ultimate sacrifice required to conquer darkness.",
            R.drawable.book_f_1,
            "Fiction"
        ));

        // book_f_2
        books.add(new Book(
            "Project Hail Mary",
            "Andy Weir",
            "A thrilling interstellar journey of survival and scientific discovery. A lone astronaut awakens with amnesia on a desperate mission to save humanity from an extinction-level threat, relying only on his wits and an unexpected alliance across the cosmos.",
            R.drawable.book_f_2,
            "Fiction"
        ));

        // book_f_3
        books.add(new Book(
            "Percy Jackson and the Olympians: The Chalice of the Gods",
            "Rick Riordan",
            "A legendary demigod returns for a perilous new quest where the stakes are deeply personal. To secure his future, he must once again navigate the treacherous whims of ancient deities and retrieve a powerful lost artifact before the celestial balance is shattered.",
            R.drawable.book_f_3,
            "Fiction"
        ));

        // book_f_4
        books.add(new Book(
            "To the Bright Edge of the World",
            "Eowyn Ivey",
            "An extraordinary tale of exploration and discovery set against the untamed wilderness of Alaska. This captivating narrative weaves together adventure, mystery, and human resilience as characters confront the raw power of nature and the depths of their own souls.",
            R.drawable.book_f_4,
            "Fiction"
        ));

        // ===== NON-FICTION BOOKS =====

        // book_nf_1
        books.add(new Book(
            "Beyond Good and Evil",
            "Friedrich Nietzsche",
            "A comprehensive overview of Nietzsche's\nmature philosophy.",
            R.drawable.book_nf_1,
            "Non-Fiction"
        ));

        // book_nf_2
        books.add(new Book(
            "Meditation",
            "Marcus Aurelius",
            "How to be great and calm like Roma Greatest Emperror\nmature philosophy.",
            R.drawable.book_nf_2,
            "Non-Fiction"
        ));

        // book_nf_3
        books.add(new Book(
            "How To Be A Stoic",
            "Massimo Picliucci",
            "How To Be a stoicism with a painfull method",
            R.drawable.book_nf_3,
            "Non-Fiction"
        ));

        return books;
    }
}

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
            "A daring descent into the complexities of human morality, where the boundaries of conventional truth and dogma are shattered. As it dismantles traditional philosophies, this profound masterpiece challenges the reader to embrace a radical understanding of power, freedom, and the untethered human spirit.",
            R.drawable.book_nf_1,
            "Non-Fiction"
        ));

        // book_nf_2
        books.add(new Book(
            "Meditations",
            "Marcus Aurelius",
            "An intimate glimpse into the mind of a Roman Emperor, offering timeless reflections on duty, resilience, and the nature of the cosmos. These personal writings serve as a profound guide to finding inner peace and maintaining moral integrity amidst the chaos of life.",
            R.drawable.book_nf_2,
            "Non-Fiction"
        ));

        // book_nf_3
        books.add(new Book(
            "How to Be a Stoic: Ancient Wisdom for Modern Living",
            "Massimo Pigliucci",
            "A practical exploration of ancient philosophy applied to contemporary challenges. This insightful guide demonstrates how the principles of Stoicism can cultivate emotional resilience, virtuous character, and a profound sense of meaning in an unpredictable world.",
            R.drawable.book_nf_3,
            "Non-Fiction"
        ));

        // book_nf_4
        books.add(new Book(
            "Principles for Dealing with The Changing World Order: Why Nations Succeed and Fail",
            "Ray Dalio",
            "A sweeping analysis of the economic and political cycles that have shaped the rise and fall of major empires throughout history. This compelling work provides a strategic framework for understanding current global shifts and navigating the uncertainties of the future.",
            R.drawable.book_nf_4,
            "Non-Fiction"
        ));

        return books;
    }
}

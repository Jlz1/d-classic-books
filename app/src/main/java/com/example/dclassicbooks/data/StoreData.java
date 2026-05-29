package com.example.dclassicbooks.data;

import com.example.dclassicbooks.R;
import com.example.dclassicbooks.models.Store;
import java.util.ArrayList;
import java.util.List;

public class StoreData {
    public static List<Store> getStores() {
        List<Store> stores = new ArrayList<>();

        // ===== STORES =====

        // store_1: Gramedia
        stores.add(new Store(
            "Gramedia",
            "The oldest bookstore in Indonesia avaliable in Jakarta",
            R.drawable.store_1
        ));

        // store_2: Periplus
        stores.add(new Store(
            "Periplus",
            "One of underated bookstore",
            R.drawable.store_2
        ));

        // store_3: Kinokuniya
        stores.add(new Store(
            "Kinokuniya",
            "Reference from japan massive book store",
            R.drawable.store_3
        ));

        // store_4: Gunung Agung
        stores.add(new Store(
            "Gunung Agung",
            "First Javaneese Book Store",
            R.drawable.store_4
        ));

        return stores;
    }
}

package com.example.dclassicbooks.data;

import com.example.dclassicbooks.models.Store;
import java.util.ArrayList;
import java.util.List;

public class StoreData {
    public static List<Store> getStores() {
        List<Store> stores = new ArrayList<>();

        // ===== STORES =====

        // store_1
        stores.add(new Store(
            "Gramedia",
            "A comprehensive retail destination offering a vast selection of local and international books, stationery, and multimedia products."
        ));

        // store_2
        stores.add(new Store(
            "Periplus",
            "A premium bookstore specializing in imported English-language literature, magazines, and high-quality lifestyle publications."
        ));

        // store_3
        stores.add(new Store(
            "Kinokuniya",
            "An internationally renowned bookstore providing an extensive, curated collection of diverse global titles, alongside a massive selection of Japanese literature."
        ));

        // store_4
        stores.add(new Store(
            "Gunung Agung",
            "A legendary Indonesian retail pioneer with a rich history of fulfilling the educational, office, and literary needs of the community."
        ));

        return stores;
    }
}


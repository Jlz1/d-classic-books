package com.example.dclassicbooks.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dclassicbooks.R;
import com.example.dclassicbooks.models.Store;

import java.util.List;

/**
 * StoreAdapter - Adapter untuk RecyclerView halaman Stores
 *
 * Menampilkan setiap toko sebagai item full-width dengan:
 * - Background image toko
 * - Dark overlay gradient
 * - Nama toko (H1 / Cinzel Bold / Color: Text)
 * - Deskripsi toko (Caption / Source Code Pro / Color: Tertiary)
 * - Tombol "PLAN A VISIT" (bordered)
 *
 * Layout item: item_store.xml
 * Model: Store.java
 * Data: StoreData.java
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private List<Store> storeList;

    public StoreAdapter(List<Store> storeList) {
        this.storeList = storeList;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Store currentStore = storeList.get(position);

        holder.ivStoreImage.setImageResource(currentStore.getImageResource());
        holder.tvStoreName.setText(currentStore.getName());
        holder.tvStoreDescription.setText(currentStore.getDescription());
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    // ViewHolder: Menghubungkan variabel Java dengan ID di item_store.xml
    public static class StoreViewHolder extends RecyclerView.ViewHolder {
        ImageView ivStoreImage;
        TextView tvStoreName, tvStoreDescription, tvPlanAVisit;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            ivStoreImage = itemView.findViewById(R.id.ivStoreImage);
            tvStoreName = itemView.findViewById(R.id.tvStoreName);
            tvStoreDescription = itemView.findViewById(R.id.tvStoreDescription);
            tvPlanAVisit = itemView.findViewById(R.id.tvPlanAVisit);
        }
    }
}

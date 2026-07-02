package com.example.dclassicbooks.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dclassicbooks.R;
import com.example.dclassicbooks.models.Store;

import java.util.List;

/**
 * StoreAdapter - Adapter multifungsi untuk RecyclerView dan ViewPager2 (Carousel)
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private List<Store> storeList;
    private int layoutId; // 1. Tambahkan variabel penampung ID layout

    // 2. Ubah Constructor untuk menerima parameter layoutId
    public StoreAdapter(List<Store> storeList, int layoutId) {
        this.storeList = storeList;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 3. Gunakan layoutId di sini, BUKAN hardcode R.layout.item_store lagi
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Store currentStore = storeList.get(position);

        holder.ivStoreImage.setImageResource(currentStore.getImageResource());
        holder.tvStoreName.setText(currentStore.getName());

        if (currentStore.getDescription() != null) {
            holder.tvStoreDescription.setText(currentStore.getDescription());
        }

        holder.tvPlanAVisit.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Kunjungi: " + currentStore.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return storeList == null ? 0 : storeList.size();
    }

    public static class StoreViewHolder extends RecyclerView.ViewHolder {
        ImageView ivStoreImage;
        TextView tvStoreName, tvStoreDescription, tvPlanAVisit;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            // PENTING: ID komponen di bawah ini harus SAMA PERSIS di kedua file XML kamu
            ivStoreImage = itemView.findViewById(R.id.ivStoreImage);
            tvStoreName = itemView.findViewById(R.id.tvStoreName);
            tvStoreDescription = itemView.findViewById(R.id.tvStoreDescription);
            tvPlanAVisit = itemView.findViewById(R.id.tvPlanAVisit);
        }
    }
}
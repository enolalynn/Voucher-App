package com.enola.voucherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enola.voucherapp.databinding.ItemProductBinding;
import com.enola.voucherapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

    private List<Product> products = new ArrayList<>();
    private  OnDeleteListener onDeleteListener;
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MainViewHolder(binding);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Product product = products.get(position);
        holder.binding.tvName.setText(product.name());
        holder.binding.tvPrice.setText(product.price().toString());
        holder.binding.tvQuantity.setText(product.quantity().toString());
        holder.binding.btDeleteProduct.setOnClickListener( v -> {
            onDeleteListener.OnDelete(product);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding binding;

        MainViewHolder(ItemProductBinding binding){
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}

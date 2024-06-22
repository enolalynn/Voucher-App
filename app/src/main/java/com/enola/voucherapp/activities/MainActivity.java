package com.enola.voucherapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.enola.voucherapp.R;
import com.enola.voucherapp.adapters.MainAdapter;
import com.enola.voucherapp.databinding.ActivityMainBinding;
import com.enola.voucherapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Product> productList = new ArrayList<>();
    private ActivityMainBinding binding;
    private MainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initListeners();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==123 && resultCode == RESULT_OK){
            String name = data.getStringExtra("name");
            Double price = data.getDoubleExtra("price", 0.0);
            Integer quantity = data.getIntExtra("quantity",0);
            Product newProduct = new Product("", name, price,quantity);
            productList.add(newProduct);
            updateListData(productList);

        }
    }

    private void initUi() {
        mainAdapter = new MainAdapter();
        binding.rvProducts.setAdapter(mainAdapter);
        binding.rvProducts.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter.setProducts(productList);
    }

    private void initListeners() {
        binding.fabAddProduct.setOnClickListener(v->{
            Intent intent = new Intent(this, AddProductActivity.class);
            startActivityForResult(intent, 123);
        });

        mainAdapter.setOnDeleteListener(product -> {
            productList.remove(product);
            updateListData(productList);
        });
    }

    private void updateListData(List<Product> productList) {
        mainAdapter.setProducts(productList);
        Double totalAmount = 0.0;
        for (Product product : productList){
            totalAmount += (product.price() * product.quantity());
        }
        binding.tvTotalAmount.setText(totalAmount.toString());
    }
}
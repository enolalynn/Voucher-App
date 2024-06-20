package com.enola.voucherapp.model;

public record Product(
        String imageUrl,
        String name,
        Double price,
        Integer quantity
) {
}

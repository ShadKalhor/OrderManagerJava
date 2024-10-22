package Entities;

import java.util.UUID;

public record Item(
        UUID id,
        String name,
        String description,
        double price,
        String size,
        double discount,
        boolean isAvailable,
        int quantity
) {}

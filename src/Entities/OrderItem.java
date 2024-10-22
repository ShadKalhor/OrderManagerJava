package Entities;

import java.util.UUID;

public record OrderItem(
        UUID id,
        String orderId,
        String itemId,
        int quantity,
        double totalPrice
) {}

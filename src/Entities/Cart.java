package Entities;

import java.util.UUID;

public record Cart (
        UUID id,
        int quantity,
        String itemId,
        double subTotal
) {}

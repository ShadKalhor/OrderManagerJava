package Entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Order(
        UUID id,
        String userId,
        String addressId,
        String driverId,
        Utilities.Status status,
        Utilities.DeliveryStatus deliveryStatus,
        List<OrderItem> items,
        double subTotal,
        double deliveryFee,
        double tax,
        double totalPrice,
        String notes
) {}

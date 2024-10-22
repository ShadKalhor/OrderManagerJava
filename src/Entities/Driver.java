package Entities;

import java.util.UUID;

public record Driver(
        UUID id,
        String name,
        String phone,
        String vehicleNumber,
        int age
) {}

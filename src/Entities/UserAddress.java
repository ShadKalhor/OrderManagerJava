package Entities;

import java.util.UUID;

public record UserAddress(
        UUID id,
        String userId,
        String name,
        String city,
        String description,
        String type,
        String street,
        String residentialNo
) {}

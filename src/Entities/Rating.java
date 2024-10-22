package Entities;

import java.util.UUID;

public record Rating(
        UUID id,
        String userId,
        String itemId,
        double rating
) {}

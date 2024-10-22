package Entities;

import java.time.LocalDateTime;
import java.util.UUID;

public record ItemImage(
        UUID id,
        String itemId,
        String imageURL,
        Boolean isPrimary,
        LocalDateTime uploadedAt
) {}

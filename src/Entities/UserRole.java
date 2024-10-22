package Entities;

import java.util.UUID;

public record UserRole(
        UUID id,
        String roleName
) {}

package Entities;

import java.util.UUID;

public record User(
        UUID id,
        String roleId,
        String name,
        String phone,
        String password,
        Utilities.Genders gender
) {}

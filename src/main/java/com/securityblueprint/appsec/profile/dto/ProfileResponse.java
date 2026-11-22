package com.securityblueprint.appsec.profile.dto;


import com.securityblueprint.appsec.profile.entity.Profile;

import java.time.Instant;

public record ProfileResponse(
        Long id,
        String displayName,
        String bio,
        Instant createdAt,
        Instant updatedAT
) {
    public  static ProfileResponse from (Profile profile) {
        return  new ProfileResponse(
                profile.getId(),
                profile.getDisplayName(),
                profile.getBio(),
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}

package com.securityblueprint.appsec.profile.service;

import com.securityblueprint.appsec.identity.service.CurrentUserService;
import com.securityblueprint.appsec.profile.dto.UpdateProfileRequest;
import com.securityblueprint.appsec.profile.entity.Profile;
import com.securityblueprint.appsec.profile.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
    private  final ProfileRepository profileRepository;
    private final CurrentUserService currentUserService;

    public ProfileService (
            ProfileRepository profileRepository,
            CurrentUserService currentUserService
    ) {
        this.profileRepository = profileRepository;
        this.currentUserService = currentUserService;
    }

    @Transactional
    public Profile getMyProfile() {
        return getOrCreateMyProfile();
    }

    @Transactional
    public Profile updateMyProfile(UpdateProfileRequest request) {
        Profile profile = getOrCreateMyProfile();

        profile.updateProfile(
                request.displayName(),
                request.bio()
        );
        return profileRepository.save(profile);
    }

    @Transactional
    public Profile getOrCreateMyProfile() {
        var currentUserId = currentUserService.getCurrentUserId();

        return profileRepository
                .findByKeycloakSubject(currentUserId)
                .orElseGet(() -> {
                    Profile newProfile = new Profile(
                            currentUserId,
                            currentUserService.getCurrentUser()
                                    .username()
                    );

                    return profileRepository.save(newProfile);
                });
    }
}

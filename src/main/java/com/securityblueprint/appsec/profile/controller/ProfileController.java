package com.securityblueprint.appsec.profile.controller;

import com.securityblueprint.appsec.profile.dto.ProfileResponse;
import com.securityblueprint.appsec.profile.dto.UpdateProfileRequest;
import com.securityblueprint.appsec.profile.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getMyProfile() {
        var profile = profileService.getMyProfile();

        return ResponseEntity.ok(
                ProfileResponse.from(profile)
        );
    }

    @PutMapping("/me")
    public ResponseEntity<ProfileResponse> updateMyProfile(
        @Valid @RequestBody UpdateProfileRequest request) {

        var profile = profileService.updateMyProfile(request);

        return ResponseEntity.ok(
                ProfileResponse.from(profile)
        );
    }
}

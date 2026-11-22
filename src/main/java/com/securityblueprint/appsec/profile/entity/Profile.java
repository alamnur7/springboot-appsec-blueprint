package com.securityblueprint.appsec.profile.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
        name = "profiles",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_profiles_keycloak_subject",
                        columnNames = "keycloak_subject"
                )
        }
)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "keycloak_subject",
            nullable = false,
            unique = true,
            length = 255
    )
    private String keycloakSubject;

    @Column(
            name = "display_name",
            nullable = false,
            length = 100
    )
    private String displayName;

    @Column(
            length = 500
    )
    private String bio;

    @Column(
            name = "created_at",
            nullable = false
    )
    private Instant createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    private  Instant updatedAt;

    protected Profile() {
    }

    public Profile(String keycloakSubject, String displayName){
        this.keycloakSubject = keycloakSubject;
        this.displayName = displayName;
    }

    @PrePersist
    protected  void onCreated() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public  Long getId(){
        return  id;
    }

    public String getKeycloakSubject() {
        return keycloakSubject;
    }

    public  String getDisplayName() {
        return displayName;
    }

    public String getBio() {
        return bio;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void updateProfile(String displayName, String bio) {
        this.displayName = displayName;
        this.bio = bio;
    }
}

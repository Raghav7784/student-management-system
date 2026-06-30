package com.example.demo.dto;

public class UserResponseDTO {

    private Long userId;
    private String username;
    private String email;
    private boolean enabled;
    private String roleName;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long userId, String username, String email, boolean enabled, String roleName) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
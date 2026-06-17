package com.smartindustries.smartlock.platform.administration.interfaces.rest.resources;

public record UserWithRoleResource(Long userId, String email, String firstName, String lastName, Long roleId, String roleName) {
}

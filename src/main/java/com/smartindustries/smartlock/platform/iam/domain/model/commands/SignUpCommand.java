package com.smartindustries.smartlock.platform.iam.domain.model.commands;

public record SignUpCommand(String firstName, String lastName, String email, String password) {
}

package com.smartindustries.smartlock.platform.iam.domain.commands;

public record SignUpCommand(String firstName, String lastName, String email, String password) {
}

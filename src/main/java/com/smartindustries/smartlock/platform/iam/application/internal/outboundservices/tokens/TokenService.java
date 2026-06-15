package com.smartindustries.smartlock.platform.iam.application.internal.outboundservices.tokens;

public interface TokenService {
    String generateToken(String email);

    String getUserEmailFromToken(String token);

    boolean validateToken(String token);
}

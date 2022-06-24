package org.gradientworks.passwordmanager.util.jwt;

public class JwtUtil {

    public static String getTokenFromAuthHeader(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }
}


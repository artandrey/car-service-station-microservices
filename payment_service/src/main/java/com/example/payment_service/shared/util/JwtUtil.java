package com.example.payment_service.shared.util;

import java.io.IOException;
import java.util.Base64;

import com.example.payment_service.shared.models.Identity;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtUtil {

    public static String[] decodeJWT(String jwt) {
        String[] splitToken = jwt.replace("Bearer ", "").split("\\.");
        String header = new String(Base64.getUrlDecoder().decode(splitToken[0]));
        String body = new String(Base64.getUrlDecoder().decode(splitToken[1]));
        String signature = splitToken[2];

        return new String[] { header, body, signature };
    }

    public static Identity mapJwtToUser(String jwt) {
        String[] parts = decodeJWT(jwt);
        String body = parts[1];

        // Convert body JSON string to User object
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(body, Identity.class);
        } catch (IOException e) {
            throw new RuntimeException("Not working");
        }

    }
}

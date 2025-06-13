package com.gentleninja.utilities;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class SecretGenerator {
    String key = Base64.getEncoder().encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());

    public String getKey(String key) {
        return "Generated key: " + key;
    }
}

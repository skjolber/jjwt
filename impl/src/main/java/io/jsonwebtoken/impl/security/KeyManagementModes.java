package io.jsonwebtoken.impl.security;

import javax.crypto.SecretKey;

public final class KeyManagementModes {

    private KeyManagementModes(){}

    public static KeyManagementMode direct(SecretKey secretKey) {
        return new DirectEncryptionMode(secretKey);
    }
}

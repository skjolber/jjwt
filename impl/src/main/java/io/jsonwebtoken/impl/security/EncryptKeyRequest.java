package io.jsonwebtoken.impl.security;

import javax.crypto.SecretKey;

/**
 * @since 0.11.0
 */
public interface EncryptKeyRequest {

    SecretKey getKey();

}

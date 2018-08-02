package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.security.EncryptionAlgorithm;

/**
 * @since 0.11.0
 */
public interface GetKeyRequest {

    /**
     * Returns the encryption algorithm that will be used to encrypt the JWE payload.  A {@link KeyManagementMode}
     * implementation can inspect this to return or generate a key that matches the required algorithm key length.
     *
     * @return the encryption algorithm that will be used to encrypt the JWE payload.
     */
    EncryptionAlgorithm getEncryptionAlgorithm();
}

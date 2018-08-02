package io.jsonwebtoken.security;

import io.jsonwebtoken.JweHeader;

/**
 * @since 0.11.0
 */
public interface EncryptionAlgorithmLocator {

    EncryptionAlgorithm getEncryptionAlgorithm(JweHeader jweHeader);
}

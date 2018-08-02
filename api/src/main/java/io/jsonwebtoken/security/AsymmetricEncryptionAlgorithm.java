package io.jsonwebtoken.security;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @since 0.11.0
 */
public interface AsymmetricEncryptionAlgorithm extends EncryptionAlgorithm<PrivateKey, PublicKey> {
}

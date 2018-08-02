package io.jsonwebtoken.impl.security;

import javax.crypto.SecretKey;

public class DirectKeyAgreementMode implements KeyManagementMode {

    @Override
    public SecretKey getKey(GetKeyRequest request) {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }
}

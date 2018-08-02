package io.jsonwebtoken.impl.security;

public class KeyWrappingMode extends RandomEncryptedKeyMode {

    @Override
    public byte[] encryptKey(EncryptKeyRequest request) {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }
}

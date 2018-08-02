package io.jsonwebtoken;

/**
 * @param <B> payload type
 * @since 0.11.0
 */
public interface Jwe<B> extends Jwt<JweHeader,B> {

    byte[] getInitializationVector();

    byte[] getAadTag();
}

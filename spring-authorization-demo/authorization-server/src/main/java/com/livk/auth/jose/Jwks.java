package com.livk.auth.jose;

import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.UUID;

/**
 * <p>
 * Jwks
 * </p>
 *
 * @author livk
 * @date 2021/12/3
 */
@UtilityClass
public class Jwks {

    public RSAKey generateRsa() {
        var keyPair = KeyGeneratorUtils.generateRsaKey();
        var publicKey = (RSAPublicKey) keyPair.getPublic();
        var privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    public ECKey generateEc() {
        var keyPair = KeyGeneratorUtils.generateEcKey();
        var publicKey = (ECPublicKey) keyPair.getPublic();
        var privateKey = (ECPrivateKey) keyPair.getPrivate();
        var curve = Curve.forECParameterSpec(publicKey.getParams());
        return new ECKey.Builder(curve, publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    public OctetSequenceKey generateSecret() {
        var secretKey = KeyGeneratorUtils.generateSecretKey();
        return new OctetSequenceKey.Builder(secretKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }
}

@UtilityClass
final class KeyGeneratorUtils {

    @SneakyThrows
    SecretKey generateSecretKey() {
        return KeyGenerator.getInstance("HmacSha256").generateKey();
    }

    @SneakyThrows
    KeyPair generateRsaKey() {
        var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    @SneakyThrows
    KeyPair generateEcKey() {
        var ellipticCurve = new EllipticCurve(
                new ECFieldFp(new BigInteger("115792089210356248762697446949407573530086143415290314195533631308867097853951")),
                new BigInteger("115792089210356248762697446949407573530086143415290314195533631308867097853948"),
                new BigInteger("41058363725152142129326129780047268409114441015993725554835256314039467401291"));
        var ecPoint = new ECPoint(
                new BigInteger("48439561293906451759052585252797914202762949526041747995844080717082404635286"),
                new BigInteger("36134250956749795798585127919587881956611106672985015071877198253568414405109"));
        var ecParameterSpec = new ECParameterSpec(ellipticCurve, ecPoint,
                new BigInteger("115792089210356248762697446949407573529996955224135760342422259061068512044369"), 1);
        var keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(ecParameterSpec);
        return keyPairGenerator.generateKeyPair();
    }
}

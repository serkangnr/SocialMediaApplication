package com.serkanguner.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.serkanguner.exception.AuthServiceException;
import com.serkanguner.exception.ErrorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {
    @Value("${authservice.secret.secret-key}")
    String secretKey;
    Long expireTime = 1000L * 60 * 15; //15 dakikalik bir zaman
    @Value("${authservice.secret.issuer}")
    String issuer;

    /**
     * Dikkat!
     * Claim objeleri icindeki degerler herkes tarafindan gorulebilir.
     * O yuzden claimler ile e-mail , password gibi herkesin gormesini istemedigimiz bilgileri payload kisminda tutmaliyiz.
     */

    //1. Token uretmeli
    public Optional<String> createToken(Long id) {
        String token = "";
        try {
            token = JWT.create()
                    .withClaim("id", id)
                    .withClaim("whichpage", "AuthMicroService")
                    .withClaim("ders", "Java JWT")
                    .withClaim("group", "Java14")
                    .withIssuer("Java14")
                    .withIssuedAt(new Date()) // Tokenin yaratildigi an
                    .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        } catch (IllegalArgumentException e) {
            throw new AuthServiceException(ErrorType.TOKEN_CREATION_FAILED);
        } catch (JWTCreationException e) {
            throw new AuthServiceException(ErrorType.TOKEN_CREATION_FAILED);
        }

    }


    //2. Token Dogrulanmali
//    public Optional<Long> verifyToken(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC512(secretKey);
//            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
//            DecodedJWT decodedJWT = verifier.verify(token);
//
//            if (decodedJWT==null) {
//                return Optional.empty();
//            }
//            Long id = decodedJWT.getClaim("id").asLong();
//
//            return Optional.of(id);
//
//        } catch (IllegalArgumentException e) {
//            throw new AuthServiceException((ErrorType.TOKEN_ARGUMENT_NOTVALID));
//        } catch (JWTVerificationException e) {
//            throw new AuthServiceException(ErrorType.TOKEN_VERIFY_FAILED);
//        }
//
//    }

    //3 Tokendan bilgi cakirimi yapmali
    public Optional<Long> getIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT==null) {
                return Optional.empty();
            }
            Long id = decodedJWT.getClaim("id").asLong();

            return Optional.of(id);

        } catch (IllegalArgumentException e) {
            throw new AuthServiceException((ErrorType.TOKEN_ARGUMENT_NOTVALID));
        } catch (JWTVerificationException e) {
            throw new AuthServiceException(ErrorType.TOKEN_VERIFY_FAILED);
        }
    }
}

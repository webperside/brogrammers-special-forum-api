package com.webperside.brogrammersspecialforum.security;

import com.webperside.brogrammersspecialforum.enums.ErrorEnum;
import com.webperside.brogrammersspecialforum.exception.RestException;
import com.webperside.brogrammersspecialforum.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenUtil {

    @Value("${jwt.token.private-key}")
    private String privateKey;

    @Value("${jwt.token.public-key}")
    private String publicKey;

    @Value("${jwt.token.access-token-validity-time}")
    private long accessTokenValidityTime;

    @Value("${jwt.token.refresh-token-validity-time}")
    private long refreshTokenValidityTime;

    private final JwtUserDetailsService userDetailsService;

    public String getUsernameFromToken(String token) {
        return getTokenBody(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getTokenBody(token).getExpiration();
    }

    public String createAccessToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("role", user.getRole().getName());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + accessTokenValidityTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                .compact();
    }

    public String createRefreshToken(User user, boolean rememberMe) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("role", "REFRESH_TOKEN");

        Date now = new Date();
        Date expiration = null;

        if (rememberMe) expiration = new Date(now.getTime() + refreshTokenValidityTime * 90);
        else expiration = new Date(now.getTime() + refreshTokenValidityTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                .compact();
    }

    public boolean validateAccessToken(String token) {

        try{
            Claims claims = getTokenBody(token);

            return claims != null && !claims.getExpiration().before(new Date());
        } catch (Exception ex){
            throw new RestException(ErrorEnum.ACCESS_TOKEN_EXPIRED_EXCEPTION);
        }
    }

    public boolean validateRefreshToken(String token) {
        try{
            Claims claims = getTokenBody(token);

            return claims != null && !claims.getExpiration().before(new Date());
        } catch (Exception ex){
            throw new RestException(ErrorEnum.REFRESH_TOKEN_EXPIRED_EXCEPTION);
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // private util methods

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
        } catch (JwtException ex) {
            log.error(ex.getMessage() + "salam");
            throw ex;
        }
    }

    private PrivateKey getPrivateKey() {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            return kf.generatePrivate(keySpecPKCS8);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PublicKey getPublicKey() {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            return kf.generatePublic(keySpecX509);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}

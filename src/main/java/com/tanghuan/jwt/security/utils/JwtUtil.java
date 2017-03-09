package com.tanghuan.jwt.security.utils;

import com.tanghuan.jwt.security.entity.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * Created by Arthur on 2017/3/9.
 */

public class JwtUtil {

    private static final String AUDIENCE_UNKNOWN = "UNKNOWN";
    private static final String AUDIENCE_WEB = "WEB";
    private static final String AUDIENCE_MOBILE = "MOBILE";
    private static final String AUDIENCE_TABLET = "TABLET";

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expira}")
    private int expiration;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = getClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 检查Token是否过期
     * @param issuedAt 返回true表示已经过期，返回false表示未过期
     * @return
     */
    private Date generateExpirationDate(Date issuedAt) {
        issuedAt.getTime();
        return new Date(issuedAt.getTime() + expiration * 1000);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private String generateAudience(Device device) {
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;
        }
        return audience;
    }

    private Boolean ignoreTokenExpiration(String token) {
        String audience = getAudienceFromToken(token);
        return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
    }

    public String generateToken(UserDetails userDetails, Device device) {
        Claims claims = Jwts.claims();

        // 该JWT的签发者
        claims.setIssuer("bdsecur.mrray.com");

        // 该JWT所面向的用户
        claims.setSubject(userDetails.getUsername());

        // 接收JWT的一方
        claims.setAudience(generateAudience(device));

        // 该JWT是什么时候签发的
        Date issuedAt = new Date(System.currentTimeMillis());
        claims.setIssuedAt(issuedAt);

        // 该JWT什么时候过期/JWT的有效期多长
        Date exp = generateExpirationDate(issuedAt);
        claims.setExpiration(exp);

        return generateToken(claims);
    }

    String generateToken(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return !isTokenExpired(token) || ignoreTokenExpiration(token);
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            // 签发时间
            Date issuedAt = new Date(System.currentTimeMillis());
            claims.setIssuedAt(issuedAt);

            // 过期时间
            Date exp = generateExpirationDate(issuedAt);
            claims.setExpiration(exp);
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        UserDetailsImpl user = (UserDetailsImpl) userDetails;
        final String username = getUsernameFromToken(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);

    }
}

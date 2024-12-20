package com.RaazDk.eComs.security.jwt;



import com.RaazDk.eComs.models.EcomUser;
import com.RaazDk.eComs.security.services.EcomUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static  final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.app.jwtExpirationMs}")
    private String jwtExpirationMs;

    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        logger.debug("Bearer Toekn :{}",bearerToken);
        if(bearerToken !=null && bearerToken.startsWith("Bearer")){


        return  bearerToken.substring(7);
        }
        return null;
    }



    public String generateTokenFromUsername(EcomUserDetails user){
        String username = user.getUsername();
        long expDate = new Date().getTime()+Long.parseLong(jwtExpirationMs);
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(expDate))
                .signWith(key())
                .compact()
                ;
    }

    public String getUserNameFromToken(String Token){

       return Jwts.parser().verifyWith((SecretKey) key())
               .build().parseSignedClaims(Token)
               .getPayload().getSubject();

    }


    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken){
        try{
                Jwts.parser().verifyWith((SecretKey)key()).build()
                        .parseSignedClaims(authToken);
                return true;
        }catch (MalformedJwtException e){
            logger.error("Invalid token for :{}",e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("Expired Token :{}",e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("Empty String", e.getMessage());
        }

        return  false;
    }

}

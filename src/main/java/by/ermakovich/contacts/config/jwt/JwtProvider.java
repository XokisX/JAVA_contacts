package by.ermakovich.contacts.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

@Log4j2
@Component
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    public String generateToken(String login){
        return Jwts.builder()
                .setSubject(login)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (Exception exception){
            log.info(exception.toString());
        }
        return false;
    }

    public String getLoginFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

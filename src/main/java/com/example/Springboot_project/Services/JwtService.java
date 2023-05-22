package com.example.Springboot_project.Services;
import com.example.Springboot_project.Controllers.authenticationController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JwtService {
    private static final String SECRET_KEY="3778214125442A472D4A614E645267556B58703273357638792F423F4528482B";
    public String ExtractUsername(String token){
        return extractClaim(token,Claims::getSubject);//subject can be email or username
    }
    public String ExtractRole(String token){
     String username=ExtractUsername(String token);
     extractClaim(token,)
    }
    public <T> T extractClaim(String token, Function<Claims,T> ClaimResolver){//generic Method for extracting just one claim
      final Claims claims=extractAllClaims(token);
        return ClaimResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){//extracting all claims for  extracting username
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String generatorToken(UserDetails userDetails){
        return generatorToken(new HashMap<>(),userDetails);
    }//if I want to generate token without the claims
    public String generatorToken(
            Map<String,Object> extraClaims,//adding claims
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))//check if the token is valid
   .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))//check if the token is expired before 24hours +1000mill
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();//compact for generate the token
         }
         public boolean isTokenValid(String token,UserDetails userDetails){
        final String username=ExtractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);//to check if the username of the login equals the username of Userdetails

         }
         private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
         }

    private Date extractExpiration(String token) {//extract Expiration from claim
        return extractClaim(token,Claims::getExpiration);
    }

    private Key getSignInKey(){
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);//Algorithm of extracting the signin token
    }

}

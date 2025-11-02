package com.lelox028.StudyPlanManagerApi.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret:mySecretKeyForStudyPlanManagerApiProjectByLelox028ThisShouldBeAtLeast256BitsLong}")
    private String secretKey;  // Clave secreta para firmar/verificar tokens (debe ser segura y larga)

    @Value("${jwt.expiration:86400000}")  // Tiempo de expiración en ms (24 horas por defecto)
    private long jwtExpiration;

    // Extrae el username del token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);  // El subject es el username
    }

    // Método genérico para extraer cualquier claim (dato) del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);  // Obtiene todos los claims
        return claimsResolver.apply(claims);  // Aplica la función para extraer el dato específico
    }

    // Genera un token con claims extra (ej. roles) y datos del usuario
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    // Genera un token básico sin claims extra
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Construye el token con firma, expiración y claims
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .setClaims(extraClaims)  // Agrega claims extra (ej. roles)
                .setSubject(userDetails.getUsername())  // Setea el username como subject
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + expiration))  // Fecha de expiración
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)  // Firma con clave secreta
                .compact();  // Genera el token como string
    }

    // Valida si el token es válido para el usuario (username coincide y no expirado)
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Verifica si el token ha expirado
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extrae la fecha de expiración del token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extrae todos los claims del token (después de verificar firma)
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())  // Usa la clave secreta para verificar
                .build()
                .parseClaimsJws(token)  // Parsea y valida el token
                .getBody();  // Devuelve los claims
    }

    // Genera la clave de firma a partir del secret (debe ser segura)
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);  // Decodifica el secret
        return Keys.hmacShaKeyFor(keyBytes);  // Crea clave HMAC para HS256 a partir de los bytes del secret
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }
}
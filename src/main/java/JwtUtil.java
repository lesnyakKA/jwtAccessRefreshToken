import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    // Секретный ключ для подписи JWT
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Время жизни Access Token (15 минут)
    private static final long ACCESS_TOKEN_EXPIRATION = 900000; // 15 минут в миллисекундах

    // Время жизни Refresh Token (7 дней)
    private static final long REFRESH_TOKEN_EXPIRATION = 604800000; // 7 дней в миллисекундах

    // Создание Access Token
    public static String generateAccessToken(String username) {
        return Jwts.builder()
            .setSubject(username) // Устанавливаем subject (обычно это username)
            .setIssuedAt(new Date()) // Устанавливаем время создания токена
            .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION)) // Срок действия
            .signWith(SECRET_KEY) // Подписываем токен
            .compact(); // Преобразуем в строку
    }

    // Создание Refresh Token
    public static String generateRefreshToken(String username) {
        return Jwts.builder()
            .setSubject(username) // Устанавливаем subject
            .setIssuedAt(new Date()) // Устанавливаем время создания токена
            .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION)) // Срок действия
            .signWith(SECRET_KEY) // Подписываем токен
            .compact(); // Преобразуем в строку
    }

    // Проверка и извлечение данных из токена
    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY) // Устанавливаем ключ для проверки подписи
            .build()
            .parseClaimsJws(token) // Парсим токен
            .getBody(); // Получаем данные (claims)
    }

    // Проверка валидности токена
    public static boolean validateToken(String token) {
        try {
            parseToken(token); // Пытаемся распарсить токен
            return true; // Если успешно, токен валиден
        } catch (Exception e) {
            return false; // Если возникла ошибка, токен невалиден
        }
    }
}
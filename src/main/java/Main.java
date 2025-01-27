import io.jsonwebtoken.Claims;

public class Main {
    public static void main(String[] args) {
        String username = "user123";

        // Генерация Access Token и Refresh Token
        String accessToken = JwtUtil.generateAccessToken(username);
        String refreshToken = JwtUtil.generateRefreshToken(username);

        System.out.println("Access Token: " + accessToken);
        System.out.println("Refresh Token: " + refreshToken);

        // Проверка Access Token
        if (JwtUtil.validateToken(accessToken)) {
            System.out.println("Access Token валиден.");
            Claims claims = JwtUtil.parseToken(accessToken);
            System.out.println("Данные из Access Token:");
            System.out.println("Subject (username): " + claims.getSubject());
            System.out.println("Выдан: " + claims.getIssuedAt());
            System.out.println("Истекает: " + claims.getExpiration());
        } else {
            System.out.println("Access Token невалиден.");
        }

        // Имитация истечения Access Token и обновление с помощью Refresh Token
        System.out.println("\nИмитация истечения Access Token...");

        // Проверка Refresh Token
        if (JwtUtil.validateToken(refreshToken)) {
            System.out.println("Refresh Token валиден.");

            // Генерация нового Access Token
            String newAccessToken = JwtUtil.generateAccessToken(username);
            System.out.println("Новый Access Token: " + newAccessToken);
        } else {
            System.out.println("Refresh Token невалиден.");
        }
    }
}
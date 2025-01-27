# jwtAccessRefreshToken
Реализация JWT с Access Token и Refresh Token с использованием библиотеки JJWT в Java.

Как это работает
1) Access Token:

2) Используется для доступа к защищенным ресурсам.

3) Имеет короткий срок жизни (например, 15 минут).

4) Refresh Token:

5) Используется для получения нового Access Token.

6) Имеет длительный срок жизни (например, 7 дней).

7) Хранится на клиенте в безопасном месте (например, в HTTP-only cookie).

8) Обновление Access Token:

9) Когда Access Token истекает, клиент отправляет Refresh Token на сервер.

10) Сервер проверяет Refresh Token и, если он валиден, выдает новый Access Token.
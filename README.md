# Backend-projekt Java – Hotellbokningssystem

Detta projekt är ett REST API byggt med Java och Spring Boot.
API:et hanterar hotellrum och bokningar. Projektet använder in-memory storage, vilket betyder att informationen sparas i listor i programmet och försvinner när servern startas om.

Projektet innehåller även en enkel webbsida som kan användas för att testa API:et.

## Tekniker

* Java 17
* Spring Boot
* Spring Web
* Spring Security
* Bean Validation
* Maven
* Basic Authentication
* JWT
* In-memory storage

## Funktioner

API:et har stöd för att:

* autentisera användare med Basic Authentication
* hantera roller med USER och ADMIN
* visa tillgängliga rum
* skapa en bokning
* visa alla bokningar som admin
* radera bokningar som admin
* validera input med Bean Validation
* räkna ut totalpris i service-lagret
* kontrollera att antal gäster inte överskrider rummets kapacitet
* visa tydliga felmeddelanden i JSON-format
* använda en enkel webbsida för att testa API:et
* logga in via JWT som extra funktion

## Användare, roller och autentisering

Projektet använder Spring Security med rollbaserad åtkomstkontroll, RBAC.

För G-kravet kan API:et testas med Basic Authentication i Postman.
Det betyder att användarnamn och lösenord skickas med i requesten via Postmans flik:

```text
Authorization → Basic Auth
```

Projektet har även JWT-login som extra funktion, men vid redovisning av G-delen används Basic Authentication.

| Användare | Lösenord | Roll  |
| --------- | -------- | ----- |
| user      | 1234     | USER  |
| admin     | admin123 | ADMIN |

## Behörigheter

| Endpoint             | Metod  | USER | ADMIN |
| -------------------- | ------ | ---- | ----- |
| `/login`             | POST   | Ja   | Ja    |
| `/api/rooms`         | GET    | Ja   | Ja    |
| `/api/bookings`      | POST   | Ja   | Ja    |
| `/api/bookings`      | GET    | Nej  | Ja    |
| `/api/bookings/{id}` | DELETE | Nej  | Ja    |

## Rumstyper

Projektet innehåller tre rumstyper:

| Rumstyp   | Antal rum | Max gäster | Pris per natt |
| --------- | --------: | ---------: | ------------: |
| Enkelrum  |        10 |          1 |        500 kr |
| Dubbelrum |         7 |          2 |       1000 kr |
| Svit      |         3 |          3 |       2000 kr |

## Starta projektet

Projektet kan startas direkt i IntelliJ genom att köra huvudklassen:

```text
BackendProjektJavaApplication
```

När projektet är igång körs API:et på:

```text
http://localhost:8080
```

## Webbsida

Projektet innehåller en enkel webbsida som kan användas för att testa API:et.

När projektet är igång kan sidan öppnas på:

```text
http://localhost:8080
```

På webbsidan kan man:

* visa tillgängliga rum
* skapa en bokning
* logga in som admin och visa alla bokningar
* radera bokningar som admin
* logga in via JWT som extra funktion

## Testa med Basic Authentication i Postman

För att testa G-delen används Basic Authentication i Postman.

### USER

Username:

```text
user
```

Password:

```text
1234
```

USER får:

* se rum
* skapa bokningar

USER får inte:

* se alla bokningar
* radera bokningar

### ADMIN

Username:

```text
admin
```

Password:

```text
admin123
```

ADMIN får:

* se rum
* skapa bokningar
* se alla bokningar
* radera bokningar

## Endpoints

### Visa alla rum

```http
GET /api/rooms
```

Exempel:

```text
http://localhost:8080/api/rooms
```

Denna endpoint kan användas av både USER och ADMIN.
Vid test i Postman används Basic Auth.

### Skapa bokning

```http
POST /api/bookings
```

Denna endpoint kan användas av både USER och ADMIN.
Vid test i Postman används Basic Auth.

Exempel på JSON-body:

```json
{
  "guestName": "Sara Ahmed",
  "roomType": "Dubbelrum",
  "numberOfGuests": 2,
  "nights": 2
}
```

Exempel på svar:

```json
{
  "id": 1,
  "guestName": "Sara Ahmed",
  "roomType": "Dubbelrum",
  "numberOfGuests": 2,
  "nights": 2,
  "totalPrice": 2000
}
```

### Visa alla bokningar

```http
GET /api/bookings
```

Denna endpoint kan endast användas av ADMIN.
Vid test i Postman används Basic Auth med admin-kontot.

### Radera bokning

```http
DELETE /api/bookings/{id}
```

Exempel:

```http
DELETE /api/bookings/1
```

Denna endpoint kan endast användas av ADMIN.
Vid test i Postman används Basic Auth med admin-kontot.

Exempel på svar:

```text
Bokningen har raderats.
```

## Bean Validation

Projektet använder Bean Validation för att kontrollera felaktig input innan en bokning skapas.

I klassen `Booking` används annoteringar som:

* `@NotBlank`
* `@Pattern`
* `@Min`
* `@Max`

Valideringen kontrollerar bland annat att:

* namn inte är tomt
* rumstyp är `Enkelrum`, `Dubbelrum` eller `Svit`
* antal gäster är minst 1
* antal gäster inte är mer än 3
* antal nätter är minst 1

## Affärslogik

Affärslogiken finns i service-lagret.

När en bokning skapas kontrollerar `BookingService` att:

* rumstypen finns
* antal gäster inte överskrider rummets kapacitet
* det finns lediga rum
* totalpriset räknas ut korrekt

Priser:

| Rumstyp   | Pris per natt |
| --------- | ------------: |
| Enkelrum  |        500 kr |
| Dubbelrum |       1000 kr |
| Svit      |       2000 kr |

Exempel:

Om en användare bokar Dubbelrum i 2 nätter blir totalpriset:

```text
1000 * 2 = 2000 kr
```

## JWT-login som extra funktion

Projektet har även stöd för JWT-login.

För att logga in med JWT skickas en POST-request till:

```http
POST /login
```

Exempel på JSON-body för vanlig användare:

```json
{
  "username": "user",
  "password": "1234"
}
```

Exempel på JSON-body för admin:

```json
{
  "username": "admin",
  "password": "admin123"
}
```

Exempel på svar:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

Token kan sedan skickas i Authorization-headern:

```text
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

JWT är en extra funktion. För redovisning av G-delen används Basic Authentication.

## Felhantering

Projektet använder egna exceptions och en global exception handler. Fel returneras i JSON-format.

Exempel: om en användare försöker boka Enkelrum för 3 gäster returneras ett fel.

```json
{
  "timestamp": "2026-05-16T12:30:00",
  "status": 400,
  "message": "Enkelrum tillåter max 1 gäster."
}
```

Exempel på fel som hanteras:

* rumstypen finns inte
* för många gäster för vald rumstyp
* rummet är fullbokat
* bokningen hittades inte
* felaktig input från användaren

## Projektstruktur

Projektet är uppdelat i flera paket:

```text
controller
dto
exception
model
security
service
```

Kort förklaring:

* `controller` innehåller endpoints, till exempel `/login`, `/api/rooms` och `/api/bookings`
* `dto` innehåller klasser för login-request och login-response
* `exception` innehåller egna exceptions och global felhantering
* `model` innehåller modellerna `Booking` och `RoomInfo`
* `security` innehåller säkerhetskonfiguration, Basic Authentication och JWT-logik
* `service` innehåller affärslogik för rum och bokningar

## Kommentar

Projektet använder in-memory storage, vilket gör det enkelt att testa utan databas. Det betyder att alla bokningar försvinner när servern startas om.

Detta är ett medvetet val för att hålla projektet fokuserat på REST API, validering, affärslogik, roller, säkerhet och felhantering.

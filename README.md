# Backend-projekt Java – Hotellbokningssystem

Detta projekt är ett REST API byggt med Java och Spring Boot.  
API:et hanterar hotellrum och bokningar. Projektet använder in-memory storage, vilket betyder att informationen sparas i listor i programmet och försvinner när servern startas om.

Projektet innehåller även en enkel webbsida som kan användas för att testa API:et.

## Tekniker

- Java 17
- Spring Boot
- Spring Web
- Spring Security
- Maven
- JWT
- In-memory storage

## Funktioner

API:et har stöd för att:

- logga in via JWT och få en token
- använda token för skyddade endpoints
- visa tillgängliga rum
- skapa en bokning
- visa alla bokningar som admin
- radera bokningar som admin
- hantera roller med USER och ADMIN
- visa tydliga felmeddelanden i JSON-format
- använda en enkel webbsida för att testa API:et

## Användare, roller och JWT

Projektet använder JWT för autentisering. Användaren loggar först in via `/login` och får tillbaka en token. Token skickas sedan med i varje skyddad request som en Bearer token.

Exempel på header:

```text
Authorization: Bearer DIN_TOKEN_HÄR
```

| Användare | Lösenord | Roll |
|---|---|---|
| user | 1234 | USER |
| admin | admin123 | ADMIN |

## Behörigheter

| Endpoint | Metod | USER | ADMIN |
|---|---|---|---|
| `/login` | POST | Ja | Ja |
| `/api/rooms` | GET | Ja | Ja |
| `/api/bookings` | POST | Ja | Ja |
| `/api/bookings` | GET | Nej | Ja |
| `/api/bookings/{id}` | DELETE | Nej | Ja |

## Rumstyper

Projektet innehåller tre rumstyper:

| Rumstyp | Antal rum | Max gäster | Pris per natt |
|---|---:|---:|---:|
| Enkelrum | 10 | 1 | 500 kr |
| Dubbelrum | 7 | 2 | 1000 kr |
| Svit | 3 | 3 | 2000 kr |

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

- logga in och få en JWT-token
- visa tillgängliga rum
- skapa en bokning
- logga in som admin och visa alla bokningar
- radera bokningar som admin

## Login med JWT

För att logga in skickas en POST-request till:

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

Token används sedan i Authorization-headern:

```text
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

## Endpoints

### Visa alla rum

```http
GET /api/rooms
```

Exempel:

```text
http://localhost:8080/api/rooms
```

Denna endpoint kan användas av både USER och ADMIN, men requesten måste innehålla en giltig JWT-token.

### Skapa bokning

```http
POST /api/bookings
```

Denna endpoint kan användas av både USER och ADMIN, men requesten måste innehålla en giltig JWT-token.

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

Denna endpoint kan endast användas av ADMIN och kräver en giltig JWT-token med admin-roll.

### Radera bokning

```http
DELETE /api/bookings/{id}
```

Exempel:

```http
DELETE /api/bookings/1
```

Denna endpoint kan endast användas av ADMIN och kräver en giltig JWT-token med admin-roll.

Exempel på svar:

```text
Bokningen har raderats.
```

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

- rumstypen finns inte
- för många gäster för vald rumstyp
- rummet är fullbokat
- bokningen hittades inte

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

- `controller` innehåller endpoints, till exempel `/login`, `/api/rooms` och `/api/bookings`
- `dto` innehåller klasser för login-request och login-response
- `exception` innehåller egna exceptions och global felhantering
- `model` innehåller modellerna `Booking` och `RoomInfo`
- `security` innehåller JWT-logik och säkerhetskonfiguration
- `service` innehåller affärslogik för rum och bokningar

## Kommentar

Projektet använder in-memory storage, vilket gör det enkelt att testa utan databas. Det betyder att alla bokningar försvinner när servern startas om. Detta är ett medvetet val för att hålla projektet fokuserat på REST API, JWT-autentisering, roller, säkerhet och felhantering.
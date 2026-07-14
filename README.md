# Keyloop Coding Challenge - Sales Lead Management Tool

Backend implementation for the Keyloop technical assessment, scenario C.

## Scope

- Lead list
- Lead detail
- Activity timeline
- Add activity
- Search leads
- Filter leads by status
- PostgreSQL persistence
- Unit tests

## Tech Stack

- Java 17
- Spring Boot 3.4.7
- Spring Web
- Spring Data JPA
- Bean Validation
- PostgreSQL
- MapStruct
- Lombok
- SpringDoc OpenAPI
- JUnit 5 / Mockito

## Run Locally

1. Start PostgreSQL and create a database named `keyloop_db`.
2. Update credentials in `backend/src/main/resources/application.properties` if needed.
3. Run the backend:

```powershell
cd backend
./mvnw.cmd spring-boot:run
```

## Test

```powershell
cd backend
./mvnw.cmd test
```

## Main Endpoints

- `GET /api/leads`
- `POST /api/leads`
- `GET /api/leads/{id}`
- `PUT /api/leads/{id}`
- `DELETE /api/leads/{id}`
- `GET /api/leads/search?keyword=...`
- `GET /api/leads/status?status=NEW`
- `POST /api/leads/{leadId}/activities`
- `GET /api/leads/{leadId}/activities`

## Notes

- Lead and activity timestamps are created automatically.
- Validation and global exception handling are enabled on the server side.
- The design document is stored in `TL.docx`.

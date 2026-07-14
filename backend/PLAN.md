# Edit plan (MapStruct bean missing)

## Information Gathered

- Crash: Spring cannot autowire `iuh.fit.backend.mapper.ActivityMapper` into `ActivityServiceImpl`.
- `ActivityMapper` exists and is annotated with `@Mapper(componentModel = "spring")`.
- `LeadMapper` is similar and likely works, but startup fails before it can matter.
- `pom.xml` contains `org.mapstruct:mapstruct` but **does not configure `mapstruct-processor`** in annotation processors.

## Plan (file-level)

1. Edit `backend/pom.xml`
   - Add `org.mapstruct:mapstruct-processor:1.6.3` to `maven-compiler-plugin` → `annotationProcessorPaths` (both main and test, or at least main).
2. Rebuild backend with Maven Wrapper.
   - Use `./mvnw -DskipTests clean compile` (no `&&` to avoid terminal separator issue).
3. Start backend again.
   - Confirm `APPLICATION FAILED TO START` is gone.

## Dependent Files to edit

- `backend/pom.xml`

## Followup steps

- Run: `cd backend` then `.
mvnw -DskipTests clean compile`
- Then run the Spring Boot app.

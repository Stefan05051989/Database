# Opdracht: Teacher Entiteit Toevoegen

## Doel
Je gaat een nieuwe entiteit `Teacher` toevoegen aan de bestaande CRUD applicatie. Deze teacher heeft een één-op-meer relatie met courses: een teacher kan meerdere courses geven, maar een course heeft maar één teacher.

## Huidige Situatie
De applicatie heeft momenteel:
- `Student` entiteit (met naam en leeftijd)
- `Course` entiteit (met naam en maximum aantal studenten)
- Een many-to-many relatie tussen Student en Course

## Stap 1: Analyseer de Bestaande Code
Bekijk eerst de huidige structuur:
- Welke packages zijn er?
- Hoe zijn de bestaande entiteiten opgebouwd?
- Welke annotaties worden gebruikt voor relaties?
- Hoe zijn de DTOs gestructureerd?

## Stap 2: Maak de Teacher Entiteit
Maak een nieuwe `Teacher` klasse aan in de `models` package met:
- Een unieke ID (auto-generated)
- Een naam
- Een email adres
- Een relatie naar courses (one-to-many)

**Vraag:** Welke JPA annotatie gebruik je voor een one-to-many relatie?

## Stap 3: Update de Course Entiteit
Pas de `Course` klasse aan om de relatie met Teacher te ondersteunen:
- Voeg een relatie naar Teacher toe
- Denk na over de cascade opties

**Vraag:** Welke cascade opties zijn logisch voor deze relatie?

## Stap 4: Maak Teacher DTOs
Maak DTOs aan in een nieuwe `teacher` package:
- `TeacherCreateDTO` - voor het aanmaken van een teacher
- `TeacherDTO` - voor het ophalen van teacher informatie
- `TeacherUpdateDTO` - voor het bijwerken van teacher informatie
- `TeacherSummaryDTO` - voor overzichten

**Vraag:** Welke velden zijn nodig in elke DTO?

## Stap 5: Maak Teacher Repository
Maak een `TeacherRepository` interface die `JpaRepository` extend.

**Vraag:** Welke methoden heeft JpaRepository standaard beschikbaar?

## Stap 6: Maak Teacher Service
Maak een `TeacherService` klasse met:
- CRUD operaties voor teachers
- Validatie logica

**Vraag:** Welke validatie is belangrijk voor teachers?

## Stap 6b: Maak TeacherCourse Service
Maak een `TeacherCourseService` klasse die de relatie tussen teachers en courses beheert:
- `assignTeacherToCourse(teacherId, courseId)` - wijs een teacher toe aan een course
- `removeTeacherFromCourse(courseId)` - verwijder teacher van een course
- `getTeacherForCourse(courseId)` - haal teacher op voor een specifieke course

**Vraag:** Waarom gebruik je `@Transactional` annotaties in deze service?

## Stap 7: Maak Teacher Controller
Maak een `TeacherController` met REST endpoints:
- POST `/teachers` - teacher aanmaken
- GET `/teachers` - alle teachers ophalen
- GET `/teachers/{id}` - specifieke teacher ophalen
- PUT `/teachers/{id}` - teacher bijwerken
- DELETE `/teachers/{id}` - teacher verwijderen

**Vraag:** Welke HTTP status codes gebruik je voor welke operaties?

## Stap 7b: Maak TeacherCourse Controller
Maak een `TeacherCourseController` met REST endpoints voor het beheren van teacher-course relaties:
- POST `/teacher-courses/assign/{teacherId}/to/{courseId}` - wijs teacher toe aan course
- DELETE `/teacher-courses/remove-from/{courseId}` - verwijder teacher van course
- GET `/teacher-courses/teacher-for/{courseId}` - haal teacher op voor course

**Vraag:** Waarom gebruik je verschillende HTTP methoden (POST, PUT, DELETE) voor deze operaties?

## Stap 8: Update Course DTOs
Pas de bestaande Course DTOs aan om teacher informatie te tonen:
- Welke velden moeten worden toegevoegd?
- Hoe toon je de teacher informatie?

## Stap 9: Test de Functionaliteit
Test de nieuwe functionaliteit:
- Maak een teacher aan
- Wijs een teacher toe aan een course
- Haal course informatie op inclusief teacher
- Test de TeacherCourseService endpoints

## Uitdaging
- Voeg validatie toe: een teacher moet een geldig email adres hebben
- Implementeer een endpoint om alle courses van een specifieke teacher op te halen
- Voeg business rules toe: een teacher kan maximaal 5 courses tegelijk geven
- Implementeer een endpoint om alle teachers te vinden die geen courses hebben

## Tips
- Begin met de basis entiteit en voeg geleidelijk functionaliteit toe
- Test elke stap voordat je verder gaat
- Kijk naar de bestaande code voor voorbeelden van implementatie

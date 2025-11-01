# spriong-ai-java

Brief, polished description:
A compact Spring Boot project for multimodal learning experiments. This repository contains the backend services, models, and controllers used to serve machine-learning related endpoints.

Quick links
- Project: Spring Boot
- Language: Java
- Location: C:\Users\sumee\IdeaProjects\spriong-ai-java

Getting started
1. Requirements
   - JDK 17+
   - Maven
2. Build
   - mvn clean package
3. Run
   - mvn spring-boot:run
   - or run the generated jar: java -jar target/*.jar

How to list all HTTP endpoints (recommended)
1. If you have Spring Actuator available:
   - Add dependency (pom.xml):
     - org.springframework.boot:spring-boot-starter-actuator
   - Enable mappings endpoint in application.properties:
     - management.endpoints.web.exposure.include=health,info,mappings
   - Start the app and run:
     - curl http://localhost:8080/actuator/mappings
   - The JSON contains all registered request mappings; copy relevant paths/methods into the "API Endpoints" section below.

2. If you use springdoc-openapi (recommended for readable API docs):
   - Add dependency:
     - org.springdoc:springdoc-openapi-ui
   - Start the app and visit:
     - http://localhost:8080/swagger-ui.html
     - http://localhost:8080/v3/api-docs  (raw JSON)
   - Use the UI or JSON to list endpoints and example requests/responses.

3. Quick one-liner to extract endpoint paths (unix-like shell):
   - curl -s http://localhost:8080/actuator/mappings | jq '.contexts.*.mappings.requestMappingConditions | .. | .patterns? // empty' | sort -u

API Endpoints
- Replace this block with the actual endpoints discovered from actuator or springdoc.
- Example format (fill with real endpoints):
  - GET  /api/health — returns service health
  - GET  /api/countries — list all countries
  - GET  /api/countries/{code} — get country details
  - POST /api/countries — create a new country
  - PUT  /api/countries/{code} — update a country
  - DELETE /api/countries/{code} — delete a country

How to paste discovered endpoints here
1. Run actuator or open swagger-ui as described above.
2. Copy each route and paste it into the list under "API Endpoints" in this file.
3. Optionally include sample requests and responses.

Example: embedding actuator output
- After running curl http://localhost:8080/actuator/mappings, look for entries like:
  {
    "requestMethods":["GET"],
    "patterns":["/api/countries"]
  }
- Translate that to:
  - GET /api/countries — list all countries

Contributing
- Fork, create a branch, open a pull request.
- Keep changes small and focused. Add tests for new endpoints.

License
- Add your license information here.

Notes
- If you want, I can generate a filled "API Endpoints" section for you — paste the output of:
  - curl http://localhost:8080/actuator/mappings
  or
  - curl http://localhost:8080/v3/api-docs
and I will convert it into a neat endpoint list to place into this README.

# This project is created using Spring AI for learning purposes. This uses open source and paid AI mode 
-   OLLAMA (DEEPSEEK)
-   OPENAI

Pass OPENAI_API_KEY key in environments

### Useful API

Course Guidance from
[Udemy Cource - From Java Dev to AI Engineer: Spring AI Fast Track](https://www.udemy.com/course/java-spring-ai/)
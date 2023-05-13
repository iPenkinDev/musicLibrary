# MusicLibrary


The program imitate simple API. Four entity has OneToMany and ManyToMany relations.

### Technology stack:
 -Java 17    
 -Spring Boot  
 -Gradle  
 -PostgreSQL    
 -Hibernate  
 -Swagger  
 -Lombok  
 -Docker  
 
 ### Deploy application
 **Prerequisites** 
 1. Java 17+
 2. Gradle
 3. Docker
 
 **Build**
 
 ```./gradlew clean build -x test```  
 ```docker build -t music-library-app .```  
 ```docker-compose up --build```  
 
 **In browser** 
 
 ```http://localhost:8080/swagger-ui/index.html#/```

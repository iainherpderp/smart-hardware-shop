# Spring Boot starter for Java 11
This is a starter including:
* Spring Boot Web starter
* Spring Boot Data JPA starter
    * Using MySQL
* Spring Boot Devtools and Annotation Processor
* A sample REST controller

## MySQL in Docker
`application.yml` already contains connection information to this DB.
```shell script
docker-compose up -d
```

Good luck!

# Running Instructions

- Clone the repository to your local machine. 
- Start the database by running `docker-compose up -d`
- Once the database has started, run `mvn install spring-boot:run`
- The application should be available at localhost:8080


# ToDo
- Add unit tests
- Add openapi documentation

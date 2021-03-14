# DemoShop

## Build docker image and run application inside docker

* run `./gradlew jibDockerBuild`
* run `cd local-env`
* run  `docker-compose up -d`
* navigate to `localhost:8080/swagger-ui/index.html` to check swagger
* navigate to `localhost:8080/docs/documentation.html` to check restdoc documentation 
* to stop run `docker-compose down`


## Start local db only

* run `cd local-env` from project root directory
* run `docker-compose up -d postgres`
* connect to the database `jdbc:postgresql://localhost:5432/demo_shop` using password `postgres` and user `postgres`
* to stop db run `docker-compose down`

mvn spring-boot:run

spring.datasource.url=jdbc:mysql://172.17.0.3:3306/aula-micro
spring.datasource.username=dbusermicro
spring.datasource.password=aulamicro2023

mvn clean package
mvn -Dmaven.test.skip clean  package
docker build -t alef123vinicius:micro-projeto-backend .

docker run -d -p 8080:8080 alef123vinicius:micro-projeto-backend .


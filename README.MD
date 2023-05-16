mvn spring-boot:run

spring.datasource.url=jdbc:mysql://172.17.0.3:3306/aula-micro
spring.datasource.username=dbusermicro
spring.datasource.password=aulamicro2023

mvn clean package
mvn -Dmaven.test.skip clean  package
docker build -t micro-projeto-backend .

docker run -d -p 8080:8080 micro-projeto-backend .

export MYUSER=dbusermicro
export MYPASSWORD=aulamicro2023
export HOSTNAME=172.17.0.3


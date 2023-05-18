# Micro Projeto Backend

## iniciando o projeto

https://start.spring.io/

Opções >
Project Maven 
Language Java
Spring Boot 2.7.11

Group
com.microprojeto

Artifact
microprojetobackend

Name
microprojetobackend

Description
Demo project for Spring Boot

Package name
com.microprojeto.microprojetobackend

Packaging
Jar

Java 
8

Dependencias

Spring Data Jpa
Spring Web
MySql Driver
Spring REST Docs

generate

Após a geração 

Adicionar duas bibliotecas no pom

```
		<!-- https://mvnrepository.com/artifact/junit/junit -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.13.2</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>test</scope>
		</dependency>
```

mvn spring-boot:run


application.properties

```
spring.datasource.url=jdbc:mysql://172.17.0.3:3306/aula-micro
spring.datasource.username=dbusermicro
spring.datasource.password=aulamicro2023
```

test.properties

```
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
```

mvn clean package
mvn -Dmaven.test.skip clean  package
docker build -t micro-projeto-backend .

docker run -d -p 8080:8080 micro-projeto-backend .

export MYUSER=dbusermicro
export MYPASSWORD=aulamicro2023
export HOSTNAME=172.17.0.3


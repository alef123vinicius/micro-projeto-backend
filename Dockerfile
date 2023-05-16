FROM openjdk:8
ENV MYUSER=dbusermicro
ENV MYPASSWORD=aulamicro2023
ENV HOSTNAME=172.17.0.2
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/micro-projeto-backend-1.0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
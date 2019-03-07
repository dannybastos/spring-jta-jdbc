FROM dannybastos/openjdk8

MAINTAINER Danny Bastos <danny.bastos.br@gmail.com>

WORKDIR /app

COPY . /app

RUN ./gradlew clean bootJar

RUN mv /app/build/libs/spring-jta-jdbc*.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]

FROM openjdk:21

LABEL author=anajpva

RUN groupadd -r app && useradd -r -g app app

USER app

COPY target/pprice-boot-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
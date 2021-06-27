FROM maven:3.8.1-jdk-11 AS MAVEN_BUILD
COPY  ./ /opt/app
RUN cd /opt/app && mvn clean package -Dmaven.test.skip=true

FROM openjdk:11-jdk-slim
COPY --from=MAVEN_BUILD /opt/app/target/books-0.0.1-SNAPSHOT.jar /opt/app/books-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/opt/app/books-0.0.1-SNAPSHOT.jar"]
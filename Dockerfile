FROM openjdk:8-jre

WORKDIR /app

COPY target/aws-wrapper-*.jar /app/application.jar

CMD ["java", "-jar", "application.jar"]

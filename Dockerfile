FROM maven:alpine

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

RUN mvn package -Dmaven.test.skip=true

EXPOSE 8080

CMD ["java", "-jar", "target/task-management-system-0.0.1-SNAPSHOT.jar"]
FROM eclipse-temurin:26-jdk

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew build -x test

RUN rm -f build/libs/*-plain.jar

RUN mv build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
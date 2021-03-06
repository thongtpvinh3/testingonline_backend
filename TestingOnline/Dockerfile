#### Stage 1: Build the application
FROM openjdk:8-jdk-alpine as build

ENV TZ=Asia/Ho_Chi_Minh

# Set the current working directory inside the image
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Build all the dependencies in preparation to go offline. 
# This is a separate step so the dependencies will be cached unless 
# the pom.xml file has changed.
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src

RUN  chmod +x mvnw
# Package the application
RUN ./mvnw package -DskipTests
# RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#### Stage 2: A minimal docker image with command to run the app 
FROM openjdk:8-jre-alpine

WORKDIR /app

ARG DEPENDENCY=/app/target/testingonline-1.0

# Copy project dependencies from the build stage
# COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
# COPY --from=build ${DEPENDENCY}/WEB-INF/classes /app
# COPY --from=build ${DEPENDENCY}/WEB-INF/lib /app/lib

COPY --from=build /app/target/testingonline-1.0.war /app


ENTRYPOINT ["java","-jar","/app/testingonline-1.0.war"]

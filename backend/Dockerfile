FROM openjdk:21-jdk-slim

# Instala o Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copia o código fonte para o container
COPY . .

RUN mvn clean package

# Copia o arquivo JAR gerado para a pasta /app
RUN cp target/user-registration-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

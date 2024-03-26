# Use a minimal Alpine-based image
FROM alpine:latest

# Install apk package manager
RUN apk update && apk add --no-cache apk-tools

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/rest-example01-0.0.1-SNAPSHOT.jar /app/

# Install MySQL client for Liquibase (using apk for Alpine-based images)
RUN apk update && apk add mysql-client

# Install Liquibase
RUN wget -qO /tmp/liquibase.tar.gz https://github.com/liquibase/liquibase/releases/download/v4.8.0/liquibase-4.8.0.tar.gz && \
    tar -xzf /tmp/liquibase.tar.gz -C /usr/local/bin/ && \
    chmod +x /usr/local/bin/liquibase && \
    rm /tmp/liquibase.tar.gz

# Copy Liquibase configuration and changelog files into the container
COPY src/main/resources/liquibase/mysql/liquibase.properties /liquibase/
COPY src/main/resources/liquibase/mysql/db-changelog-master.xml /liquibase/

# Run Liquibase migrations during container startup
CMD ["java", "-jar", "rest-example01.jar"]
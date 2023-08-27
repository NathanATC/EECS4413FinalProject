# Use maven to build the Java app
FROM maven:3.6-jdk-11 as builder
WORKDIR /app
COPY . .
RUN mvn clean install

# Use Tomcat to deploy the war
FROM tomcat:9.0-jdk11

# Copy wait-for-it.sh and give it the necessary permissions
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Copy the built artifact from builder
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/

# Expose port 8080
EXPOSE 8080

# Run wait-for-it.sh before starting Tomcat
ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--"]
CMD ["catalina.sh", "run"]
# Builder stage
FROM openjdk:21-jdk-slim as builder
WORKDIR application
COPY ./target/application-3.1.2.jar application-3.1.2.jar
RUN java -Djarmode=layertools -jar application-3.1.2.jar extract
# Optimize layer copying
RUN rm application.jar
CMD ./target/application-3.1.2.jar application-3.1.2.jar

# Copy extracted layers to the final image
FROM openjdk:21-jdk-slim
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
EXPOSE 8080
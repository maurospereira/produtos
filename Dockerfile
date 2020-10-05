FROM openjdk
WORKDIR /app
COPY target/produtos-0.0.1-SNAPSHOT.jar /app/produtos.jar
ENTRYPOINT ["java","-jar","produtos.jar"]


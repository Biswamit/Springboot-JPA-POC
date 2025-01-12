export JAVA_HOME=/usr/lib/jvm/zulu-17-amd64
rm -rf springboot-jpa-rest-service-0.0.1-SNAPSHOT.jar
cd ../../../
./mvnw clean compile package -DskipTests
cp target/springboot-jpa-rest-service-0.0.1-SNAPSHOT.jar src/main/docker/
cd src/main/docker/
docker compose down
docker rmi springboot-jpa-rest-service:latest
docker compose up
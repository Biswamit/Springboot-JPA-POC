FROM azul/zulu-openjdk-alpine:17
MAINTAINER biswamit.co.in
RUN apk update && apk upgrade
RUN apk add wget
#RUN apt-get update && apt-get install -y wget
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 9081
CMD ["java", "-jar", "application.jar"]

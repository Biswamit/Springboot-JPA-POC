#!/bin/bash

set -e
setjava=true
printenv=true
mode=
dimg=
dimgrmv=
svcname=

usage() {
  echo "Usage : "
  echo "docker-create.sh -m <run mode> -h <help>"
  echo "-m <Run mode>: {pr|gr||eu|db|pgex|gw|jpa|all}"
  echo "-h : Help"
}

dockerCheckAndRemove() {
  dimgrmv=$1
  if test ! -z "$(docker images -q $dimgrmv)"; then
    echo "Docker image $dimgrmv exist"
    docker rmi $dimgrmv
  else
    echo "Docker image $dimgrmv doesnt exist"
  fi
}

dockerimgremove() {
  dimg=$1
  case $dimg in
    'pr')
      echo "Removing prometheus docker image"
      dockerCheckAndRemove prom/prometheus:latest
      ;;
    'gr')
      echo "Removing grafana docker image"
      dockerCheckAndRemove grafana/grafana:latest
      ;;
    'db')
      echo "Removing db docker image"
      dockerCheckAndRemove postgres:16.1-alpine3.18
      ;;
    'pgex')
      echo "Removing postgres exporter docker image"
      dockerCheckAndRemove quay.io/prometheuscommunity/postgres-exporter
      ;;
    'jpa')
      echo "Removing jpa docker image"
      dockerCheckAndRemove springboot-jpa-rest-service:latest
      ;;
    'jpaexdb')
      echo "Removing all except db docker image"
      echo "Removing prometheus docker image"
      dockerCheckAndRemove prom/prometheus:latest
      echo "Removing grafana docker image"
      dockerCheckAndRemove grafana/grafana:latest
      echo "Removing postgres exporter docker image"
      dockerCheckAndRemove quay.io/prometheuscommunity/postgres-exporter
      echo "Removing jpa docker image"
      dockerCheckAndRemove springboot-jpa-rest-service:latest
      ;;
    'all')
      echo "Removing prometheus docker image"
      dockerCheckAndRemove prom/prometheus:latest
      echo "Removing grafana docker image"
      dockerCheckAndRemove grafana/grafana:latest
      echo "Removing postgres exporter docker image"
      dockerCheckAndRemove quay.io/prometheuscommunity/postgres-exporter
      echo "Removing db docker image"
      dockerCheckAndRemove postgres:16.1-alpine3.18
      echo "Removing jpa docker image"
      dockerCheckAndRemove springboot-jpa-rest-service:latest
      ;;
    *)
      echo "Invalid options. See usage."
      usage
      ;;
  esac

}

dockerup() {
  svcname=$1
  case $svcname in
    'pr')
      echo "Running docker up for prometheus"
      docker compose -f docker-compose.yml up prometheus-jpa-rest --build -d
      ;;
    'gr')
      echo "Running docker up for grafana"
      docker compose -f docker-compose.yml up grafana-jpa-rest --build -d
      ;;
    'db')
      echo "Running db up for db"
      docker compose -f docker-compose.yml up postgresql.jpa.rest.service.db --build -d
      ;;
    'pgex')
      echo "Running db up for postgres exporter"
      docker compose -f docker-compose.yml up postgres.exporter.db --build -d
      ;;
    'jpa')
      echo "Running docker up for jpa-rest"
      docker compose -f docker-compose.yml up springboot-jpa-rest-service --build -d
      ;;
    'jpaexdb')
      echo "Running docker up for jpa except db"
      dockerupjpaexdb
      ;;
    'all')
      dockerupall
      ;;
    *)
      echo "Invalid options. See usage."
      usage
      ;;
  esac
}

dockerupall() {
  echo "Running docker up for prometheus"
  docker compose -f docker-compose.yml up prometheus-jpa-rest --build -d
  echo "Running docker up for grafana"
  docker compose -f docker-compose.yml up grafana-jpa-rest --build -d
  echo "Running db up for db"
  docker compose -f docker-compose.yml up postgresql.jpa.rest.service.db --build -d
  echo "Running db up for postgres exporter"
  docker compose -f docker-compose.yml up postgres.exporter.db --build -d
  echo "Running docker up for jpa-rest"
  docker compose -f docker-compose.yml up springboot-jpa-rest-service --build -d
}

dockerupjpaexdb() {
  echo "Running docker up for prometheus"
  docker compose -f docker-compose.yml up prometheus-jpa-rest --build -d
  echo "Running docker up for grafana"
  docker compose -f docker-compose.yml up grafana-jpa-rest --build -d
  echo "Running db up for postgres exporter"
  docker compose -f docker-compose.yml up postgres.exporter.db --build -d
  echo "Running docker up for jpa-rest"
  docker compose -f docker-compose.yml up springboot-jpa-rest-service --build -d
}

dockerdwnjpaexdb() {
  echo "Running docker down for prometheus"
  docker compose rm -s -v prometheus-jpa-rest
  echo "Running docker down for grafana"
  docker compose rm -s -v grafana-jpa-rest
  echo "Running db down for postgres exporter"
  docker compose rm -s -v postgres.exporter.db
  echo "Running docker down for jpa-rest"
  docker compose rm -s -v springboot-jpa-rest-service
}

dockerdown() {
  svcname=$1
  case $svcname in
    'pr')
      echo "Running docker down for prometheus"
      #docker run --name postgres-exporter -p 9187:9187 -d --network sb-eks-jpa-rest-svc-nw -e DATA_SOURCE_NAME="postgresql:/monitoring:Welcome1234@localhost:5432/springboot-jpa-rest-db?sslmode=require" quay.io/prometheuscommunity/postgres-exporter
      docker compose rm -s -v prometheus-jpa-rest
      ;;
    'gr')
      echo "Running docker down for grafana"
      docker compose rm -s -v grafana-jpa-rest
      ;;
    'db')
      echo "Running db down for db"
      docker compose rm -s -v postgresql.jpa.rest.service.db
      ;;
    'pgex')
      echo "Running db down for postgres exporter"
      docker compose rm -s -v postgres.exporter.db
      ;;
    'jpa')
      echo "Running docker down for jpa-rest"
      docker compose rm -s -v springboot-jpa-rest-service
      ;;
    'jpaexdb')
      echo "Running docker down for jpa except db"
      dockerdwnjpaexdb
      ;;
    'all')
      docker compose down
      ;;
    *)
      echo "Invalid options. See usage."
      usage
      ;;
  esac
}

jparestsvc() {
  rm -f springboot-jpa-rest-service-0.0.1-SNAPSHOT.jar
  cd ../springboot-jpa-rest-service
  rm -f src/main/docker/springboot-jpa-rest-service-0.0.1-SNAPSHOT.jar
  ./mvnw clean compile package -DskipTests
  cp target/springboot-jpa-rest-service-0.0.1-SNAPSHOT.jar src/main/docker/
  cp target/springboot-jpa-rest-service-0.0.1-SNAPSHOT.jar ../docker/
  cd ../docker
}

printAllEnvVar() {
  echo "###############Environment variable#################"
  printenv
  echo "###############Environment variable#################"
}

init() {
  if [ $printenv == "true" ]; then
      printAllEnvVar
  fi
}

runmode() {
  if [ $setjava == "true" ]; then
    echo "Setting Java env to 17"
    export JAVA_HOME=/usr/lib/jvm/zulu-17-amd64
  fi
  case $mode in
  'pr')
    echo "Building....."
    init
    dockerdown pr
    dockerimgremove pr
    dockerup pr
    ;;
  'gr')
    echo "Building....."
    init
    dockerdown gr
    dockerimgremove gr
    dockerup gr
    ;;
  'db')
    echo "Building....."
    init
    dockerdown db
    dockerimgremove db
    dockerup db
    ;;
  'pgex')
    echo "Building....."
    init
    dockerdown pgex
    dockerimgremove pgex
    dockerup pgex
    ;;
  'jpa')
    echo "Building....."
    init
    jparestsvc
    dockerdown jpa
    dockerimgremove jpa
    dockerup jpa
    ;;
  'jpaexdb')
    echo "Building....."
    init
    jparestsvc
    dockerdown jpaexdb
    dockerimgremove jpaexdb
    dockerup jpaexdb
    ;;
  'all')
    echo "Building....."
    init
    jparestsvc
    dockerdown all
    dockerimgremove all
    dockerup all
    ;;
  *)
    echo "Invalid run mode operation. See usage for supported run modes."
    usage
    exit 1
    ;;
  esac
}

while getopts "m:h" opt; do
  case $opt in
  m)
    mode=${OPTARG}
    echo "Run mode set to $mode"
    runmode
    ;;
  h)
    usage
    ;;
  *)
    echo "Invalid options. See usage."
    usage
    exit 1
    ;;
  esac
done
#!/bin/bash

set -e
mode=
container_name=
container_id=

fetch_tail_logs(){
  container_name=$1
  # Get the container ID based on the container name or image name
  container_id=$(docker ps | grep "$container_name" | awk '{print $1}')

  # Check if a container ID was found
  if [ -z "$container_id" ]; then
      echo "No container found matching '$container_name'"
      exit 1
  fi

  # Fetch logs from the container
  echo "Fetching logs from '$container_name' with container id '$container_id'"
  docker logs --tail -20 "$container_id"
}

runmode() {

  echo "Running mode in $mode"
  case $mode in
  'pr')
    echo "Fetching logs from pr....."
    fetch_tail_logs 'prom/prometheus:latest'
    ;;
  'gr')
    echo "Fetching logs from gr....."
    fetch_tail_logs 'grafana/grafana:latest'
    ;;
  'eu')
    echo "Fetching logs from eu....."
    fetch_tail_logs 'springboot-jpa-rest-eureka-service:latest'
    ;;
  'gw')
    echo "Fetching logs from gw....."
    fetch_tail_logs 'springboot-jpa-rest-apigw-service:latest'
    ;;
  'db')
    echo "Fetching logs from db....."
    fetch_tail_logs 'timescale/timescaledb-ha:pg16'
    ;;
  'pgbr')
    echo "Fetching logs from pgbr....."
    fetch_tail_logs 'pgbouncer/pgbouncer:latest'
    ;;
  'pgbrex')
    echo "Fetching logs from pgbrex....."
    fetch_tail_logs 'spreaker/prometheus-pgbouncer-exporter'
    ;;
  'pgex')
    echo "Fetching logs from pgex....."
    fetch_tail_logs 'quay.io/prometheuscommunity/postgres-exporter'
    ;;
  'al')
    echo "Fetching logs from al....."
    fetch_tail_logs 'springboot-jpa-rest-service:latest'
    ;;
  *)
    echo "Invalid operation. See usage for supported options."
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
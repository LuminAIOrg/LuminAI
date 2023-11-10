#!/usr/bin/env bash

set -e
cd LuminAi
rm -rf target
mvn -B clean package
mkdir -p target/deploy
cp target/quarkus-app/*-run.jar target/deploy/
docker build --tag backend --file ./src/main/docker/Dockerfile ./target/deploy
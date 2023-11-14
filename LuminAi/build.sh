#!/usr/bin/env bash

set -e

# Clean previous build artifacts
rm -rf target

# Build the Maven project
./mvnw -B clean package

# Create deployment directory
pwd
mkdir -p ./target/deploy

# Copy the JAR file to the deployment directory
cp ./target/quarkus-app/quarkus-run.jar ./target/deploy/

# Build Docker image
docker build --tag backend --file ./src/main/docker/Dockerfile.jvm .

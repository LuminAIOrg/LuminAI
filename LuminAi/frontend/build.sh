#!/bin/zsh

set -e

BASE_HREF=${BASE_HREF:-"/"}

rm -rf ./target
mkdir -p target/nginx

pushd www
    npm install
    npm run build
#   npm run build -- --env baseHref=$BASE_HREF
popd

cp ./docker/default.conf ./target/nginx/

docker build --tag frontend --file ./docker/Dockerfile ./target/
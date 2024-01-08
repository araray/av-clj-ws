#!/bin/bash

# Download/update dependencies
lein deps
# Build docker image
docker build --no-cache -t av-clj-ws:latest .


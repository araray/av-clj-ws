#!/bin/bash

#docker run -v /home/araray/.m2:/root/.m2 -v $(pwd):/app -p 3000:8080 av-clj-ws:latest
docker run -v $(pwd):/app -p 3000:8080 av-clj-ws:latest


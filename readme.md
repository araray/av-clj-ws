# AV-CLJ-WS: Clojure Web Service Playground with Docker

## Overview

AV-CLJ-WS is a project designed to experiment with web services using Clojure, a modern Lisp dialect for the JVM. This project includes a Docker setup, allowing you to run a Clojure web service within a Docker container and observe requests through an Nginx proxy. The setup is ideal for local development and testing, with potential for extension into Kubernetes environments.

### Features

- Clojure web service application setup.
- Docker and Docker Compose integration for easy containerization.
- Nginx proxy for monitoring and handling web service requests.
- Customizable through environment variables and configuration files.

## Prerequisites

- Docker and Docker Compose installed on your machine.
- Basic understanding of Clojure, Leiningen, and Docker.
- (Optional) Leiningen installed locally for managing Clojure projects.

## Project Structure

```
av-clj-ws/
├── build.sh                   # Script to build the Docker image
├── docker-compose.yaml        # Docker Compose configuration
├── Dockerfile                 # Dockerfile for the Clojure app
├── Dockerfile-clojure         # Symlink to Dockerfile
├── Dockerfile-nginx           # Dockerfile for the Nginx container
├── lein-docker/
│   └── profiles.clj           # Leiningen profile configuration
├── LICENSE                    # Project License
├── nginx.conf                 # Nginx configuration
├── project.clj                # Leiningen project file
├── proxy.conf                 # Nginx proxy configuration
├── resources/
│   └── logback.xml            # Logging configuration
├── run.sh                     # Script to run the Docker container
└── src/
    └── av_clj_ws/
        └── core.clj           # Main Clojure application source
```

## Setup Instructions

### Step 1: Clone the Repository

Clone this repository to your local machine.

### Step 2: Create Required Directories

Create two directories within the project's root:

- `repo`: This can be a symlink to your local Maven repository (usually `~/.m2/repository`).
- `target`: This is created by running `lein deps` in the project's root directory.

### Step 3: Building the Docker Image

Run the `build.sh` script to build the Docker image. This script will download dependencies and build the Docker image tagged as `av-clj-ws:latest`.

### Step 4: Running with Docker Compose

Use Docker Compose to start the services defined in `docker-compose.yaml`. This will start both the Clojure application and the Nginx proxy.

```shell
docker-compose up
```

### Step 5: Accessing the Web Service

The web service will be accessible at `http://localhost:3001`, and the Nginx proxy will be available at `http://localhost:3000`.

## Usage

The Clojure application provides a basic web service with multiple endpoints:

- `GET /`: Welcome message.
- `GET /app`: App route message.
- `GET /somethingelse`: Custom message for the `/somethingelse` path.
- `ANY /*`: Generic handler for all other requests.

## Customizing the Setup

- Modify `nginx.conf` and `proxy.conf` for custom Nginx settings.
- Update `project.clj` for additional Clojure dependencies or project settings.
- Adjust `resources/logback.xml` to change logging configurations.

## License

This project is licensed under the terms of the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).

## Contributing

Contributions are welcome. Please open issues and pull requests for any bugs, features, or improvements.

------

This README provides basic documentation for the AV-CLJ-WS project. Users are encouraged to modify and extend it as per their project requirements.
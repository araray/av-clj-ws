# Documentation for AV-CLJ-WS

## Introduction

AV-CLJ-WS is a Clojure-based web service designed to be run in a Docker environment. It's set up with Nginx as a reverse proxy for better insight into request handling. This document provides a detailed explanation of the project's components, their functionality, and usage instructions.

## Clojure Application

### Core Application (`src/av_clj_ws/core.clj`)

The core of the web service is defined in `core.clj`. This file sets up a simple web server using the Ring library and Compojure for routing.

#### Key Components:

- **Namespaces**: The application uses namespaces for organizing its components. Key namespaces include `ring.adapter.jetty` for the web server, `ring.util.response` for generating HTTP responses, and `compojure.core` for routing.
- **Routes**: The application defines several routes (`/`, `/app`, `/somethingelse`, etc.) using Compojure's `defroutes`.
- **Handlers**: Each route is associated with a handler function that generates a response. For example, `handle-root` responds to requests to the root URL.
- **JSON Responses**: For certain routes, JSON responses are constructed using the Cheshire library.
- **Middleware**: The application uses Ring middleware like `wrap-defaults` and `wrap-params` for common web application features.

#### Starting the Server:

The `-main` function starts a Jetty server on port 8080. This function is the entry point for the application.

### Logging Configuration (`resources/logback.xml`)

This file configures logging for the application using Logback. The current setup logs messages to the standard output (console) with a specified format.

## Docker Configuration

### Dockerfile

This file creates a Docker image for the Clojure application.

#### Key Steps:

1. **Base Image**: Uses `openjdk:11-jdk` as the base image.
2. **Installation**: Installs Clojure, Leiningen, and Babashka.
3. **Configuration**: Copies the `profiles.clj` file into the container for Leiningen configuration.
4. **Exposing Ports**: Exposes port 8080 for the Jetty server.
5. **Volume**: Sets `/app` as a volume to mount the local project folder.
6. **CMD**: Defines the command to start the Jetty server using Leiningen.

### Docker Compose (`docker-compose.yaml`)

Defines two services: `app` (the Clojure application) and `nginx` (the Nginx proxy).

#### `app` Service:

- Builds the image using `Dockerfile-clojure`.
- Mounts the current directory to `/app` in the container.
- Exposes port 3001, mapping it to the internal port 8080.

#### `nginx` Service:

- Builds the image using `Dockerfile-nginx`.
- Exposes port 3000, mapping it to the internal port 8080.
- Depends on the `app` service.

### Nginx Configuration (`nginx.conf`, `proxy.conf`)

These files configure Nginx as a reverse proxy to forward requests to the Clojure application.

#### Key Points:

- `nginx.conf`: Basic Nginx configuration, including logging and MIME types.
- `proxy.conf`: Configures Nginx to proxy requests to the `app` service.

## Usage Instructions

### Building and Running

1. **Build the Docker Image**: Run `./build.sh` to build the Docker image for the Clojure application.
2. **Start Services**: Use `docker-compose up` to start both the `app` and `nginx` services.
3. **Access the Web Service**: The application is accessible at `http://localhost:3001`, and the Nginx proxy at `http://localhost:3000`.

### Interacting with the Application

Send HTTP requests to the defined endpoints to interact with the web service. For example, a `GET` request to `http://localhost:3001/` will receive a welcome message.

## Conclusion

This solution provides a straightforward setup for running a Clojure-based web service with Docker. Its modular structure allows for easy modification and extension, making it suitable for both learning and developing more complex web applications.
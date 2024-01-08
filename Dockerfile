# Use a base image with Java 11
FROM openjdk:11-jdk

# Set the working directory in the container
WORKDIR /app

# Install Clojure and tools
RUN apt-get update && \
    apt-get install -y curl && \
    curl -L -O https://github.com/clojure/brew-install/releases/latest/download/linux-install.sh && \
    chmod +x linux-install.sh && \
    ./linux-install.sh && \
    rm linux-install.sh

# Create a directory for Leiningen configuration
RUN mkdir -p /root/.lein

# Install Leiningen
ENV LEIN_ROOT=1
RUN curl -s https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein > /usr/local/bin/lein && \
    chmod a+x /usr/local/bin/lein && \
    lein
RUN lein -v

# Copy only project.clj initially to cache dependencies
#COPY project.clj /app/
#RUN lein deps

# Copy the profiles.clj file into the container
COPY ./lein-docker/profiles.clj /root/.lein/

# Install Babashka
RUN curl -s https://raw.githubusercontent.com/babashka/babashka/master/install > /tmp/install-babashka.sh && \
    chmod +x /tmp/install-babashka.sh && \
    /tmp/install-babashka.sh

# Expose the port Jetty will run on
EXPOSE 8080

# Ensure JAVA is running headlessly
ENV JAVA_TOOL_OPTIONS="-Djava.awt.headless=true"

# Mount the local folder
VOLUME ["/app"]

# Command to run Jetty
CMD ["lein", "ring", "server-headless"]


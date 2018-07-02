FROM openjdk:8-jdk-slim

# Install something in order to downloading gradle
RUN apt-get update && \
    apt-get install -y --no-install-recommends unzip wget

# Install gradle
RUN mkdir -p /opt/gradle && \
    wget https://services.gradle.org/distributions/gradle-4.8.1-bin.zip && \
    unzip -d /opt/gradle gradle-4.8.1-bin.zip && \
    rm gradle-4.8.1-bin.zip && \
    ls /opt/gradle/gradle-4.8.1 && \
    ln -s /opt/gradle/gradle-4.8.1/bin/gradle /usr/bin/gradle

# Set /app as workdir
WORKDIR /app

# clone project directory to /app
ADD . /app

# build gradle env
RUN gradle build --no-daemon --console plain

# keep container running
CMD tail -f /dev/null

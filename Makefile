# For Local

.PHONY: install_java
install_java:
	apt-get install openjdk-8-jre
	apt-get install default-jdk

.PHONY: install_gradle
install_gradle:
	mkdir -p /opt/gradle; \
	wget https://services.gradle.org/distributions/gradle-4.8.1-bin.zip; \
	unzip -d /opt/gradle gradle-4.8.1-bin.zip; \
	rm gradle-4.8.1-bin.zip; \
	ls /opt/gradle/gradle-4.8.1; \
	ln -s /opt/gradle/gradle-4.8.1/bin/gradle /usr/bin/gradle;

.PHONY: compile
compile:
	gradle build

.PHONY: run
run:
	gradle run

# For Docker

.PHONY: build_docker
build_docker:
	docker build -t java-project .

.PHONY: run_docker
run_docker:
	docker run -d --name java-project java-project

.PHONY: attach_docker
attach_docker:
	docker exec -i -t java-project bash

.PHONY: stop_docker
stop_docker:
	docker container stop java-project

.PHONY: rm_docker
rm_docker:
	docker container rm java-project

.PHONY: compile_app
compile_app:
	docker exec -i -t java-project gradle build

.PHONY: run_app
run_app:
	docker exec -i -t java-project gradle run

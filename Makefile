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
	ls /opt/gradle/gradle-4.8.1;
	# export PATH=$PATH:/opt/gradle/gradle-4.8.1/bin

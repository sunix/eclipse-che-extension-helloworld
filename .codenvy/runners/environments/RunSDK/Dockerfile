FROM codenvy/jdk7

# Install Maven
RUN sudo apt-get update > /dev/null && \
    sudo apt-get install -y wget  > /dev/null &&  \
    wget --no-verbose -O /tmp/apache-maven-3.2.2.tar.gz http://archive.apache.org/dist/maven/maven-3/3.2.2/binaries/apache-maven-3.2.2-bin.tar.gz  > /dev/null && \
    echo "87e5cc81bc4ab9b83986b3e77e6b3095 /tmp/apache-maven-3.2.2.tar.gz" | md5sum -c > /dev/null && \
    sudo tar xzf /tmp/apache-maven-3.2.2.tar.gz -C /opt/ > /dev/null && \
    sudo ln -s /opt/apache-maven-3.2.2 /opt/maven > /dev/null && \
    sudo ln -s /opt/maven/bin/mvn /usr/local/bin > /dev/null && \
    sudo rm -f /tmp/apache-maven-3.2.2.tar.gz

ENV MAVEN_HOME /opt/maven

# Download SDK and dependencies
RUN mvn org.apache.maven.plugins:maven-dependency-plugin:2.1:get -DrepoUrl="https://maven.codenvycorp.com/content/groups/public/" -Dartifact=com.codenvy.sdk:assembly-sdk:3.2.0:zip > /dev/null && \
     mkdir /tmp/sdk/ && \
     mvn dependency:get -DrepoUrl="https://maven.codenvycorp.com/content/groups/public/" -Dartifact=com.codenvy.sdk:codenvy-sdk-parent:3.2.0:pom -Ddest=/tmp/sdk/pom.xml > /dev/null && \
     cd /tmp/sdk && \
     mvn clean install -N > /dev/null

# Unzip Eclipse CHE SDK
RUN mkdir /home/user/tomcat-ide
WORKDIR /home/user/tomcat-ide
RUN unzip /home/user/.m2/repository/com/codenvy/sdk/assembly-sdk/3.2.0/assembly-sdk-3.2.0.zip > /dev/null

ENV CODENVY_APP_PORT_8080_HTTP 8080
EXPOSE 8080

# Copy, install extension and start SDK
ADD $app$ /home/user/tomcat-ide/ext/testext.jar
RUN ./extInstall.sh > /dev/null
CMD ./bin/codenvy.sh run

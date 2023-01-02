From tomcat:8.5.47-jdk8-openjdk
RUN rm -rf /usr/local/tomcat/webapps/*
EXPOSE  8080/tcp
EXPOSE 8080/udp
COPY ./target/Webservice-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh","run"]
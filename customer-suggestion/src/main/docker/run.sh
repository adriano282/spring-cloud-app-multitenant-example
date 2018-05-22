#!/bin/sh

echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z configserver $CONFIGSERVER_PORT `; do sleep 10; done
echo ">>>>>>>>>>>> Configuration Server has started"

echo "********************************************************"
echo "Waiting for the Eureka Server to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z eurekaserver $EUREKASERVER_PORT `; do sleep 10; done
echo ">>>>>>>>>>>> Eureka Server has started"

echo "********************************************************"
echo "Waiting for the Zuul Server to start on port $ZUULSERVER_PORT"
echo "********************************************************"
while ! `nc -z zuulserver $ZUULSERVER_PORT `; do sleep 10; done
echo ">>>>>>>>>>>> Zuul Server has started"

echo "********************************************************"
echo "Starting Customer Suggestion Service with Configuration Service :  $CONFIGSERVER_URI";
echo "And Registering with Eureka Server : $EUREKASERVER_URI"
echo "Server Port :  $SERVER_PORT"
echo "Profile : $PROFILE"
echo "********************************************************"

java -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI             \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                          \
     -Dspring.profiles.active=$PROFILE                                    \
-jar /usr/local/customersuggestion/@project.build.finalName@.jar

#!/bin/sh

echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z configserver $CONFIGSERVER_PORT `; do sleep 10; done
echo ">>>>>>>>>>>> Configuration Server has started"

echo "********************************************************"
echo "Starting Eureka Server with Configuration Service :  $CONFIGSERVER_URI";
echo "********************************************************"
java -Dspring.profiles.active=$PROFILE                                      \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                            \
     -jar /usr/local/eurekaserver/@project.build.finalName@.jar
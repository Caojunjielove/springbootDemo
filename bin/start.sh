#! /bin/bash
clear
. para
java -jar ${app_jar_name} --spring.config.location=../config/application.yml> /dev/null 2>&1 & 
sleep 2
echo "app has start"
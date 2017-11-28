#! /bin/bash
sleep 1
clear
. para
pid=`ps -ef|grep ${app_jar_name}|grep -v grep`
if [ -n "${pid}" ]
then
    ps -ef|grep ${app_jar_name}|grep -v grep|grep -v tail|awk 'BEGIN{printf "kill "}{printf "%s ", $2}'|bash
        echo "service has close"
else
    echo "service is not start"
fi
#!/bin/bash
# stores the PID of the previously running jar
file=pid.txt
# path to Java
path=""
# Check if the Java version is provided as a command-line argument
if [ -z "$1" ]; then
    # If not provided, use a default
    path=java
else
    # If provided, use the provided value, for now only 21
    version="$1"
    path=/usr/lib/jvm/jdk-21.0.2/bin/java
fi
echo "Running template matcher with Java version : $version"
# read process id from previous_pid.txt and kill it
while read -r line;
do
echo "Line : $line"
if [ $line -gt 1000 ]
    then
        kill -9 $line;
        echo "Killed previous jar [Process ID : $line]";
    else
        echo "Here"
fi

done < $file
sleep 2
# pick the latest jar from the current directory
service=`ls -t1 |grep .jar |head -n 1`
echo "Running the jar : $service"
nohup $path -jar $service spring.output.ansi.enabled = always -Xms2g -Xmx6g >/dev/null 2>&1 &
echo $! > $file &
echo "Started the jar : $service successfully."

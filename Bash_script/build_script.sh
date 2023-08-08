#!/bin/bash
BRANCH1=$1
if [ "$BRANCH1" = "main" ]; then
    echo "clonning Main branch"
    git clone https://github.com/Anamika-2000/IP_Finder.git
else
    echo "branch name is $BRANCH1"
    git clone -b $BRANCH1 https://github.com/Anamika-2000/IP_Finder.git
fi
export JAVA_HOME=/tmp/jdk-17.0.8/
export PATH=$PATH:/tmp/gradle-8.1.1/bin
cd IP_Finder
gradle build 
pwd
cd build
cd distributions
echo check zip file at the this path: 
pwd
cd ../../../../
echo "done"
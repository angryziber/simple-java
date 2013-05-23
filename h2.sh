#!/bin/bash
# This script will give you H2 database shell

java -cp lib/default/h2*.jar org.h2.tools.Shell -url 'jdbc:h2:.simple-java;AUTO_SERVER=TRUE' -user sa -password sa

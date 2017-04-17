#!/bin/bash

echo " > creating clean directories"
rm -r classes
mkdir classes
rm -r jars
mkdir jars

echo " > compiling and packaging Advent"
# using Java9's new long forms of existing options
mkdir classes/org.codefx.demo.advent
javac9 -d classes/org.codefx.demo.advent \
	$(find src/org.codefx.demo.advent -name '*.java')
jar9 --create \
	--file jars/org.codefx.demo.advent.jar \
	--manifest src/org.codefx.demo.advent/manifest.mf \
	-C classes/org.codefx.demo.advent/ .

echo " > running Advent"
java9 -jar jars/org.codefx.demo.advent.jar

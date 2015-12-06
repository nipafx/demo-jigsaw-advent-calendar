#!/bin/bash

echo " > creating clean directories"
rm -r classes
mkdir classes
rm -r jars
mkdir jars

echo " > compiling and packaging Advent"
mkdir classes/org.codefx.demo.advent
javac -d classes/org.codefx.demo.advent \
	src/org.codefx.demo.advent/org/codefx/demo/advent/Surprise.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/SurpriseFactory.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/surprises/AbstractSurpriseFactory.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/surprises/ChocolateFactory.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/surprises/QuoteFactory.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/Calendar.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/CalendarSheet.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/Main.java
jar -cfm jars/org.codefx.demo.advent.jar \
	src/org.codefx.demo.advent/manifest.mf \
	-C classes/org.codefx.demo.advent/ .

echo " > running Advent"
java -jar jars/org.codefx.demo.advent.jar

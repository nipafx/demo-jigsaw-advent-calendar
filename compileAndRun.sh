#!/bin/bash

echo " > creating clean directories"
rm -r classes
mkdir classes
rm -r mods
mkdir mods

echo " > compiling and packaging Advent"
mkdir classes/org.codefx.demo.advent
$JIGSAW_BIN/javac \
	-d classes/org.codefx.demo.advent \
	src/org.codefx.demo.advent/module-info.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/Surprise.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/SurpriseFactory.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/surprises/AbstractSurpriseFactory.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/surprises/ChocolateFactory.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/surprises/QuoteFactory.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/Calendar.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/CalendarSheet.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/Main.java
$JIGSAW_BIN/jar \
	-c \
	--file mods/org.codefx.demo.advent.jar \
	--main-class=org.codefx.demo.advent.Main \
	-C classes/org.codefx.demo.advent/ .

echo " > running Advent"
$JIGSAW_BIN/java \
	-mp mods \
	-m org.codefx.demo.advent

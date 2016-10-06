#!/bin/bash

echo " > creating clean directories"
rm -r classes
mkdir classes
rm -r mods
mkdir mods

echo " > compiling and packaging Surprise"
mkdir classes/org.codefx.demo.advent.surprise
$JIGSAW_BIN/javac \
	-d classes/org.codefx.demo.advent.surprise \
	src/org.codefx.demo.advent.surprise/module-info.java \
	src/org.codefx.demo.advent.surprise/org/codefx/demo/advent/surprise/Surprise.java \
	src/org.codefx.demo.advent.surprise/org/codefx/demo/advent/surprise/SurpriseFactory.java
$JIGSAW_BIN/jar \
	-c \
	--file mods/org.codefx.demo.advent.surprise.jar \
	-C classes/org.codefx.demo.advent.surprise/ .

echo " > compiling and packaging Calendar"
mkdir classes/org.codefx.demo.advent.calendar
$JIGSAW_BIN/javac \
	-p mods \
	-d classes/org.codefx.demo.advent.calendar \
	src/org.codefx.demo.advent.calendar/module-info.java \
	src/org.codefx.demo.advent.calendar/org/codefx/demo/advent/calendar/Calendar.java \
	src/org.codefx.demo.advent.calendar/org/codefx/demo/advent/calendar/CalendarSheet.java
$JIGSAW_BIN/jar \
	-c \
	--file mods/org.codefx.demo.advent.calendar.jar \
	-C classes/org.codefx.demo.advent.calendar/ .

echo " > compiling and packaging ChocolateFactory"
mkdir classes/org.codefx.demo.advent.factory.chocolate
$JIGSAW_BIN/javac \
	-p mods \
	-d classes/org.codefx.demo.advent.factory.chocolate \
	src/org.codefx.demo.advent.factory.chocolate/module-info.java \
	src/org.codefx.demo.advent.factory.chocolate/org/codefx/demo/advent/factory/chocolate/AbstractSurpriseFactory.java \
	src/org.codefx.demo.advent.factory.chocolate/org/codefx/demo/advent/factory/chocolate/ChocolateFactory.java
$JIGSAW_BIN/jar \
	-c \
	--file mods/org.codefx.demo.advent.factory.chocolate.jar \
	-C classes/org.codefx.demo.advent.factory.chocolate/ .

echo " > compiling and packaging QuoteFactory"
mkdir classes/org.codefx.demo.advent.factory.quote
$JIGSAW_BIN/javac \
	-p mods \
	-d classes/org.codefx.demo.advent.factory.quote \
	src/org.codefx.demo.advent.factory.quote/module-info.java \
	src/org.codefx.demo.advent.factory.quote/org/codefx/demo/advent/factory/quote/AbstractSurpriseFactory.java \
	src/org.codefx.demo.advent.factory.quote/org/codefx/demo/advent/factory/quote/QuoteFactory.java
$JIGSAW_BIN/jar \
	-c \
	--file mods/org.codefx.demo.advent.factory.quote.jar \
	-C classes/org.codefx.demo.advent.factory.quote/ .

echo " > compiling and packaging Advent"
mkdir classes/org.codefx.demo.advent
$JIGSAW_BIN/javac \
	-p mods \
	-d classes/org.codefx.demo.advent \
	src/org.codefx.demo.advent/module-info.java \
	src/org.codefx.demo.advent/org/codefx/demo/advent/Main.java
$JIGSAW_BIN/jar \
	-c \
	--file mods/org.codefx.demo.advent.jar \
	--main-class org.codefx.demo.advent.Main \
	-C classes/org.codefx.demo.advent/ .

echo " > running Advent"
$JIGSAW_BIN/java -p mods -m org.codefx.demo.advent
